#!/usr/bin/env python
from mqtt import MQTTClient             #
from network import WLAN
from network import LoRa
import machine
import socket               #precisamos
import time

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

     # IP externo do meu PC: 193.136.93.249
wlan = WLAN(mode=WLAN.STA)
#wlan.connect("ASUS_X008D", auth=(WLAN.WPA2, "skdlsdj"), timeout=3000)
wlan.connect("nubia Z17 lite", auth=(WLAN.WPA, "caarolina"), timeout=3000)
connectWiFi()


print("Connecting to Lora Server...\n")

client = MQTTClient("pycom-fipy-ua01", "192.168.0.", user="pasathos", password="", port=1883)

failMQTT = False
topic="sala-anf-v"
client.set_callback(sub_cb)
connectMQTT()


while True:
    #if sensor.get_sensor_data():       #por aqui posteriomente a condição do sensor estar a mandar dados
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


    # Lora send data
    #####lora_socket.send(output)
    # MQTT send data
    try:
        print("Sending MQTT data...")
        #mqtt_data = "{0}, {1:2.1f}, {2:4.1f}, {3:2.1f}, {4:7.0f}, {5:2.1f}, {6:3.0f}, {7:3.0f}".format(
        #timestamp, sensor.data.temperature, sensor.data.pressure, sensor.data.humidity, sensor.data.gas_resistance, oxygen, co2_1, co2_2)
        mqtt_data = "teste"
        client.publish(topic + ".sensors", mqtt_data)
        #client.publish(topic + ".temperature", str(sensor.data.temperature))
        #client.publish(topic + ".pressure", str(sensor.data.pressure))
        #client.publish(topic + ".humidity", str(sensor.data.humidity))
        #client.publish(topic + ".gas-resistance", str(sensor.data.gas_resistance))
        #client.publish(topic + ".oxygen", str(oxygen))
        #client.publish(topic + ".co2_2", str(co2_2))
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
