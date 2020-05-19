package com.example.senseapp.ui.login

//import android.support.v7.app.AppCompatActivity
//import br.com.hussan.mqttandroid.mqtt.MqttClient
//import kotlinx.android.synthetic.main.activity_sensor.txtHumidity
//import kotlinx.android.synthetic.main.activity_sensor.txtTemperature
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.senseapp.R
import org.eclipse.paho.client.mqttv3.MqttMessage

class SensorActivity : AppCompatActivity() {

        companion object {
            const val TAG = "SensorActivity"
            const val TEMPERATURE_TOPIC = "t0th/temperature"
            const val HUMIDITY_TOPIC = "t0th/humidity"
            const val text = ""

        }


    val mqttClient by lazy {
            MqttClient(this)
        }

        //@Override
        //fun onCreate(savedInstanceState: Bundle?) {
        //   super.onCreate(savedInstanceState)
        //    setContentView(R.layout.activity_sensor)
            //  mqttClient.connect(BROKER)
            //mqttClient.connect(arrayOf(TEMPERATURE_TOPIC, HUMIDITY_TOPIC), ::setData)
        //}
       override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sensor);




          //     mqttClient.connect(BROKER) //substituir BROKER
          //     mqttClient.setCallBack(arrayOf(TEMPERATURE_TOPIC, HUMIDITY_TOPIC), ::setData)



        }

        private fun setData(topic: String, msg: MqttMessage) {

            //declaração de uma TextView
            var temperatura: TextView? = null
            //associação à textView56
            temperatura = findViewById(R.id.textView56)

            //declaração de uma TextView
            var humidade: TextView? = null
            //associação à textView55
            humidade = findViewById(R.id.textView55)



            when (topic) {
                TEMPERATURE_TOPIC -> {
                    val text_temp= " ${String(msg.payload)} ° c"

                    //enviar a String acima para a textView
                    humidade.setText(text_temp)

                    //println(text_temp)
                }
                else -> {
                    val text = " ${String(msg.payload)}"

                    //enviar a String acima para a textView
                    temperatura.setText(text)

                    print(text)
                }
            }

        }

        override fun onDestroy() {
            super.onDestroy()
            mqttClient.close()
        }
    }
