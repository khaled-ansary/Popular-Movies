package com.khaledansary.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.khaledansary.popularmovies.Models.Movie;
import com.khaledansary.popularmovies.adapters.MovieAdapter;
import com.khaledansary.popularmovies.Utils.Constants;
import com.khaledansary.popularmovies.Utils.MovieDataProcess;
import com.khaledansary.popularmovies.Utils.NetworkUtils;
import com.squareup.okhttp.OkHttpClient;
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
    private String filter;

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
        adapter = new MovieAdapter(MainActivity.this, movieList);
        mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        setFilter(Constants.APIConstants.GET_POPULAR);
        if(NetworkUtils.getConnectivityStatus(MainActivity.this)){
            new  MovieQueryTask().execute();
        }else{
            showErrorMessage();
        }

        adapter.notifyDataSetChanged();
    }

    private void showErrorMessage(){
        Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
    }

    private void setFilter(String filter){
        this.filter = filter;
    }

    private String getFilter(){
        return this.filter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.popular:
                setFilter(Constants.APIConstants.GET_POPULAR);
                movieList.clear();
                if(NetworkUtils.getConnectivityStatus(MainActivity.this)){
                    new  MovieQueryTask().execute();
                }else{
                    showErrorMessage();
                }
                break;
            case R.id.highest_rated:
                setFilter(Constants.APIConstants.GET_TOP_RATED);
                movieList.clear();
                if(NetworkUtils.getConnectivityStatus(MainActivity.this)){
                    new  MovieQueryTask().execute();
                }else{
                    showErrorMessage();
                }
                break;
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

            URL url =  NetworkUtils.buildUrl(getFilter(),  Constants.APIConstants.PAGE_NUMBER);
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
            if (movies != null) {
                try {
                    movieList.addAll(MovieDataProcess.getMovies(movies));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showErrorMessage();
            }
            adapter.notifyDataSetChanged();
        }
    }
}
