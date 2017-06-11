package com.khaledansary.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        TextView movie_title = (TextView) findViewById(R.id.title);
        ImageView movie_poster = (ImageView) findViewById(R.id.poster);
        TextView overview = (TextView) findViewById(R.id.overview);
        TextView release_date = (TextView) findViewById(R.id.release_date);
        TextView vote_average = (TextView) findViewById(R.id.average_rating);

        movie_title.setText(bundle.getString("title"));
        overview.setText(bundle.getString("overview"));
        release_date.setText(bundle.getString("release_date"));

        vote_average.setText(String.format("%.2f", bundle.getDouble("vote_average")) + getString(R.string.highest_rate));
        try {
            Glide.with(this).load(bundle.getString("poster_path")).into(movie_poster);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
