package com.jacmobile.technews.util.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jacmobile.technews.R;

import java.net.URL;
import java.util.ArrayList;

import com.jacmobile.technews.util.listeners.NewsItemSelectedListener;

/**
 * Created by User on 7/12/2014.
 */
public class NewsItemAdapter extends ArrayAdapter<String>
{
    private ArrayList<String> dates;
    private ArrayList<URL> links;
    private Context mContext;
    private NewsItemSelectedListener newsItemSelectedListener;

    public NewsItemAdapter(Context context, int layout, int resId, ArrayList<String> titles, ArrayList<String> descriptions, ArrayList<URL> links, NewsItemSelectedListener newsItemSelectedListener)
    {
        super(context, layout, resId, titles);
        this.dates = descriptions;
        this.links = links;
        this.mContext = context;
        this.newsItemSelectedListener = newsItemSelectedListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
//Inflate a new row if one isn’t recycled
        if (row == null) {
            row = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }
//        ImageView left = (ImageView)row.findViewById(R.id.leftimage);
//        ImageView right = (ImageView)row.findViewById(R.id.rightimage);
//        left.setImageResource(R.drawable.icon);
//        right.setImageResource(R.drawable.icon);
        String title = getItem(position);
        String date = dates.get(position);

        TextView tvTitle = (TextView) row.findViewById(R.id.tv_news_title);
        TextView tvDescription = (TextView) row.findViewById(R.id.tv_news_date);
        tvTitle.setText(title);
        tvDescription.setText(date);

        row.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newsItemSelectedListener.onNewsItemClicked(links.get(position).toString());
            }
        });

        return row;
    }
}