package com.example.erik.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.erik.newsapp.R;
import com.example.erik.newsapp.model.News;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<News> newsList;
    private Context context;

    public NewsRecyclerViewAdapter(List<News> myDataset, Context context) {
        newsList = myDataset;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NewsViewHolder){
            NewsViewHolder nvHolder = (NewsViewHolder) holder;
            nvHolder.urlTextView.setText(newsList.get(position).getUrl());
            nvHolder.titleTextView.setText(newsList.get(position).getTitle());
            nvHolder.sectionTextView.setText(newsList.get(position).getSection());
            nvHolder.dateTextView.setText(newsList.get(position).getDate());
        }

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView urlTextView, titleTextView, sectionTextView, dateTextView;
        public NewsViewHolder(final View itemView, final Context context) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = urlTextView.getText().toString();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    itemView.getContext().startActivity(i);
                }
            });
            urlTextView = itemView.findViewById(R.id.news_url);
            titleTextView = itemView.findViewById(R.id.news_title);
            sectionTextView = itemView.findViewById(R.id.news_section);
            dateTextView = itemView.findViewById(R.id.news_date);
        }
    }

    public void clear(){
        newsList.clear();
    }

    public void addAll(List<News> list){
        newsList.addAll(list);
    }
}