package com.example.awsdemo.api.model.requests;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class Request<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    // Attributes
    // ------------------------------------------------------
    protected String endpoint;

    // Constructors
    // ------------------------------------------------------
    protected Request(String url) {
        super();
        this.endpoint = url;
    }

    // Behaviour
    // ------------------------------------------------------
    protected abstract void onSuccess(String response);
    protected abstract void onError(String error);

    // Handlers
    // ------------------------------------------------------
    @Override
    protected Result doInBackground(Params... params) {
        try {
            URL url = new URL(this.endpoint);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            int responseCode = urlConnection.getResponseCode();

            String response = readStream(urlConnection.getInputStream());
            if(responseCode == HttpURLConnection.HTTP_OK) {
                Log.println(Log.DEBUG, "Http response.", response);
                onSuccess(response);
            } else {
                Log.println(Log.ERROR, "Http error.", response);
                onError(response);
            }
        } catch (IOException e) {
            Log.println(Log.ERROR, "Http exception.", e.getLocalizedMessage());
            onError(e.getLocalizedMessage());
        }
        return null;
    }

    protected String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

}
