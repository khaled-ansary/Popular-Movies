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
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by Khaled on 29/01/2017.
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils  {

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
            // Log.d("Response code: ",""+response);
            return response.body().string();
            // Log.d("post List size:: ",""+postList.size());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("URL not connected: ",""+e);
        }catch (Exception en) {
            System.out.println("End of content" + en);
        }
        return null;
    }

    public static URL buildUrl(String sortValue, String page) {

        Uri builtUri = null; URL url = null;
        if(sortValue.equals(Constants.APIConstants.GET_POPULAR)){
            builtUri = Uri.parse(Constants.APIConstants.BASE_URL+Constants.APIConstants.GET_POPULAR).buildUpon()
                    .appendQueryParameter(Constants.APIConstants.API_KEY, "")
                    .appendQueryParameter(Constants.APIConstants.SET_LANGUAGE, "en-US")
                    .appendQueryParameter(Constants.APIConstants.PAGE, page)
                    .build();
        }else if(sortValue.equals(Constants.APIConstants.GET_TOP_RATED)){
             builtUri = Uri.parse(Constants.APIConstants.BASE_URL+Constants.APIConstants.GET_TOP_RATED).buildUpon()
                     .appendQueryParameter(Constants.APIConstants.API_KEY, "")
                    .appendQueryParameter(Constants.APIConstants.SET_LANGUAGE, "en-US")
                    .appendQueryParameter(Constants.APIConstants.PAGE, page)
                    .build();
        }
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}