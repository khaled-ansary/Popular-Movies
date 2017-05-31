package com.khaledansary.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.khaledansary.popularmovies.Models.Movie;
import com.khaledansary.popularmovies.adapters.MovieAdapter;
import com.khaledansary.popularmovies.utilities.Constants;
import com.khaledansary.popularmovies.utilities.MovieDataProcess;
import com.khaledansary.popularmovies.utilities.NetworkUtils;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Movie>  movieList;
    private ProgressBar progressBar;
    private GridLayoutManager mLayoutManager;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                    return 1;
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        new  MovieQueryTask().execute();
        adapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void showErrorMessage(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.toolbar);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelectedId = item.getItemId();
        if(menuItemSelectedId == R.id.menu_item){
            Context context = MainActivity.this;
            String message = "popular movies clicked";
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    public class MovieQueryTask extends AsyncTask<URL, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {

            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(6, TimeUnit.SECONDS);
            client.setReadTimeout(6, TimeUnit.SECONDS);
            client.setWriteTimeout(6, TimeUnit.SECONDS);

            URL url =  NetworkUtils.buildUrl(Constants.APIConstants.BASE_URL, "1");
            String movieSearchResults = null;
            try {
                movieSearchResults = NetworkUtils.getApiResponse(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return movieSearchResults;
        }

        @Override
        protected void onPostExecute(String movies) {
            progressBar.setVisibility(View.INVISIBLE);
            if (movies != null && !movies.equals("")) {
                try {
                   movieList = MovieDataProcess.getMovies(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                showErrorMessage();
            }
        }
    }
}
