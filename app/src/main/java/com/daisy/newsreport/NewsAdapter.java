package com.daisy.newsreport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        TextView newsTitleTextView = (TextView) listItemView.findViewById(R.id.news_title);
        TextView sectionNameTextView = (TextView) listItemView.findViewById(R.id.section_name);
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.news_date);
        newsTitleTextView.setText(news.getNewsTitle());
        sectionNameTextView.setText(news.getSectionName());
        try {
            Date date = dateFormat.parse(news.getTime());
            String newsDate = dateFormat.format(date);
            dateTextView.setText(newsDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return listItemView;
    }
}
