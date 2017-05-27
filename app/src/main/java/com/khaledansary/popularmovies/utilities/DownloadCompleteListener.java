package com.khaledansary.popularmovies.utilities;

import com.khaledansary.popularmovies.Models.Movie;

import java.util.ArrayList;

/**
 * Created by Khaled on 23/02/2017.
 */

public interface DownloadCompleteListener {
    void downloadComplete(ArrayList<Movie> movies);
}
