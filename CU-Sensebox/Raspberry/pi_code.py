from raspi_lora import LoRa, ModemConfig
import time

# This is our callback function that runs when a message is received
def on_recv(payload):
    print("From:", payload.header_from)
    print("Received:", payload.message)
    
# This is a configuration function
# The selected SPI channel is 0, The selected interrupt pin is GPIO17
lora = LoRa(0, 17, 2, freq=868, acks = False)
lora.on_recv = on_recv

# Sets the radio to rx(receive) mode
lora.set_mode_rx()

try:
    while True:
        time.sleep(0.5)
except KeyboardInterrupt:
    print('Stopping...')
    lora.close()
