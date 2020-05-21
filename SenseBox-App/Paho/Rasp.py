temperature_topic = "t0th/temperature"
humidity_topic = "t0th/humidity"
broker_url = "iot.eclipse.org"

import paho.mqtt.client as mqtt
import time
#import Adafruit_DHT as dht
#from variables import *        ----pus ali em cima

def mqtt_client_connect():
    print("connected to: ", broker_url)
    client.connect(broker_url)
    client.loop_start()

client = mqtt.Client("client_name")
mqtt_client_connect()

while True:
    humidity, temperature = "3"
    print('Sending... Temperature: {}   Humidity: {}'.format(temperature, humidity))
    client.publish(temperature_topic, temperature)
    client.publish(humidity_topic, humidity)
    time.sleep(3)
