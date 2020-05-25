package com.example.cocktailapps;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonHolder {

    @GET("api/json/v1/1/search.php?")
    Call<ListResult> getDrinks(@Query("s") String name);;
}
