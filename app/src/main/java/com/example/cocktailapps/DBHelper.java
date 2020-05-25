package com.example.cocktailapps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public   static  final int DATABASE_VERSION = 1;
    public   static  final String DATABASE_NAME = "CocktailDB";
    public   static  final String COCKTAILS_TABLE = "Drinks";

    public   static  final String KEY_id = "_id";
    public   static  final String NAME = "Name";
    public   static  final String ALCOHOLIC = "Alcoholic";
    public   static  final String GLASS = "Glass";
    public   static  final String INGRADIENT_1 = "Ingradient1";
    public   static  final String INGRADIENT_2 = "Ingradient2";
    public   static  final String INGRADIENT_3 = "Ingradient3";
    public   static  final String INGRADIENT_4 = "Ingradient4";
    public   static  final String MEASURE_1 = "Measure1";
    public   static  final String MEASURE_2 = "Measure2";
    public   static  final String MEASURE_3 = "Measure3";
    public   static  final String MEASURE_4 = "Measure4";
    public   static  final String INSTRUCTION = "Instructions";
    public   static  final String URL = "URL";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "CREATE TABLE "+ COCKTAILS_TABLE +" ( "+KEY_id + " integer primary key," + NAME +" text," + ALCOHOLIC + " text," + GLASS + " text," + INGRADIENT_1 + " text," + INGRADIENT_2 + " text," + INGRADIENT_3 + " text," + INGRADIENT_4 + " text," +MEASURE_1 + " text," + MEASURE_2 + " text," + MEASURE_3 + " text," +MEASURE_4 + " text," + INSTRUCTION + " text," + URL + " text)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
