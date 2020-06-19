from raspi_lora import LoRa, ModemConfig
import time
import random
global msg
global ack

# This is our callback function that runs when a message is received
def on_recv(payload):
    print("From:", payload.header_from)
    print("Received:", payload.message)
    msg = payload.message
    if msg == "ack"
        ack = 1

# This is a configuration function
# The selected SPI channel is 0, The selected interrupt pin is GPIO17
lora = LoRa(0, 17, 2, freq=868, modem_config=ModemConfig.Bw125Cr45Sf128, tx_power=14, acks=False)

lora.on_recv = on_recv

try:
    while True:
        if  flag == 1: #flag should be activated outside of this cycle whenever a message is meant to be sent
          do
              send_to_wait("teste", 255, header_flags=0)
              flag = 0
            t1 = #greater than the round-trip delay
            time.sleep(t1)

            if ack = 1:
                ack = 0
                break
            rt = random.random() #returns a floating point from 0 to 1
            k = #range that you want the random number to be choosen from
            rt1 = k*rt
            time.sleep(rt1)
          while ack == 0
    except KeyboardInterrupt:
        print('Stopping...')
        lora.close()                   

# And remember to call this as your program exits...
lora.close()
