package com.khaledansary.popularmovies.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.khaledansary.popularmovies.Holders.MovieHolder;
import com.khaledansary.popularmovies.Models.Movie;
import com.khaledansary.popularmovies.R;

import java.util.List;

/**
 * Created by Khaled on 29/01/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<Movie> movieList;
    private Movie movie;

    private final int MOVIE = 0;


    public MovieAdapter(Context context, List<Movie> movieList){
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View movieHolder = inflater.inflate(R.layout.movie_items, viewGroup, false);
        viewHolder = new MovieHolder(movieHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        MovieHolder movieHolder = (MovieHolder) viewHolder;
        configureMovieHolder(movieHolder, position);
    }

    private void configureMovieHolder(MovieHolder movieHolder, int position){

        final Movie movie = (Movie) movieList.get(position);
        if(movie != null){


            Glide.with(mContext).load(movie.getPoster_path()).into(movieHolder.moviePoster);

            movieHolder.moviePoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     Toast.makeText(mContext, "Picture click"+ movie.getId(), Toast.LENGTH_SHORT).show();
                    //Intent post_intent = new Intent(mContext, PageActivity.class);
                    //post_intent.putExtra("post_title",post.getPost_title());

                    //mContext.startActivity(post_intent);
                    //showPopupMenu(holder.thumbnail);
                    // Toast.makeText(view, "imageIV onClick at" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
