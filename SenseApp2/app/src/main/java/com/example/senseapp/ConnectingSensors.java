package com.example.senseapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ConnectingSensors extends AppCompatActivity {

    private Button button4;
    private Button button6;
    private Button button7;
    private Button button8;
    private TextView Info1;

    TextView porto1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecting_sensors);


        porto1 = findViewById(R.id.porto1);


        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openOne();
            }
        });
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTwo();
            }
        });
        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openThree();
            }
        });
        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openFour();
            }
        });


    }
    public void openOne(){
        Intent intent = new Intent(this, one.class);
        startActivity(intent);
    }
    public void openTwo(){
        Intent intent = new Intent(this, two.class);
        startActivity(intent);
    }
    public void openThree(){
        Intent intent = new Intent(this, three.class);
        startActivity(intent);
    }
    public void openFour(){
        Intent intent = new Intent(this, four.class);
        startActivity(intent);
    }

}


