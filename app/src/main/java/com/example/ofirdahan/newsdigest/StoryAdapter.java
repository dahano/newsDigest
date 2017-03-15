package com.example.ofirdahan.newsdigest;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ofirdahan on 3/14/17.
 */

public class StoryAdapter extends ArrayAdapter<Story> {

    public StoryAdapter(Activity context, ArrayList<Story> stories){
        super(context,0,stories);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        View listItemView = view;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }

        Story currentStory = getItem(position);

        TextView storyPoints = (TextView) listItemView.findViewById(R.id.storyPoints);
        storyPoints.setText(String.valueOf(currentStory.getPoints()));

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentStory.getTitle());

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(currentStory.getAuthor());

        TextView createdAt = (TextView) listItemView.findViewById(R.id.created_at);
        createdAt.setText(currentStory.getCreatedAt());

        return listItemView;
    }
}
