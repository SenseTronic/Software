package com.example.senseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.example.senseapp.ui.login.SensorActivity;

public class AfterLogIn extends AppCompatActivity {
    private Button button4;
    private Button button2;
    private Button button17;
    private Button button27;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_log_in);

        button4 = (Button) findViewById(R.id.button);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMeasuredData();
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        //button2.setOnClickListener(new View.OnClickListener(){
        //    @Override
        //    public void onClick(View v){
        //        openConnectingSensors();
        //    }
        //});

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTesting();
            }
        });

        button17 = (Button) findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openPersonal();
            }
        });

        /**
        button27 = (Button) findViewById(R.id.button27);
        button27.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSensor();
            }
        });
         **/

    }
    public void openMeasuredData(){
        Intent intent = new Intent(this, MeasuredData.class);
        startActivity(intent);
    }
    public void openConnectingSensors(){
        Intent intent = new Intent(this, ConnectingSensors.class);
        startActivity(intent);
    }

    public void openPersonal(){
        Intent intent = new Intent(this, Personal.class);
        startActivity(intent);
    }

    public void openTesting(){
        Intent intent = new Intent(this, testing2.class);
        startActivity(intent);
    }

    /**
    public void openSensor(){
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }
    **/
}
