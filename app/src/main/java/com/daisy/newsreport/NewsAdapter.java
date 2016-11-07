package com.daisy.newsreport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by macmini2 on 07/11/2016.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.news_list_item, parent, false);
        }

        News news = getItem(position);
        TextView newsTitleTextView = (TextView) listItemView.findViewById(R.id.news_title);
        TextView sectionNameTextView = (TextView) listItemView.findViewById(R.id.section_name);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.news_date);
        newsTitleTextView.setText(news.getNewsTitle());
        sectionNameTextView.setText(news.getSectionName());
        dateTextView.setText(news.getTime());

        return listItemView;
    }
}
