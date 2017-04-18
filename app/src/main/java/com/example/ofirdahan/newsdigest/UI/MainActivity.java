package com.example.ofirdahan.newsdigest.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ofirdahan.newsdigest.Adapters.ApiClient;
import com.example.ofirdahan.newsdigest.Adapters.RetrofitInterface;
import com.example.ofirdahan.newsdigest.Adapters.StoryRecAdapter;
import com.example.ofirdahan.newsdigest.Models.Story;
import com.example.ofirdahan.newsdigest.R;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        setNetworkCall();
    }

    private void setNetworkCall() {
        mProgressBar.setVisibility(View.VISIBLE);

        RetrofitInterface retrofitInterface =
                ApiClient.getClient().create(RetrofitInterface.class);
        Call<Story> call = retrofitInterface.listOfHits();
        call.enqueue(new Callback<Story>() {

            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                setStories(response.body().getHits());
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void setStories(List<Story> stories){
        final StoryRecAdapter storyRecAdapter = new StoryRecAdapter(stories);
        mRecyclerView.setAdapter(storyRecAdapter);


    }
}


