package com.example.cocktailapps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.example.cocktailsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {



    DBHelper dbHelper;

    ArrayList<Drinks> drinksList = new ArrayList<Drinks>();
    private  RecyclerView myrecycler;
    private RecyclerAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cocktail App");
        toolbar.setTitleTextColor(Color.BLACK);


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchActivity();
            }
        });






        dbHelper = new DBHelper(this);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBHelper.COCKTAILS_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_id);
            int nameIndex = cursor.getColumnIndex(DBHelper.NAME);
            int alcoholicIndex = cursor.getColumnIndex(DBHelper.ALCOHOLIC);
            int glassIndex = cursor.getColumnIndex(DBHelper.GLASS);

            int r1 = cursor.getColumnIndex(DBHelper.INGRADIENT_1);
            int r2 = cursor.getColumnIndex(DBHelper.INGRADIENT_2);
            int r3 = cursor.getColumnIndex(DBHelper.INGRADIENT_3);
            int r4 = cursor.getColumnIndex(DBHelper.INGRADIENT_4);

            int f1 = cursor.getColumnIndex(DBHelper.MEASURE_1);
            int f2 = cursor.getColumnIndex(DBHelper.MEASURE_2);
            int f3 = cursor.getColumnIndex(DBHelper.MEASURE_3);
            int f4 = cursor.getColumnIndex(DBHelper.MEASURE_4);
            int instructions = cursor.getColumnIndex(DBHelper.INSTRUCTION);
            int urlindex = cursor.getColumnIndex(DBHelper.URL);

            do {



                drinksList.add(new Drinks((String.valueOf(cursor.getInt(idIndex))),cursor.getString(nameIndex),cursor.getString(alcoholicIndex),cursor.getString(glassIndex),cursor.getString(r1),cursor.getString(r2),cursor.getString(r3),cursor.getString(r4),cursor.getString(f1),cursor.getString(f2),cursor.getString(f3),cursor.getString(f4),cursor.getString(instructions),cursor.getString(urlindex)));
            } while (cursor.moveToNext());


        } else
            Log.d("mLog","0 rows");
        cursor.close();


        myrecycler = (RecyclerView) findViewById(R.id.recycler_viewhome);
        myAdapter = new RecyclerAdapter(this, drinksList);

        myrecycler.setLayoutManager(new GridLayoutManager(this, 2));
        myrecycler.setAdapter(myAdapter);

        if(drinksList.isEmpty()){
            TextView textView = findViewById(R.id.text_empty);
            textView.setVisibility(View.VISIBLE);
            myrecycler.setVisibility(View.INVISIBLE);
        }
    }


    public void openSearchActivity(){
        Intent intent = new Intent(this, SearchView.class);
        startActivityForResult(intent,RESULT_OK);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}