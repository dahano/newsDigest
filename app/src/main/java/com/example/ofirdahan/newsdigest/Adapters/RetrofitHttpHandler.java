package com.example.ofirdahan.newsdigest.Adapters;

import com.example.ofirdahan.newsdigest.Models.Hits;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by ofirdahan on 3/21/17.
 */

public interface RetrofitHttpHandler {
    @GET("search?tags=front_page")
    Call<List<Hits>> listOfHits();

}



