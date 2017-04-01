package com.example.ofirdahan.newsdigest.Adapters;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by ofirdahan on 3/24/17.
 */

public class ApiClient {

    public static final String BASE_URL = "https://hn.algolia.com/api/v1/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
