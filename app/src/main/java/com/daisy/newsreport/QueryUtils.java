package com.daisy.newsreport;

import android.util.Log;

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
import java.util.ArrayList;

/**
 * Created by macmini2 on 07/11/2016.
 */

public final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    public QueryUtils() {
    }

    public static ArrayList<News> fetchNewsData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return extractNewsData(jsonResponse);
    }

    private static ArrayList<News> extractNewsData(String jsonResponse) {
        ArrayList<News> news = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            Log.v(LOG_TAG, baseJsonResponse.toString());
            JSONObject responseObj = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = responseObj.getJSONArray("results");
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject resultObj = resultsArray.getJSONObject(i);
                String newsTitle = resultObj.optString("webTitle");
                String sectionName = resultObj.optString("sectionName");
                String url  = resultObj.optString("webUrl");
                String time = resultObj.optString("webPublicationDate");
                News mNews = new News(url, newsTitle, sectionName, time);
                news.add(mNews);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return news;
    }

    private static String makeHttpRequest(URL url) throws Exception{
        String jsonResponse = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;

        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200){
                inputStream = conn.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + conn.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    private static URL createUrl(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
