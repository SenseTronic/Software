package com.example.senseapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    database_helper myDb;
    private EditText Name;
    private EditText Password;
    private Button Login;
    //private TextView Info;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb = new database_helper(this);
        Name = (EditText)findViewById(R.id.etName);
        Password= (EditText)findViewById(R.id.etPassword);
      //  Info = (TextView)findViewId(R.id.tvInfo);
        Login= (Button)findViewById(R.id.btnLogin);

        //Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
              validate(Name.getText().toString(),Password.getText().toString());
              //add_to_Database();


                viewAll();
            }
        });

}

    private void validate(String userName, String userPassword){

        if((userName.equals("Samuel")) && (userPassword.equals("sam"))){
            Intent intent = new Intent(Main2Activity.this, AfterLogIn.class);
            startActivity(intent);
        }else{
            counter--;

           // Info.setText("No of attempts remaining " + String.valueOf(counter));

            if(counter == 0)
            {
              Login.setEnabled(false);
            }
        }

    }

    public void add_to_Database (){
        boolean isinserted = myDb.insertData( Name.getText().toString(),
                Password.getText().toString(), 7 );
        if(isinserted == true)
            Toast.makeText(Main2Activity.this, "Data inserted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Main2Activity.this, "Data  not inserted", Toast.LENGTH_SHORT).show();
    }

    public void viewAll() {
        //button14.setOnClickListener(
        // new View.OnClickListener() {
        //        @Override
        //   public void onClick(View v) {
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            //show message
            ShowMessage("Error", "No data found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("ID:"+ res.getString(0)+ "\n" );
            buffer.append("Name:"+ res.getString(1)+ "\n" );
            buffer.append("Password:"+ res.getString(2)+ "\n" );
            buffer.append("Temp_Threshold:"+ res.getString(3)+ "\n" );
            buffer.append("Lum_Threshold:"+ res.getString(4)+ "\n" );
            buffer.append("Hum_Threshold:"+ res.getString(5)+ "\n\n" );
        }
        ShowMessage("Data", buffer.toString());
        //}
        //}
        //);

        //buffer.append("ID:"+ res.getString(0)+ "\n" );
    /*

        ShowMessage("Data", buffer.toString());

    */
    }
    public void ShowMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
