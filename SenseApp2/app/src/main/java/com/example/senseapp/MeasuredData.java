package com.example.senseapp;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.app.Notification;
import androidx.core.app.NotificationManagerCompat;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import static com.example.senseapp.App.CHANNEL_1_ID;

public class MeasuredData extends AppCompatActivity {


    private NotificationManagerCompat notificationManager;
    static String topicStr = "t0th/temperature";
    static String topicStr2 = "t0th/humidity";
    static String topicStr3 = "alerta";

    static String MQTTHOST = "tcp://test.mosquitto.org:1883";

    MqttAndroidClient client;

    TextView subText;
    TextView subText2;

    int RefTemp;
    int RefHum=30;
    int RefLum;
    int RefPre=30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_medidos);

        subText = (TextView) findViewById(R.id.textView16);  //temp
        subText2 = (TextView) findViewById(R.id.textView17); //hum

        String clientId = MqttClient.generateClientId();

        //client = new MqttAndroidClient(MainActivity.this, MQTTHOST, clientId);
        client = new MqttAndroidClient(MeasuredData.this, MQTTHOST, clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MeasuredData.this, "Connected to test.mosquitto.org!", Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MeasuredData.this, "Connection failed!", Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                if (topic.equals(topicStr)) {
                    subText.setText("Temperature = " + new String(message.getPayload()) + " ºC");
                }
                if (topic.equals(topicStr2)) {
                    subText2.setText("Humidity = " + new String(message.getPayload()) + " %");
                }

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        notificationManager = NotificationManagerCompat.from(this);


       /**
         if(temp>refTemp){
         Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
         .setSmallIcon(R.drawable.ic_message)
         .setContentTitle("Warning")                            //title
         .setContentText("Temperature-Too High!")               //message
         .setPriority(NotificationCompat.PRIORITY_HIGH)
         .setCategory(NotificationCompat.CATEGORY_MESSAGE)
         .build();

         notificationManager.notify(1, notification);
         }


         if(hum>refHum){
         Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
         .setSmallIcon(R.drawable.ic_message)
         .setContentTitle("Warning")                            //title
         .setContentText("Humidity- Excessive!")                      //message
         .setPriority(NotificationCompat.PRIORITY_HIGH)
         .setCategory(NotificationCompat.CATEGORY_MESSAGE)
         .build();

         notificationManager.notify(1, notification);

         }
         **/
    }



    private void setSubscription() {
        try {
            client.subscribe(topicStr, 0);
            client.subscribe(topicStr2, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }




    public void sendOnChannelTemp(View v) {
        //if(testenumero>0) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Warning")                            //title
                .setContentText("Temperature-Too High!")               //message
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
        //}
    }

    public void sendOnChannelHum(View v) {

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Warning")                            //title
                .setContentText("Humidity- Excessive!")                      //message
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }

    public void sendOnChannelLum(View v) {

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Warning")                            //title
                .setContentText("Luminosity- Excessive!")                //message
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }

    public void sendOnChannelPres(View v) {

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Warning")                            //title
                .setContentText("Pression-Very High!")                      //message
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }

}

/**

 package com.example.senseapp;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.app.NotificationCompat;

 import android.os.Bundle;

 import android.view.View;
 import android.widget.Button;
 import android.app.Notification;
 import androidx.core.app.NotificationManagerCompat;

 package com.example.mqtt;

 import androidx.appcompat.app.AppCompatActivity;

 import android.os.Bundle;
 import android.util.Log;
 import android.view.View;
 import android.view.View.OnClickListener;
 import android.widget.Button;
 import android.widget.TextView;
 import android.widget.Toast;

 import org.eclipse.paho.android.service.MqttAndroidClient;
 import org.eclipse.paho.client.mqttv3.IMqttActionListener;
 import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
 import org.eclipse.paho.client.mqttv3.IMqttToken;
 import org.eclipse.paho.client.mqttv3.MqttCallback;
 import org.eclipse.paho.client.mqttv3.MqttClient;
 import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
 import org.eclipse.paho.client.mqttv3.MqttException;
 import org.eclipse.paho.client.mqttv3.MqttMessage;



 import android.os.Bundle;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.RadioButton;
 import android.widget.RadioGroup;

 import java.text.DecimalFormat;

 import static com.example.senseapp.App.CHANNEL_1_ID;


 public class MeasuredData extends AppCompatActivity {


 // database_helper myDb = new database_helper(this);

 private NotificationManagerCompat notificationManager;
 private EditText editText1;
 //private EditText editTextMessage;

 EditText teste;
 private Double testenumero;


 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_dados_medidos);

 //teste =findViewById(R.id.etName2);
 //String teste2=teste.getText().toString();
 //testenumero= Double.parseDouble(teste2);   //o erro é originado nesta linha

 //teste = (EditText)findViewById(R.id.teste);

 //testenumero= Double.parseDouble(teste.getText().toString());

 /**try{

 teste.getText().toString();
 DecimalFormat dF= new DecimalFormat("0.00");
 testenumero= dF.parse(teste);

 }catch(Exception e){
 }
 **/
/**
 notificationManager = NotificationManagerCompat.from(this);

 //sendOnChannelTemp(testenumero);


 }
 public void sendOnChannelTemp(View v){
 //if(testenumero>0) {
 Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
 .setSmallIcon(R.drawable.ic_message)
 .setContentTitle("Warning")                            //title
 .setContentText("Temperature-Too High!")               //message
 .setPriority(NotificationCompat.PRIORITY_HIGH)
 .setCategory(NotificationCompat.CATEGORY_MESSAGE)
 .build();

 notificationManager.notify(1, notification);
 //}
 }
 public void sendOnChannelHum(View v){

 Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
 .setSmallIcon(R.drawable.ic_message)
 .setContentTitle("Warning")                            //title
 .setContentText("Humidity- Excessive!")                      //message
 .setPriority(NotificationCompat.PRIORITY_HIGH)
 .setCategory(NotificationCompat.CATEGORY_MESSAGE)
 .build();

 notificationManager.notify(1, notification);

 }
 public void sendOnChannelLum(View v){

 Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
 .setSmallIcon(R.drawable.ic_message)
 .setContentTitle("Warning")                            //title
 .setContentText("Luminosity- Excessive!")                //message
 .setPriority(NotificationCompat.PRIORITY_HIGH)
 .setCategory(NotificationCompat.CATEGORY_MESSAGE)
 .build();

 notificationManager.notify(1, notification);

 }
 public void sendOnChannelPres(View v){

 Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
 .setSmallIcon(R.drawable.ic_message)
 .setContentTitle("Warning")                            //title
 .setContentText("Pression-Very High!")                      //message
 .setPriority(NotificationCompat.PRIORITY_HIGH)
 .setCategory(NotificationCompat.CATEGORY_MESSAGE)
 .build();

 notificationManager.notify(1, notification);

 }


 }
 **/

