package com.example.cocktailapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.cocktailsapp.R;

public class InfoDrink extends AppCompatActivity{

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        String id = getIntent().getStringExtra("id");


        int  k_id = Integer.parseInt(id);

        String name = getIntent().getStringExtra("Name");
        String alcohol = getIntent().getStringExtra("Alcohol");
        String glass = getIntent().getStringExtra("Glass");
        String Ingradient1 = getIntent().getStringExtra("Ingradient1");
        String Ingradient2 = getIntent().getStringExtra("Ingradient2");
        String Ingradient3 = getIntent().getStringExtra("Ingradient3");
        String Ingradient4 = getIntent().getStringExtra("Ingradient4");
        String Measure1 = getIntent().getStringExtra("Measure1");
        String Measure2 = getIntent().getStringExtra("Measure2");
        String Measure3 = getIntent().getStringExtra("Measure3");
        String Measure4 = getIntent().getStringExtra("Measure4");
        String Instruction = getIntent().getStringExtra("Instruction");
        String url = getIntent().getStringExtra("URL");

        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name);
        toolbar.setTitleTextColor(Color.BLACK);

        TextView text1 = findViewById(R.id.t1);
        text1.setText(name);

        TextView text2 = findViewById(R.id.t2);
        text2.setText(alcohol);

        TextView text3 = findViewById(R.id.t3);
        text3.setText(glass);

        TextView text4 = findViewById(R.id.t4);
        text4.setText(Measure1);

        TextView text5 = findViewById(R.id.t5);
        text5.setText(Measure2);

        TextView text6 = findViewById(R.id.t6);
        text6.setText(Measure3);

        TextView text7 = findViewById(R.id.t7);
        text7.setText(Measure4);

        TextView text8 = findViewById(R.id.t8);
        text8.setText(Ingradient1);

        TextView text9 = findViewById(R.id.t9);
        text9.setText(Ingradient2);

        TextView text10 = findViewById(R.id.t10);
        text10.setText(Ingradient3);

        TextView text11 = findViewById(R.id.t11);
        text11.setText(Ingradient4);

        TextView text12 = findViewById(R.id.t12);
        text12.setText(Instruction);

        ImageView imageView = findViewById(R.id.image_info);

        Glide.with(this)
                .load(url)
                .into(imageView);

        dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        Cursor cursor = database.query(DBHelper.COCKTAILS_TABLE, null, null, null, null, null, null);

        boolean f = true;

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_id);
            do {
                if(cursor.getInt(idIndex)== k_id){
                    f=false;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        if(f) {
            contentValues.put(DBHelper.KEY_id, k_id);
            contentValues.put(DBHelper.NAME, name);
            contentValues.put(DBHelper.ALCOHOLIC, alcohol);
            contentValues.put(DBHelper.GLASS, glass);
            contentValues.put(DBHelper.INGRADIENT_1, Ingradient1);
            contentValues.put(DBHelper.INGRADIENT_2, Ingradient2);
            contentValues.put(DBHelper.INGRADIENT_3, Ingradient3);
            contentValues.put(DBHelper.INGRADIENT_4, Ingradient4);
            contentValues.put(DBHelper.MEASURE_1, Measure1);
            contentValues.put(DBHelper.MEASURE_2, Measure2);
            contentValues.put(DBHelper.MEASURE_3, Measure3);
            contentValues.put(DBHelper.MEASURE_4, Measure4);
            contentValues.put(DBHelper.INSTRUCTION, Instruction);
            contentValues.put(DBHelper.URL, url);

            database.insert(DBHelper.COCKTAILS_TABLE, null, contentValues);
        }
        dbHelper.close();

    }
}