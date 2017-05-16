package com.example.ofirdahan.newsdigest.Adapters;

import android.content.Intent;
import android.os.Bundle;

import com.example.ofirdahan.newsdigest.Models.Story;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ofirdahan on 3/21/17.
 */

public interface RetrofitInterface {

    @GET("search?tags=front_page")
    Call<Story> listOfHits();

    @GET("search?query=")
    Call<Story> queryResults(
            @Query("query") String query
    );


}



