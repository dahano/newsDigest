package com.example.ofirdahan.newsdigest.UI;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ofirdahan.newsdigest.Adapters.StoryAdapter;
import com.example.ofirdahan.newsdigest.Adapters.StoryParser;
import com.example.ofirdahan.newsdigest.Models.Story;
import com.example.ofirdahan.newsdigest.R;

import java.io.IOException;
import java.util.List;

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
