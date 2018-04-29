package com.example.erik.newsapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erik.newsapp.R;
import com.example.erik.newsapp.model.News;

import org.w3c.dom.Text;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<News> newsList;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsRecyclerViewAdapter(List<News> myDataset, Context context) {
        newsList = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view, context);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NewsViewHolder){
            NewsViewHolder nvHolder = (NewsViewHolder) holder;
            nvHolder.title = newsList.get(position).getTitle();
            nvHolder.description = newsList.get(position).getSection();
            nvHolder.tags = newsList.get(position).getSection();
            nvHolder.date = newsList.get(position).getDate();
            nvHolder.titleTextView.setText(newsList.get(position).getTitle());
            nvHolder.descriptionTextView.setText(newsList.get(position).getAuthor());
            nvHolder.dateTextView.setText(newsList.get(position).getDate());
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        public String title, date, description, tags;
        public TextView titleTextView, dateTextView, descriptionTextView, tagsTextView;
        public NewsViewHolder(final View itemView, final Context context) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Noticia", Toast.LENGTH_LONG).show();
                }
            });
            titleTextView = itemView.findViewById(R.id.news_title);
            dateTextView = itemView.findViewById(R.id.news_date);
            descriptionTextView = itemView.findViewById(R.id.news_description);
            tagsTextView = itemView.findViewById(R.id.news_tags);
        }
    }
}