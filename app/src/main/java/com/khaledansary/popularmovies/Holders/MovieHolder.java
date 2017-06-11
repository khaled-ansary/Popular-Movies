package com.khaledansary.popularmovies.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.khaledansary.popularmovies.R;

/**
 * Created by Khaled on 31/05/2017.
 */

public class MovieHolder extends RecyclerView.ViewHolder {
    public ImageView thumbnail;
    public MovieHolder(View view){
        super(view);
        thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
    }
}
