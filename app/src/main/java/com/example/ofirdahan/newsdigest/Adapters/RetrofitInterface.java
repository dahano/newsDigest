package com.example.ofirdahan.newsdigest.Adapters;

import com.example.ofirdahan.newsdigest.Models.Story;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ofirdahan on 3/21/17.
 */

public interface RetrofitInterface {
    @GET("search?tags=front_page")
    Call<Story> listOfHits();

}



