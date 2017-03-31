package com.example.ofirdahan.newsdigest.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.ofirdahan.newsdigest.Adapters.ApiClient;
import com.example.ofirdahan.newsdigest.Adapters.RetrofitInterface;
import com.example.ofirdahan.newsdigest.Adapters.StoryAdapter;
import com.example.ofirdahan.newsdigest.Adapters.StoryParser;
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
    private ListView storiesListView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNetworkCall();

        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setIndeterminate(true);

        setStories(mStories);

    }

    private void setNetworkCall() {
        RetrofitInterface retrofitInterface =
                ApiClient.getClient().create(RetrofitInterface.class);

        Call<Story> call = retrofitInterface.listOfHits();

        call.enqueue(new Callback<Story>() {

            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                mStories = response.body().getHits();
                Log.d(TAG, "Number of hits: " + mStories.size());
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void setStories(List<Story> stories){

        final StoryAdapter storyAdapter = new StoryAdapter(this, stories);
        storiesListView = (ListView) findViewById(R.id.list);

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


