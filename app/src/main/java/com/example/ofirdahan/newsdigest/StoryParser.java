package com.example.ofirdahan.newsdigest;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ofirdahan on 3/14/17.
 */

public class StoryParser {

    //TODO: Import more examples for list
    private static final String SAMPLE_JSON_RESPONSE = "{\"hits\":[{" +
            "\"created_at_i\":\"1489500616\",\"title\":\"Keep the Internet Open\",\"url\":\"http://blog.samaltman.com/keep-the-internet-open\",\"author\":\"firloop\",\"points\":717}" + "]}";

    private StoryParser(){}

    public static ArrayList<Story> parseStories(){

        ArrayList<Story> stories = new ArrayList<>();



        try{
            JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray hits = root.getJSONArray("hits");

            Date dateObj;

            //TODO: Fix date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);

            for(int i=0; i < hits.length(); i++){
                JSONObject story = hits.getJSONObject(i);

                long createdAt = story.getLong("created_at_i");
                dateObj = new Date(createdAt);
                String dateToDisplay = dateFormat.format(dateObj);

                String title = story.getString("title");
                String url = story.getString("url");
                String author = story.getString("author");
                int points = story.getInt("points");

                stories.add(new Story(dateToDisplay,title,author,url,points));
            }
        } catch (JSONException e) {
            Log.e("Story Parser", "Something broke while trying to parse the JSON text", e);
        }
        return stories;
    }
}
