package com.example.senseapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import android.os.Bundle;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.Toast;
 import androidx.appcompat.app.AppCompatActivity;
 import java.io.BufferedReader;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStreamReader;


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

    private static final String FILE_NAME = "example1.txt";
    private static final String FILE_NAME2 = "example2.txt";
    private static final String FILE_NAME3 = "example3.txt";
    private static final String FILE_NAME4 = "example4.txt";


    EditText mEditText, mEditText2, mEditText3, mEditText4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEditText = findViewById(R.id.editText2);
        mEditText2= findViewById(R.id.editText3);
        mEditText3= findViewById(R.id.editText);
        mEditText4= findViewById(R.id.editText4);



        if(savedInstanceState != null){
            String savedFirst=savedInstanceState.getString(KEY_FIRSTNAME);
            tv.setText(savedFirst);
        }else{
            Toast.makeText(this,"There are sensors connected", Toast.LENGTH_SHORT).show();
        }

    }
    public void save1(View v) {
          String text = mEditText.getText().toString();
          FileOutputStream fos = null;
          try {
              fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
              fos.write(text.getBytes());
              mEditText.getText().clear();
              Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                                        Toast.LENGTH_LONG).show();
         } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              if (fos != null) {
                  try {
                      fos.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
      public void load1(View v) {
          FileInputStream fis = null;
          try {
              fis = openFileInput(FILE_NAME);
              InputStreamReader isr = new InputStreamReader(fis);
              BufferedReader br = new BufferedReader(isr);
              StringBuilder sb = new StringBuilder();
              String text;
              while ((text = br.readLine()) != null) {
                  sb.append(text).append("\n");
              }
              mEditText.setText(sb.toString());
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              if (fis != null) {
                  try {
                      fis.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
     }
    public void save2(View v) {
        String text = mEditText2.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME2, MODE_PRIVATE);
            fos.write(text.getBytes());
            mEditText2.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME2,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void load2(View v) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME2);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            mEditText2.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void save3(View v) {
        String text = mEditText3.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME3, MODE_PRIVATE);
            fos.write(text.getBytes());
            mEditText3.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME3,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void load3(View v) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME3);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            mEditText3.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void save4(View v) {
        String text = mEditText4.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME4, MODE_PRIVATE);
            fos.write(text.getBytes());
            mEditText4.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME4,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void load4(View v) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME4);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            mEditText4.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

