package com.example.ofirdahan.newsdigest.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ofirdahan.newsdigest.Adapters.StoryAdapter;
import com.example.ofirdahan.newsdigest.Adapters.StoryParser;
import com.example.ofirdahan.newsdigest.Models.Story;
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
                Bundle bundle = new Bundle();
                if (currentStory != null) {
                    bundle.putString("url", currentStory.getUrl());
                }
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });

        storiesListView.setAdapter(storyAdapter);
    }

}


