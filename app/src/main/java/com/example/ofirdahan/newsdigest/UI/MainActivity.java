package com.example.ofirdahan.newsdigest.UI;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ofirdahan.newsdigest.Adapters.StoryAdapter;
import com.example.ofirdahan.newsdigest.Adapters.StoryParser;
import com.example.ofirdahan.newsdigest.Models.Story;
import com.example.ofirdahan.newsdigest.Models.WebViewActivity;
import com.example.ofirdahan.newsdigest.R;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
     WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Story> stories = null;

        try {
            stories = StoryParser.jsonParsedStories();
        } catch (IOException e) {
            e.printStackTrace();
        }


        final ListView storiesListView = (ListView) findViewById(R.id.list);
        final StoryAdapter storyAdapter = new StoryAdapter(this, stories);

        storiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Story currentStory = storyAdapter.getItem(position);
                setContentView(R.layout.web_view_activity);
                mWebView = (WebView) findViewById(R.id.web_view);
                mWebView.setWebViewClient(new WebViewClient());
                if (currentStory != null) {
                    mWebView.loadUrl(currentStory.getUrl());
                }
                if(getSupportActionBar() != null){
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }

            }

        });
        storiesListView.setAdapter(storyAdapter);
    }
}
