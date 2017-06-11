package com.khaledansary.popularmovies.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Khaled on 29/01/2017.
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils  {

    private static boolean isConnected;
    public static boolean getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
    public static String getApiResponse(URL url){

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(6, TimeUnit.SECONDS);
        client.setReadTimeout(6, TimeUnit.SECONDS);
        client.setWriteTimeout(6, TimeUnit.SECONDS);

        Request request = new Request.Builder()
                .url(url)
                .build();
        try{
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("URL not connected: ",""+e);
        }catch (Exception en) {
            System.out.println("End of content" + en);
        }
        return null;
    }

    public static URL buildUrl(String sortValue, String page) {
        URL url = null;
        try {
            Uri builtUri = Uri.parse(Constants.APIConstants.BASE_URL+sortValue).buildUpon()
                    .appendQueryParameter(Constants.APIConstants.API_KEY, "")
                    .appendQueryParameter(Constants.APIConstants.SET_LANGUAGE, "en-US")
                    .appendQueryParameter(Constants.APIConstants.PAGE, page)
                    .build();
            url = new URL(builtUri.toString());
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


}