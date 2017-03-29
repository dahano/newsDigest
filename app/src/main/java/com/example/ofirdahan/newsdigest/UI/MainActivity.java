package com.example.ofirdahan.newsdigest.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ofirdahan.newsdigest.Adapters.ApiClient;
import com.example.ofirdahan.newsdigest.Adapters.RetrofitInterface;
import com.example.ofirdahan.newsdigest.Adapters.StoryAdapter;
import com.example.ofirdahan.newsdigest.Adapters.StoryParser;
import com.example.ofirdahan.newsdigest.Models.Hits;
import com.example.ofirdahan.newsdigest.Models.SampleJSON;
import com.example.ofirdahan.newsdigest.Models.Story;
import com.example.ofirdahan.newsdigest.R;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Story> mStories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitInterface retrofitInterface =
                ApiClient.getClient().create(RetrofitInterface.class);

        Call<Story> call = retrofitInterface.listOfHits();

        call.enqueue(new Callback<Story>() {

            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                mStories = response.body().getHits();
                Log.d(TAG, "Number of hits: " + mStories.size());
                Log.d(TAG, "Response Code: " + response.code());

            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        List<Story> stories = null;

        try {
            stories = StoryParser.jsonParsedStories();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Log.d(TAG, "Number of mStories: " + mStories.size());

        final ListView storiesListView = (ListView) findViewById(R.id.list);
        final StoryAdapter storyAdapter = new StoryAdapter(this, stories);
        storiesListView.setAdapter(storyAdapter);

        storiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Story currentStory = storyAdapter.getItem(position);
                Bundle bundle = new Bundle();
                if (currentStory != null) {
                    bundle.putString("url", currentStory.getUrl());
                }
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

        });

    }

}


