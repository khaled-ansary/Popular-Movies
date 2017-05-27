/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.khaledansary.popularmovies.utilities;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Khaled on 29/01/2017.
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils extends AsyncTask<String, Void, String> {


    private  DownloadCompleteListener mDownloadCompleteListener;

    public NetworkUtils(DownloadCompleteListener downloadCompleteListener) {
        this.mDownloadCompleteListener = downloadCompleteListener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return getResponseFromHttpUrl(buildUrl(Constants.APIConstants.API_KEY));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static URL buildUrl(String apiKey) {
        Uri builtUri = Uri.parse(Constants.APIConstants.BASE_URL).buildUpon()
                .appendQueryParameter(Constants.APIConstants.APP_KEY_QUERY_PARAM, apiKey)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            inputStream = urlConnection.getInputStream();
            return convertToString(inputStream);
        } finally {
            if (inputStream !=null){
                inputStream.close();
            }
            urlConnection.disconnect();
        }
    }

    private static String convertToString(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return new String(total);
    }


}