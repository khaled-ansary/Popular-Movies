package com.khaledansary.popularmovies.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.khaledansary.popularmovies.Models.Movie;
import com.khaledansary.popularmovies.R;

import java.util.List;

/**
 * Created by Khaled on 29/01/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{

    private Context mContext;
    private List<Movie> movieList;
    private Movie movie;

    public class MovieHolder extends RecyclerView.ViewHolder {
        public ImageView moviePoster;

        public MovieHolder(View view){
            super(view);
            moviePoster = (ImageView) view.findViewById(R.id.movie_poster);
        }
    }

    public MovieAdapter(Context context, List<Movie> movieList){
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View movieItemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_items, viewGroup, false);

        return new MovieHolder(movieItemView);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieHolder holder, int position) {
        //TODO: load image
        //TODO: set details data
        //TODO: putExtra for send data to other activity
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
