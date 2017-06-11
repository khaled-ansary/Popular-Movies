package com.khaledansary.popularmovies.Utils;

import android.util.Log;

import com.khaledansary.popularmovies.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khaled on 24/02/2017.
 */

public class MovieDataProcess {

    public static List<Movie> getMovies (String response) throws JSONException{
        Log.d("response",response);
        if (response == null ) {
            return new ArrayList<>();
        }
        JSONObject moviesData = new JSONObject(response);
        JSONArray results = moviesData.getJSONArray("results");
        ArrayList<Movie> movies = new ArrayList<>();
        Log.d("Results", results.toString());
        for (int i = 0; i < results.length(); i++) {

            JSONObject object = results.getJSONObject(i);
            Movie movie = new Movie();
            movie.setId(object.getInt("id"));
            movie.setTitle(object.getString("title"));
            movie.setPoster_path(Constants.APIConstants.IMAGE_URL+Constants.APIConstants.IMAGE_MEDIUM_SIZE+object.getString("poster_path"));
            movie.setOverview(object.getString("overview"));
            movie.setRelease_date(object.getString("release_date"));
            movie.setVote_average(object.getDouble("vote_average"));
            movies.add(movie);
        }

        return movies;
    }
}
