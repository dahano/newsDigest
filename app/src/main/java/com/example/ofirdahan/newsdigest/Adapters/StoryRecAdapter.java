package com.example.ofirdahan.newsdigest.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ofirdahan.newsdigest.Models.Story;
import com.example.ofirdahan.newsdigest.R;
import com.example.ofirdahan.newsdigest.UI.WebActivity;

import java.util.List;

public class StoryRecAdapter extends  RecyclerView.Adapter<StoryRecAdapter.ContentViewHolder>{

    private List<Story> mStoriesList;


    public StoryRecAdapter(List<Story> mStoriesList){
        this.mStoriesList = mStoriesList;
    }

    @Override
    public int getItemCount(){
        return mStoriesList.size();
    }

    @Override
    public void onBindViewHolder(ContentViewHolder contentViewHolder, int i ){
        Story story = mStoriesList.get(i);
        contentViewHolder.mTitle.setText(story.getTitle());
        contentViewHolder.mAuthor.setText(story.getAuthor());
        contentViewHolder.mPoints.setText(story.getPoints());
        contentViewHolder.mCreatedAt.setText(story.getCreatedAt().toString());
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        Story story = mStoriesList.get(i);

        itemView.setOnClickListener(new StoryClickListener(story));
        return new ContentViewHolder(itemView, mStoriesList.get(i));
    }


    public class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mAuthor;
        TextView mPoints;
        TextView mCreatedAt;
        Story story;

        public ContentViewHolder(View view, Story story){
            super(view);
            mTitle = (TextView) view.findViewById(R.id.storyTitle);
            mAuthor = (TextView) view.findViewById(R.id.author);
            mPoints = (TextView) view.findViewById(R.id.storyPoints);
            mCreatedAt = (TextView) view.findViewById(R.id.created_at);
            this.story = story;
        }
    }

    public static class StoryClickListener implements View.OnClickListener {

        Story story;

        public StoryClickListener(Story story) {
            this.story = story;
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(v.getContext(), WebActivity.class);
            //Intent intent = WebActivity.getWebActivityIntent(v.getContext(), story.getUrl());
            bundle.putString("url", story.getUrl());
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        }
    }
}