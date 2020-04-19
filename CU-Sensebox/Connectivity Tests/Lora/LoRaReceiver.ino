#include <SPI.h>
#include <LoRa.h>

//define pins used by the transceiver module
#define nss 10
#define reset 5
#define dio0 2

void setup() {
  Serial.begin(9600);
  while (!Serial);

  Serial.println("LoRa Receiver");

  //setup LoRa transceiver module
  LoRa.setPins(nss, reset, dio0);

  if (!LoRa.begin(863E6)) {
    Serial.println("Starting LoRa failed!");
    while (1);
  }
}

void loop() {
  // try to parse packet
  int packetSize = LoRa.parsePacket();
  if (packetSize) {
    // received a packet
    Serial.print("Received packet '");

    // read packet
    while (LoRa.available()) {
      Serial.print((char)LoRa.read());
    }

    // print RSSI of packet
    Serial.print("' with RSSI ");
    Serial.println(LoRa.packetRssi());
  }
}
