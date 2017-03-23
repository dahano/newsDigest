package com.example.ofirdahan.newsdigest.Adapters;

import android.util.Log;

import com.example.ofirdahan.newsdigest.Models.Hits;
import com.example.ofirdahan.newsdigest.Models.Story;
import com.example.ofirdahan.newsdigest.Models.SampleJSON;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ofirdahan on 3/14/17.
 */

public class StoryParser {


    private StoryParser() throws IOException {}

    public static List<Story> jsonParsedStories() throws IOException{
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Hits> jsonAdapter = moshi.adapter(Hits.class);
        SampleJSON sampleJSON = new SampleJSON();
        Hits hits = jsonAdapter.fromJson(sampleJSON.getSampleJsonResponse());

        return hits.getHits();
    }
}
