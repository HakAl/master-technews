package com.jacmobile.technews.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.jacmobile.technews.activities.MainActivity;
import com.jacmobile.technews.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import at.theengine.android.simple_rss2_android.RSSItem;
import at.theengine.android.simple_rss2_android.SimpleRss2Parser;
import at.theengine.android.simple_rss2_android.SimpleRss2ParserCallback;
import com.jacmobile.technews.util.adapters.NewsItemAdapter;
import com.jacmobile.technews.util.listeners.NewsItemSelectedListener;
import com.jacmobile.technews.util.networking.RssUrls;

public class Home extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{
    private Context mContext;
    private ListView mListLatest;
    private NewsItemAdapter mListAdapter;
    private NewsItemSelectedListener newsItemSelectedListener;
    private SwipeRefreshLayout swipeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);

        mContext = getActivity().getApplicationContext();
        getRssFeed(getArguments().getString(RssUrls.RSS_URL_KEY));
        mListLatest = (ListView) view.findViewById(R.id.list_latest_news);


        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_latest);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setMinimumHeight(R.dimen.ten_dp);
        swipeLayout.setColorScheme(R.color.Green, R.color.CoolBlue, R.color.Yellow, R.color.CoolGreen);

        return view;
    }

    private void getRssFeed(String url)
    {
        final ArrayList<String> rssFeed = new ArrayList<String>();
        final ArrayList<String> dates = new ArrayList<String>();
        final ArrayList<URL> links = new ArrayList<URL>();
        SimpleRss2Parser parser = new SimpleRss2Parser(url,
                new SimpleRss2ParserCallback()
                {
                    @Override
                    public void onFeedParsed(List<RSSItem> items)
                    {
                        for (int i = 0; i < items.size(); i++) {
                            Log.d("SimpleRss2ParserDemo", items.get(i).getDescription());
                            links.add(items.get(i).getLink());
                            dates.add(items.get(i).getDate());
                            rssFeed.add(items.get(i).getTitle());
                        }
                        mListAdapter = new NewsItemAdapter(mContext, R.layout.news_item, R.id.tv_news_title, rssFeed, dates, links, newsItemSelectedListener);
                        mListLatest.setAdapter(mListAdapter);
                    }

                    @Override
                    public void onError(Exception ex)
                    {
                        Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        parser.parseAsync();
    }

    public Home()
    {
    }

    public static Home getInstance(String rssUrl)
    {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(RssUrls.RSS_URL_KEY, rssUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.newsItemSelectedListener = (MainActivity) activity;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        makePrettyRefresh();
    }

    @Override
    public void onRefresh()
    {
        getRssFeed(getArguments().getString(RssUrls.RSS_URL_KEY));
        makePrettyRefresh();
    }

    private void makePrettyRefresh()
    {
        swipeLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                swipeLayout.setRefreshing(false);
            }
        }, 2500);
    }
}
