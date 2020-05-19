package com.example.senseapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;


import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class one extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    private String abs="";
    TextView sensor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);


        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);

        sensor = findViewById(R.id.textView30);


        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                textView.setText("Your choice: " + radioButton.getText());
                abs= radioButton.getText().toString();
                sensor.setText(abs);
            }

        });

       // Intent i = new Intent(this, ConnectingSensors.class);
       // i.putExtra("sensor",abs);
       // startActivity(i);

        //Intent myIntent = myIntent(view.getContext(),ConnectingSensors.class);
        //myIntent.putExtra("abs",abs);
        //startActivity(myIntent);

    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();


    }
}