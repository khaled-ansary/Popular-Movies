package com.khaledansary.popularmovies.adapters;



import com.bumptech.glide.Glide;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khaledansary.popularmovies.DetailsActivity;
import com.khaledansary.popularmovies.Holders.MovieHolder;
import com.khaledansary.popularmovies.Models.Movie;
import com.khaledansary.popularmovies.R;
import java.util.List;

/**
 * Created by Khaled
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_items,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(movies.get(position).getPoster_path()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent movie_details = new Intent(context, DetailsActivity.class);
                movie_details.putExtra("id", movies.get(position).getId());
                movie_details.putExtra("title", movies.get(position).getTitle());
                movie_details.putExtra("poster_path", movies.get(position).getPoster_path());
                movie_details.putExtra("overview", movies.get(position).getOverview());
                movie_details.putExtra("release_date", movies.get(position).getRelease_date());
                movie_details.putExtra("vote_average", movies.get(position).getVote_average());
                context.startActivity(movie_details);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }
}
