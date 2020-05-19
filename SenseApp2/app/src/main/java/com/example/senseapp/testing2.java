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


public class testing2 extends AppCompatActivity {

    private static final String KEY_FIRSTNAME= "firstname_key";

    EditText et;
    Button b;
    TextView tv;

    EditText et2;
    Button b2;
    TextView tv2;

    EditText et3;
    Button b3;
    TextView tv3;

    EditText et4;
    Button b4;
    TextView tv4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b=findViewById(R.id.button21);
        et = findViewById(R.id.editText2);
        tv=  findViewById(R.id.textView36);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = et.getText().toString();
                tv.setText(str);

            }
        });

        b2=findViewById(R.id.button19);
        et2 = findViewById(R.id.editText3);
        tv2=  findViewById(R.id.textView39);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = et2.getText().toString();
                tv2.setText(str);

            }
        });

        b3=findViewById(R.id.button23);
        et3 = findViewById(R.id.editText);
        tv3=  findViewById(R.id.textView54);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = et3.getText().toString();
                tv3.setText(str);

            }
        });

        b4=findViewById(R.id.button22);
        et4 = findViewById(R.id.editText4);
        tv4=  findViewById(R.id.textView47);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = et4.getText().toString();
                tv4.setText(str);

            }
        });


        if(savedInstanceState != null){
            String savedFirst=savedInstanceState.getString(KEY_FIRSTNAME);
            tv.setText(savedFirst);
        }else{
            Toast.makeText(this,"New entry", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString(KEY_FIRSTNAME, tv.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    public void saveView(View view){
        tv.setText(et.getText().toString().trim());
    }
}
