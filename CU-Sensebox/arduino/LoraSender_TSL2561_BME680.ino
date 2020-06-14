#include <SPI.h>
#include <LoRa.h>
#include <Wire.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_TSL2561_U.h>
#include <Adafruit_BME680.h>

/*********************************************** TSL2561 configuration*******************************************************/ 


/*
void displaySensorDetails(void)
{
  sensor_t sensor;
  tsl.getSensor(&sensor);
  Serial.println("------------------------------------");
  Serial.print  ("Sensor:       "); Serial.println(sensor.name);
  Serial.print  ("Driver Ver:   "); Serial.println(sensor.version);
  Serial.print  ("Unique ID:    "); Serial.println(sensor.sensor_id);
  Serial.print  ("Max Value:    "); Serial.print(sensor.max_value); Serial.println(" lux");
  Serial.print  ("Min Value:    "); Serial.print(sensor.min_value); Serial.println(" lux");
  Serial.print  ("Resolution:   "); Serial.print(sensor.resolution); Serial.println(" lux");  
  Serial.println("------------------------------------");
  Serial.println("");
  delay(500);
}
*/ 

Adafruit_TSL2561_Unified tsl = Adafruit_TSL2561_Unified(TSL2561_ADDR_FLOAT, 12345);

    //Configures the gain and integration time for the TSL2561
void configureSensor(void)
{
  /* You can also manually set the gain or enable auto-gain support */
  // tsl.setGain(TSL2561_GAIN_1X);      /* No gain ... use in bright light to avoid sensor saturation */
  // tsl.setGain(TSL2561_GAIN_16X);     /* 16x gain ... use in low light to boost sensitivity */
  tsl.enableAutoRange(true);            /* Auto-gain ... switches automatically between 1x and 16x */
  
  /* Changing the integration time gives you better sensor resolution (402ms = 16-bit data) */
  tsl.setIntegrationTime(TSL2561_INTEGRATIONTIME_13MS);      /* fast but low resolution */
  // tsl.setIntegrationTime(TSL2561_INTEGRATIONTIME_101MS);  /* medium resolution and speed   */
  // tsl.setIntegrationTime(TSL2561_INTEGRATIONTIME_402MS);  /* 16-bit data but slowest conversions */

  /* Update these values depending on what you've set above! */  
  Serial.println("------------------------------------");
  Serial.print  ("Gain:         "); Serial.println("Auto");
  Serial.print  ("Timing:       "); Serial.println("13 ms");
  Serial.println("------------------------------------");
}
/*****************************************************************************************************************************/

/***************************************BME680 configuration******************************************************************/
//#define BME_SCK 13
//#define BME_MISO 12
//#define BME_MOSI 11
//#define BME_CS 10

//#define SEALEVELPRESSURE_HPA (1032.5)  // UPDATE THIS NUMBER

Adafruit_BME680 bme; // I2C
//Adafruit_BME680 bme(BME_CS); // hardware SPI
//Adafruit_BME680 bme(BME_CS, BME_MOSI, BME_MISO,  BME_SCK);
/*****************************************************************************************************************************/

/**************************Dfine pins used by transceiver module RFM95*******************************************************/
#define nss 10 
#define reset 9
#define dio0 2

int counter = 0;

void setup() {
  Serial.begin(9600);
  Serial.println("Light Sensor Test"); Serial.println("");
  
  /* Initialise the TSL2561 sensor */
  //use tsl.begin() to default to Wire, 
  //tsl.begin(&Wire2) directs api to use Wire2, etc.
  if(!tsl.begin())
  {
    /* There was a problem detecting the TSL2561 ... check your connections */
    Serial.print("Ooops, no TSL2561 detected ... Check your wiring or I2C ADDR!");
    while(1);
  }
  /****************************************************/

  /* Initialise the BME680 sensor*/
  while (!Serial);
  Serial.println(F("BME680 test"));

  if (!bme.begin(0x76)) {
    Serial.println("Could not find a valid BME680 sensor, check wiring!");
    while (1);
  }

  // Set up oversampling and filter initialization
  bme.setTemperatureOversampling(BME680_OS_8X);
  bme.setHumidityOversampling(BME680_OS_2X);
  bme.setPressureOversampling(BME680_OS_4X);
  bme.setIIRFilterSize(BME680_FILTER_SIZE_3);
  bme.setGasHeater(320, 150); // 320*C for 150 ms

 /**************************************************************/
  while (!Serial);

  Serial.println("LoRa Sender");

  // setup LoRa transceiver module
  LoRa.setPins(nss, reset, dio0);
  //LoRa.setSPIFrequency(1e6);
  
  if (!LoRa.begin(863E6)) {
    Serial.println("Starting LoRa failed!");
    while (1);
  }
}


  
void loop() {

  /* Get a new sensor event */
  sensors_event_t event; 
  tsl.getEvent(&event);
  
  Serial.print("Sending packet: ");
  Serial.println(counter);

  // send packet
  LoRa.beginPacket();

  if(event.light)
  {
    LoRa.print(event.light); 
    LoRa.println(" lux");
  }
  else
  {
    /* If event.light = 0 lux, the sensor is probably saturated
     *  and no reliable data could be generated! */
    LoRa.println("Sensor overload");
  }

if (! bme.performReading()) {
    LoRa.println("Failed to perform reading :(");
    return;
  }
  LoRa.print("Temperature = ");
  LoRa.print(bme.temperature);
  LoRa.println(" *C");

  LoRa.print("Pressure = ");
  LoRa.print(bme.pressure / 100.0);
  LoRa.println(" hPa");

  LoRa.print("Humidity = ");
  LoRa.print(bme.humidity);
  LoRa.println(" %");

  LoRa.print("Gas = ");
  LoRa.print(bme.gas_resistance / 1000.0);
  LoRa.println(" KOhms");

// It is necessary to update de SEALEVELPRESSURE_HPA to the local sea level pressure
  //Serial.print("Approx. Altitude = ");
  //Serial.print(bme.readAltitude(SEALEVELPRESSURE_HPA));
  //Serial.println(" m");

  LoRa.println();
  
  //LoRa.print(counter);
  LoRa.endPacket();
  counter++;

  delay(1000);
}
