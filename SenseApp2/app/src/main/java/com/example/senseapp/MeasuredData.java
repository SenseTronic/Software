package com.example.senseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.app.Notification;
import androidx.core.app.NotificationManagerCompat;


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