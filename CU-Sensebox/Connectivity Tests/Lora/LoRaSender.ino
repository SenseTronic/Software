#include <SPI.h>
#include <LoRa.h>

//define pins used by transceiver module 
#define nss 10 
#define reset 5
#define dio0 2

int counter = 0;

void setup() {
  Serial.begin(9600);
  while (!Serial);

  Serial.println("LoRa Sender");

  // setup LoRa transceiver module
  LoRa.setPins(nss, reset, dio0);
  
  if (!LoRa.begin(863E6)) {
    Serial.println("Starting LoRa failed!");
    while (1);
  }
}

void loop() {
  Serial.print("Sending packet: ");
  Serial.println(counter);

  // send packet
  LoRa.beginPacket();
  LoRa.print("Ping");
  LoRa.print(counter);
  LoRa.endPacket();

  counter++;

  delay(1000);
}
