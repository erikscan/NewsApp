package com.example.erik.newsapp.util;


import android.text.TextUtils;
import android.util.Log;

import com.example.erik.newsapp.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();
    private static final int TIMEOUT = 15000;
    private static final int OK_CODE = 200;
    private static final int DATE_START = 0;
    private static final int DATE_END = 10;
    private QueryUtils() {
    }

    public static List<News> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "HTTP request failed ", e);
        }
        return jsonToList(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Creating URL failed ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(TIMEOUT);
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == OK_CODE) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Retrieving JSON falied ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    private static List<News> jsonToList(String newsJSON) {
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }
        List<News> newsList = new ArrayList<News>();
        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONObject responseArray = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = responseArray.getJSONArray("results");
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject currentResult = resultsArray.getJSONObject(i);
                String newsUrl = currentResult.getString("webUrl");
                String newsTitle = currentResult.getString("webTitle");
                String newsSection = currentResult.getString("sectionName");
                String newsDate = currentResult.getString("webPublicationDate").substring(DATE_START, DATE_END);
                News news = new News(newsUrl, newsTitle, newsSection, newsDate);
                newsList.add(news);
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results. ", e);
        }
        return newsList;
    }
}