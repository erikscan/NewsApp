package com.example.erik.newsapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.erik.newsapp.R;
import com.example.erik.newsapp.adapter.NewsRecyclerViewAdapter;
import com.example.erik.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    List<News> newsList;
    NewsRecyclerViewAdapter newsRVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        newsList = new ArrayList<News>();
        for(int i = 0; i < 10; i++){
            newsList.add(new News(getApplicationContext().getString(R.string.news_title),
                    getApplicationContext().getString(R.string.news_author),
                    getApplicationContext().getString(R.string.news_date) ,
                    getApplicationContext().getString(R.string.news_section),
                    getApplicationContext().getString(R.string.news_description)));
        }
        newsRVA = new NewsRecyclerViewAdapter(newsList, this.getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        RecyclerView newsRV = (RecyclerView) findViewById(R.id.news_recycler_view);
        newsRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        newsRV.setAdapter(newsRVA);

    }
}
