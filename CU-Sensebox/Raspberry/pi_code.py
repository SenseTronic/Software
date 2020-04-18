from raspi_lora import LoRa, ModemConfig

# This is our callback function that runs when a message is received
def on_recv(payload):
    print("Received:", payload.message)

#The selected SPI channel is 0, The selected interrupt pin is GPIO17
lora = LoRa(0, 17, 2, freq=868, modem_config=ModemConfig.Bw125Cr45Sf128, tx_power=14, acks=True)

lora.on_recv = on_recv

while True:
    print("1")

# And remember to call this as your program exits...
lora.close()
