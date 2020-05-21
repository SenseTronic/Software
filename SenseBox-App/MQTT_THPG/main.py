#!/usr/bin/env python
import bme680                           #s biblioteca sensor bosch
from i2c import I2CAdapter              #s bibilioteca I2C
from mqtt import MQTTClient             #
from network import WLAN
from network import LoRa
import machine
from machine import RTC
from machine import ADC
import socket               #precisamos
import time
import pycom
from lcd_api import LcdApi
from pycom_i2c_lcd import I2cLcd

def sub_cb(topic, msg):
    print('Callback(cb) function called:', end = " ")
    print(msg+'\n')
    print('cb end.')
    pass

def connectWiFi():
    if wlan.isconnected():
        return
    print("Connecting to WiFi...")
    while not wlan.isconnected():
        try:
            machine.idle()
            print(".", end=" ")
            time.sleep(0.1)
        except Exception as e:
            print("WiFi connection Failed: ", end="")
            print(e)
            pass
    print("\nConnected to WiFi!")

def connectMQTT():
    print("Connecting to MQTT server...")
    try:
        client.connect()
        client.subscribe(topic + '.sensors')
        client.subscribe(topic + ".temperature")
        client.subscribe(topic + ".pressure")
        client.subscribe(topic + ".humidity")
        client.subscribe(topic + ".gas-resistance")
        client.subscribe(topic + ".oxygen")
        client.subscribe(topic + ".co2_1")
        client.subscribe(topic + ".co2_2")
        failMQTT = False
    except Exception as e:
        print("MQTT connection failed!")
        print("Error: ", end="")
        print(e)
        failMQTT = True
        return
    print("Connected to MQTT!")

def settimeout(duration):
     pass

def O2_Concentration():
    sum = 0
    for i in range (0,32):
        sum = sum + adc_oxygen.value();
    sum = sum / 32          # average of the 32 values
    sum = sum * 3.3 / 4096  # VRef = 3.3V
    #print("-----------{}V\n".format(sum))
    return (sum * 0.21 / 2.0 * 100)

# The PCF8574 has a jumper selectable address: 0x20 - 0x27
DEFAULT_I2C_ADDR = 0x27

print("--------------------- pycom fipy starting ---------------------\n")
pycom.heartbeat(False)
rtc = RTC()
rtc.init((2018, 12, 17, 12, 14, 0, 0, 0), source=RTC.XTAL_32KHZ)

i2c_dev = I2CAdapter()                                  #não é preciso(protocolo fios)
sensor = bme680.BME680(i2c_device=i2c_dev)              #n

lcd = I2cLcd(i2c_dev, DEFAULT_I2C_ADDR, 2, 16)
lcd.putstr("fipy SOCA\nby Paulo Santos")

# These oversampling settings can be tweaked to
# change the balance between accuracy and noise in
# the data.
sensor.set_humidity_oversample(bme680.OS_2X)            #tudo isto outra placa
sensor.set_pressure_oversample(bme680.OS_4X)
sensor.set_temperature_oversample(bme680.OS_8X)
sensor.set_filter(bme680.FILTER_SIZE_3)

# IP externo do meu PC: 193.136.93.249
wlan = WLAN(mode=WLAN.STA)
#wlan.connect("ASUS_X008D", auth=(WLAN.WPA2, "skdlsdj"), timeout=3000)
wlan.connect("nubia Z17 lite", auth=(WLAN.WPA, "caarolina"), timeout=3000)
connectWiFi()

print("Connecting to Lora Server...\n")
# Please pick the region that matches where you are using the device:
# Asia = LoRa.AS923
# Australia = LoRa.AU915
# Europe = LoRa.EU868
# United States = LoRa.US915
### lora = LoRa(mode=LoRa.LORA, region=LoRa.EU868)
### lora_socket = socket.socket(socket.AF_LORA, socket.SOCK_RAW)
### lora_socket.setblocking(False)

#client = MQTTClient("pycom-fipy-ua01", "io.adafruit.com", user="pas_it", password="8a8ac4bf9c264fd7ac3025a47cde6603", port=1883)
#topic="pas_it/feeds/sala-anf-v"

#client = MQTTClient("pycom-fipy-ua01", "io.adafruit.com", user="pasathos", password="8e4d97352e5341b686c0c811cd65144d", port=1883)
#topic="pasathos/feeds/sala-anf-v"
#client = MQTTClient("pycom-fipy-ua01", "192.168.0.201", user="", password="", port=1883)
#client = MQTTClient("pycom-fipy-ua01", "192.168.10.131", user="pas", password="", port=1883)
client = MQTTClient("pycom-fipy-ua01", "192.168.0.", user="pasathos", password="", port=1883)

failMQTT = False
topic="sala-anf-v"
client.set_callback(sub_cb)
connectMQTT()

adc = machine.ADC(bits=12)
adc_oxygen = adc.channel(pin='P16', attn=ADC.ATTN_11DB)
adc_co2 = adc.channel(pin='P15', attn=ADC.ATTN_11DB)

print("Polling:")
line = 0
fail = False
while True:
    if sensor.get_sensor_data():
        if fail:    # WiFi was down
            try:
                connectWiFi()
                fail = False
            except OSError: # WiFi is still down
                print("Reconnect with WiFi failed!")
        if failMQTT:    # MQTT was down
            try:
                connectMQTT()
                failMQTT = False
            except OSError: # WiFi is still down
                print("Reconnect with MQTT failed!")
        print("-------------------------------------------------------------")
        print("Line {:04d}".format(line))
        line = line + 1

        oxygen = O2_Concentration()
        #print("O2 Concentration = {:2.2f}%".format(oxygen))

        inBuf = [0, 0, 0, 0]
        try:
            i2c_dev.writeto(0x15, bytes([0x04, 0x13, 0x8B, 0x00, 0x01])) # send 5 bytes to slave with address 0x42
            inBuf = bytes(i2c_dev.readfrom(0x15, 4))
        except Exception as e:
            print("Error: ", end="")
            print(e)
            co2_1 = -1
        #print(inBuf)
        if (inBuf[0] == 0x04) and (inBuf[1] == 0x02):
            co2_1 = inBuf[2] * 256 + inBuf[3]
        else:
            co2_1 = -1

        #print("TelAire CO2 = {}ppm".format(co2_1))
        valid_co2 = False
        co2_2 = adc_co2()*(3300/4096.0)
        if (co2_2 == 0):
            print("CO2 (2) reading fault!")
        elif (co2_2 < 400):
            print("CO2 (2) preheating!")
        else:
            valid_co2 = True
            co2_2 = (co2_2 - 400) * 50.0 / 16.0;
            #print("CO2 (2) Concentration = {}".format(co2_2))

        #if (valid_co2 == False):
        #    co2_2 = -1

        dt = rtc.now()
        timestamp = "{:02d}-{:02d}-{:4d}, {:02d}:{:02d}:{:02d}".format(dt[2], dt[1], dt[0], dt[3], dt[4], dt[5])
        output = "T = {0:2.1f} C, P = {1:4.1f} hPa, H = {2:2.1f} RH, R = {3:7.0f} RES, O = {4:2.1f} %, CO2(1) = {5:3.0f} ppm, CO2(2) = {6:3.0f} ppm\nDate/Time = {7}".format(
            sensor.data.temperature, sensor.data.pressure, sensor.data.humidity, sensor.data.gas_resistance, oxygen, co2_1, co2_2, timestamp)
        mqtt_data = "{0}, {1:2.1f}, {2:4.1f}, {3:2.1f}, {4:7.0f}, {5:2.1f}, {6:3.0f}, {7:3.0f}".format(
            timestamp, sensor.data.temperature, sensor.data.pressure, sensor.data.humidity, sensor.data.gas_resistance, oxygen, co2_1, co2_2)
        print(output)
        #print(mqtt_data)

        #lcd.clear()    # takes too long to process
        lcd.move_to(0, 0)
        lcd.putstr("T={0:2.1f}C H={1:2.1f}RH".format(sensor.data.temperature, sensor.data.humidity))
        lcd.move_to(0, 1)
        lcd.putstr("                ")
        lcd.move_to(0, 1)
        lcd.putstr("O={0:2.1f}% CO2={1:3.0f}p".format( oxygen, co2_2))

        # Lora send data
        #####lora_socket.send(output)
        # MQTT send data
        try:
            print("Sending MQTT data...")
            client.publish(topic + ".sensors", mqtt_data)
            client.publish(topic + ".temperature", str(sensor.data.temperature))
            client.publish(topic + ".pressure", str(sensor.data.pressure))
            client.publish(topic + ".humidity", str(sensor.data.humidity))
            client.publish(topic + ".gas-resistance", str(sensor.data.gas_resistance))
            client.publish(topic + ".oxygen", str(oxygen))
            client.publish(topic + ".co2_1", str(co2_1))
            client.publish(topic + ".co2_2", str(co2_2))
            client.check_msg()
            failMQTT = False
            print("MQTT data sent!")
        except Exception as e:
            print("Publish fail!")
            print("MQTT Error: ", end="")
            print(e)
            fail = True     # because WiFi might be down
            failMQTT = True
        #print("Temperature=" + str(sensor.data.temperature) + "ºC")

        time.sleep(7)
