package com.example.ofirdahan.newsdigest.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;


import com.example.ofirdahan.newsdigest.Models.Story;
import com.example.ofirdahan.newsdigest.R;
import com.example.ofirdahan.newsdigest.UI.WebActivity;

import java.util.List;

public class StoryRecAdapter extends  RecyclerView.Adapter<StoryRecAdapter.ContentViewHolder>{

    private List<Story> mStoriesList;
    int rating = 0;

    public StoryRecAdapter(List<Story> mStoriesList){
        this.mStoriesList = mStoriesList;
    }

    @Override
    public int getItemCount(){
        return mStoriesList.size();
    }

    @Override
    public void onBindViewHolder(ContentViewHolder contentViewHolder, int i ){
        final Story story = mStoriesList.get(i);
        contentViewHolder.itemView.setOnClickListener(new StoryClickListener(story));
        contentViewHolder.mTitle.setText(story.getTitle());
        contentViewHolder.mAuthor.setText(story.getAuthor());
        contentViewHolder.mPoints.setText(story.getPoints());
        setRating(Integer.valueOf(story.getPoints()));
        contentViewHolder.mCreatedAt.setText(story.getCreatedAt());
    }

    private void setRating(Integer storyPoints) {
        if(storyPoints != null){
            int divedPointRating = storyPoints/100;
            System.out.println("Divided Point Rating: "+ divedPointRating + "\n" +
                               "story Points: " + storyPoints + "\n");
            rating = divedPointRating;
        }
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ContentViewHolder(itemView, mStoriesList.get(i));
    }


    public class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mAuthor;
        TextView mPoints;
        TextView mCreatedAt;
        RatingBar mRatingBar;
        Story story;

        public ContentViewHolder(View view, Story story){
            super(view);
            mTitle = (TextView) view.findViewById(R.id.storyTitle);
            mAuthor = (TextView) view.findViewById(R.id.author);
            mPoints = (TextView) view.findViewById(R.id.storyPoints);
            mCreatedAt = (TextView) view.findViewById(R.id.created_at);
            mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            mRatingBar.setRating(rating);
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
            bundle.putString("url", story.getUrl());
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        }
    }

}