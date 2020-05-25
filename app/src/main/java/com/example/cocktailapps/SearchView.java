package com.example.cocktailapps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.cocktailsapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchView extends AppCompatActivity {


    public JsonHolder jsonHolder;
    private TextView textViewResult;
    private RecyclerView myrecycler;
    private RecyclerAdapter myAdapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(Color.BLACK);

        textView = findViewById(R.id.text_start);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myrecycler = (RecyclerView) findViewById(R.id.recycler_view123);

        jsonHolder =  retrofit.create(JsonHolder.class);

        EditText editText = findViewById(R.id.edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    getDrinks(s.toString());
                    myrecycler.setVisibility(View.VISIBLE);

                }catch (Exception e){

                }
            }
        });



    }

    public void getDrinks(String s) {

        Call<ListResult> call = jsonHolder.getDrinks(s);

        call.enqueue(new Callback<ListResult>() {
            @Override
            public void onResponse(Call<ListResult> call, Response<ListResult> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("code:" + response.code());
                    return;
                }

                ListResult drinks = response.body();

                try{
                    drinks.getDrinksList().size();
                }catch (Exception e){
                    myrecycler.setVisibility(View.INVISIBLE);
                    textView.setText("No cocktails found");
                }

                myAdapter = new RecyclerAdapter(SearchView.this, drinks.getDrinksList());

                myrecycler.setLayoutManager(new GridLayoutManager(SearchView.this, 2));
                myrecycler.setAdapter(myAdapter);

                setResult(RESULT_CANCELED);
            }

            @Override
            public void onFailure(Call<ListResult> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, HomeActivity.class);
        finish();
        this.startActivity(i);
    }
}







