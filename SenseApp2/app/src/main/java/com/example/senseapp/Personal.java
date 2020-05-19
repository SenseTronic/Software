package com.example.senseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Personal extends AppCompatActivity {

    TextView showValue;
    int counter = 0;

    TextView showValue2;
    int counter2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        showValue=(TextView) findViewById(R.id.textView57);
        showValue2=(TextView) findViewById(R.id.textView58);

    }

    public void countIN(View view) {
        counter++;
        showValue.setText(Integer.toString(counter)+"%");
    }
    public void countDE(View v){
        counter--;
        showValue.setText(Integer.toString(counter)+"%");
    }

    public void countIN2(View view) {
        counter2++;
        showValue2.setText(Integer.toString(counter2)+"ºC");
    }
    public void countDE2(View v){
        counter2--;
        showValue2.setText(Integer.toString(counter2)+"ºC");
    }
}
