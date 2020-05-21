import network
import dht

def Connect_WiFi(thisnet, pass):            #function to connect the board to a specified WiFi network
    net = network.WLAN(network.AP_IF)       #createsa WLAN network to connect to an AP
    if not net.isconnected():               #if the board isn't connected to WiFi
        net.active(True)                    #activate the network interface
        net.connect(thisnet, pass)          #connect the board to network thisnet with password password
    print(net.ifconfig())                   #print to find out if the board is connected to a WiFi
