package com.example.senseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database_helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Clients_Data.db";

    /*Tabela onde vão aparecer os dados dos clientes e os valores de threshold que pretendem para cada sensor*/
    public static final String TABLE_1 = "Clients";
    public static final String COL_1 = "ID";        /*qnd é adicionado um login é atribuido o id imediatamente*/
    public static final String COL_2 = "Name";
    public static final String COL_3= "Password";
    public static final String COL_4= "Temp_Threshold";         /*a ser preenchido qnd o cliente escrever na aplicação o devido valor*/
    public static final String COL_5= "Lum_Threshold";
    public static final String COL_6= "Hum_Threshold";


    /*Outra tabela onde vão aparecer os dados dos respetivos sensores. O ID vais ser a key. Key_table2 = Key_table1 */
    public static final String TABLE_2 = "Data";
    public static final String COL1 = "ID";        /*qnd é adicionado um login é atribuido o id imediatamente*/
    public static final String COL2 = "Tempe";
    public static final String COL3= "CO2";
    public static final String COL4= "CO";
    public static final String COL5= "Hum";
    public static final String COL6= "Lum";
    public static final String COL7= "Pressure";



    public database_helper(@Nullable Context context) {     /*cria a base de dados qnd chamada*/
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase() ; /*so para dar check*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_1 + "(ID Integer Primary key AUTOINCREMENT, Name text, Password text, Temp_Threshold double," +
                "Lum_Threshold double, Hum_Threshold double )"); /*cria uma tabela*/

        db.execSQL( " create table " + TABLE_2 + "(ID Integer Primary key AUTOINCREMENT, Tempe double, CO2 double, CO double," +
                "Hum double, Lum double, pressure double)" ); /*cria uma tabela*/
        //esta tabela vai conter ultimos x valores lidos nos sensores
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_1);
        onCreate(db);
    }

    public boolean insertData(String name, String password, Integer threshold) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, password);
        contentValues.put(COL_4, threshold);
        long result = db.insert(TABLE_1, null, contentValues);
        /*se der erro retorna -1 se der bem retorna o valor da linha onde foi inserido*/
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_1, null);
        return res;
    }
}
/*alteraçõesfeitas em "Main2Activity*/