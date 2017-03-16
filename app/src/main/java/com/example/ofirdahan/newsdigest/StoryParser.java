package com.example.ofirdahan.newsdigest;

import android.util.Log;
import android.widget.Toast;

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

    public static final String SAMPLE_JSON_RESPONSE = "{\n" +
            "\t\"hits\": [{\n" +
            "\t\t\"created_at\": \"2017-03-15T16:16:22.000Z\",\n" +
            "\t\t\"title\": \"GitLab acquires Gitter, will open-source the code\",\n" +
            "\t\t\"url\": \"http://venturebeat.com/2017/03/15/gitlab-acquires-software-chat-startup-gitter-will-open-source-the-code/\",\n" +
            "\t\t\"author\": \"marcinkuzminski\",\n" +
            "\t\t\"points\": 739,\n" +
            "\t\t\"story_text\": null,\n" +
            "\t\t\"comment_text\": null,\n" +
            "\t\t\"num_comments\": 237,\n" +
            "\t\t\"story_id\": null,\n" +
            "\t\t\"story_title\": null,\n" +
            "\t\t\"story_url\": null,\n" +
            "\t\t\"parent_id\": null,\n" +
            "\t\t\"created_at_i\": 1489594582,\n" +
            "\t\t\"_tags\": [\"story\", \"author_marcinkuzminski\", \"story_13877156\", \"front_page\"],\n" +
            "\t\t\"objectID\": \"13877156\"\n" +
            "\t}]\n" +
            "}";

    private StoryParser() throws IOException {}

    public static List<Story> jsonParsedStories() throws IOException{
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Hits> jsonAdapter = moshi.adapter(Hits.class);
        SampleJSON sampleJSON = new SampleJSON();

        Hits hits = jsonAdapter.fromJson(sampleJSON.getSampleJsonResponse());

        return hits.getHits();
    }


    public static ArrayList<Story> parseStories(){

        ArrayList<Story> stories = new ArrayList<>();

        try{
            JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray hits = root.getJSONArray("hits");

            Date dateObj;

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

//                stories.add(new Story(dateToDisplay,title,author,url,points));
            }
        } catch (JSONException e) {
            Log.e("Story Parser", "Something broke while trying to parse the JSON text", e);
        }
        return stories;
    }
}
