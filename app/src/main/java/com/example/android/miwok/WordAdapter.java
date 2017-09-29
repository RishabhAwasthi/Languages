package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by awasthi's on 9/27/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Activity context, ArrayList<Word> words) {

        super(context,0, words);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView spanish_text = (TextView) listItemView.findViewById(R.id.spanish_text);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        spanish_text.setText(currentWord.getSpanish());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView eng_text = (TextView) listItemView.findViewById(R.id.eng_text);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        eng_text.setText(currentWord.getEng());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
       ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to
        if(currentWord.hasimage()) {
            iconView.setImageResource(currentWord.getImageId());
            iconView.setVisibility(View.VISIBLE);
        }
        else
            iconView.setVisibility(View.GONE);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}


