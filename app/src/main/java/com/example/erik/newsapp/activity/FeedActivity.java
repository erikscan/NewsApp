package com.example.erik.newsapp.activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.erik.newsapp.R;
import com.example.erik.newsapp.adapter.NewsRecyclerViewAdapter;
import com.example.erik.newsapp.model.News;
import com.example.erik.newsapp.request.NewsRequestLoader;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String REQUEST_URL = "https://content.guardianapis.com/search";

    //private List<News> newsList;
    private NewsRecyclerViewAdapter newsRVA;
    private static final int NEWS_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //newsList = new ArrayList<>();
        //NewsRequestLoader newsRequest = createNewsRequest();
        //newsList = newsRequest.loadInBackground();
//        for(int i = 0; i < 10; i++){
//            String url = "site.com/" + i;
//            String title = "Test Title " + i;
//            String section = "Test Section " + i;
//            String date = "01/01/200" + i;
//            newsList.add(new News(url,title,section,date));
//        }
        newsRVA = new NewsRecyclerViewAdapter(new ArrayList<News>(), this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        RecyclerView newsRV = findViewById(R.id.news_recycler_view);
        newsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        newsRV.setAdapter(newsRVA);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", "android");
        uriBuilder.appendQueryParameter("api-key", "test");
        uriBuilder.appendQueryParameter("order-by", "newest");

        return new NewsRequestLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        newsRVA.clear();
        newsRVA.notifyDataSetChanged();

        if (news != null && !news.isEmpty()) {
            newsRVA.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsRVA.clear();
    }
}
