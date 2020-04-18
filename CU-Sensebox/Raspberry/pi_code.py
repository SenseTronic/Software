from raspi_lora import LoRa, ModemConfig
global msg
# This is our callback function that runs when a message is received
def on_recv(payload):
    global msg
    print("From:", payload.header_from)
    msg = payload.message

lora = LoRa(0, 17, 2, freq=868, modem_config=ModemConfig.Bw125Cr45Sf128, tx_power=14, acks=True)
lora.on_recv = on_recv
buffer = []
#buffer.append(msg)

while True:
#    if msg != 0
    print("1")

#gtfhs
lora.close()
