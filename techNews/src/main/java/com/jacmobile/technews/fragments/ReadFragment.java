package com.jacmobile.technews.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jacmobile.technews.R;

import com.jacmobile.technews.util.networking.RssUrls;


/**
 * Created by User on 7/12/2014.
 */
public class ReadFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
{
    private WebView mWebView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_read, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_read);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorScheme(R.color.Green, R.color.CoolBlue, R.color.Yellow, R.color.CoolGreen);
        swipeRefreshLayout.setRefreshing(true);

        mWebView = (WebView) view.findViewById(R.id.wv_read);
        mWebView.setWebViewClient(new ReadWebClient());
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);

        if (savedInstanceState == null) {
            mWebView.loadUrl(getArguments().getString(RssUrls.LINK_URL_KEY));
        } else {
            mWebView.restoreState(savedInstanceState);
        }

        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh()
    {
        swipeRefreshLayout.setRefreshing(false);
    }

    public ReadFragment()
    {
    }

    public static ReadFragment getInstance(String url)
    {
        ReadFragment fragment = new ReadFragment();
        Bundle args = new Bundle();
        args.putString(RssUrls.LINK_URL_KEY, url);
        fragment.setArguments(args);
        return fragment;
    }

    private class ReadWebClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }

        public void onPageFinished(WebView view, String url)
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1667);
        }
    }
}
