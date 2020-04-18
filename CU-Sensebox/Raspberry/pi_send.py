from raspi_lora import LoRa, ModemConfig
import time
# This is our callback function that runs when a message is received
def on_recv(payload):
    print("From:", payload.header_from)
    print("Received:", payload.message)

#The selected SPI channel is 0, The selected interrupt pin is GPIO17
lora = LoRa(0, 17, 2, freq=868, modem_config=ModemConfig.Bw125Cr45Sf128, tx_power=14, acks=False)

lora.on_recv = on_recv

while True:
    send_to_wait("teste", 255, header_flags=0)
    time.sleep(2)

# And remember to call this as your program exits...
lora.close()
