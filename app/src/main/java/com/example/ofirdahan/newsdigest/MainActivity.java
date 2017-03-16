package com.example.ofirdahan.newsdigest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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
                Story currentStory = storyAdapter.getItem(position);
                Uri storyUri = Uri.parse(currentStory.getUrl());
                Intent webpage = new Intent(Intent.ACTION_VIEW, storyUri);
                startActivity(webpage);
            }
        });

        storiesListView.setAdapter(storyAdapter);
    }
}
