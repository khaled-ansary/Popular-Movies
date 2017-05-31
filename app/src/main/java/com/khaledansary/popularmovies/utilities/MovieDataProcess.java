package com.khaledansary.popularmovies.utilities;

import com.khaledansary.popularmovies.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Khaled on 24/02/2017.
 */

public class MovieDataProcess {

    public static ArrayList<Movie> getMovies (String response) throws JSONException{
        if (null == response) {
            return new ArrayList<>();
        }
        JSONArray jsonArray = new JSONArray(response);
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject object = jsonArray.getJSONObject(i);
            Movie movie = new Movie();
            movie.setId(object.getInt("id"));
            movie.setPoster_path(object.getString("poster_path"));
            movies.add(movie);

        }

        return movies;
    }
}
