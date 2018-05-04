package com.example.erik.newsapp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.erik.newsapp.R;
import com.example.erik.newsapp.adapter.NewsRecyclerViewAdapter;
import com.example.erik.newsapp.model.News;
import com.example.erik.newsapp.request.NewsRequest;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private static final String REQUEST_URL = "https://content.guardianapis.com/search";

    private List<News> newsList;
    private NewsRecyclerViewAdapter newsRVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        newsList = new ArrayList<News>();
        //NewsRequest newsRequest = createNewsRequest();
        //newsList = newsRequest.loadInBackground();
        for(int i = 0; i < 10; i++){
            String url = "site.com/" + i;
            String title = "Test Title " + i;
            String section = "Test Section " + i;
            String date = "01/01/200" + i;
            newsList.add(new News(url,title,section,date));
        }
        newsRVA = new NewsRecyclerViewAdapter(newsList, this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        RecyclerView newsRV = (RecyclerView) findViewById(R.id.news_recycler_view);
        newsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        newsRV.setAdapter(newsRVA);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private NewsRequest createNewsRequest(){
        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("api-key", "test");
        uriBuilder.appendQueryParameter("order-by", "newest");
        return new NewsRequest(this, uriBuilder.toString());
    }
}
