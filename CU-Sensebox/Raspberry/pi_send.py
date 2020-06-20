from raspi_lora import LoRa, ModemConfig
import time

def on_recv(payload):
    print("From:", payload.header_from)
    print("Received:", payload.message)

lora = LoRa(0, 17, 2, freq=868, acks = False)
lora.on_recv = on_recv

lora.set_mode_tx()

message = "Ping"

try:
    while True:
        lora.send(message, 2, 255, header_flags=0)
        print('message sent')
        time.sleep(1)
except KeyboardInterrupt:
    print('Stopping...')
    lora.close()
