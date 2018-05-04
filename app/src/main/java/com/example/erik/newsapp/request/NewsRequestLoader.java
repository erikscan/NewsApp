package com.example.erik.newsapp.request;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.erik.newsapp.util.QueryUtils;
import com.example.erik.newsapp.model.News;
import java.util.List;

public class NewsRequestLoader extends AsyncTaskLoader<List<News>> {

    private String mUrl;

    public NewsRequestLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<News> books = QueryUtils.fetchNewsData(mUrl);
        return books;
    }
}