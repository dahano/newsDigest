package com.example.ofirdahan.newsdigest.UI;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.ofirdahan.newsdigest.Adapters.ApiClient;
import com.example.ofirdahan.newsdigest.Adapters.RetrofitInterface;
import com.example.ofirdahan.newsdigest.Adapters.StoryRecAdapter;
import com.example.ofirdahan.newsdigest.Models.Story;
import com.example.ofirdahan.newsdigest.R;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static String QUERY;
    private RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.orange));
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setNetworkCall();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setNetworkCall();
            }
        });
    }

    private void searchForQuery(){
        RetrofitInterface retrofitInterface =
                ApiClient.getClient().create(RetrofitInterface.class);
        Call<Story> call = retrofitInterface.queryResults(QUERY);
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



    private void setNetworkCall() {
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
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                QUERY = query;
                searchForQuery();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}


