package com.daisy.newsreport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmini2 on 07/11/2016.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String murl;
    public NewsLoader(Context context, String url) {
        super(context);
        murl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        ArrayList<News> newsArrayList = QueryUtils.fetchNewsData(murl);
        return newsArrayList;
    }
}
