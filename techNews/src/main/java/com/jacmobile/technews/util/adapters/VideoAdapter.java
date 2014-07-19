package com.jacmobile.technews.util.adapters;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jacmobile.technews.R;

import com.jacmobile.technews.util.memory.BitmapLruCache;


public class VideoAdapter extends BaseAdapter 
{
	private LayoutInflater mInflater;
	private ImageLoader mImageLoader;
	
	private ArrayList<String> mData;

	static class ViewHolder 
	{
		NetworkImageView image;
		TextView text;
	}
	
	
	public VideoAdapter(Context context, RequestQueue rq, ArrayList<String> data) 
	{
		mInflater = LayoutInflater.from(context);
		mData = data;
		ImageLoader.ImageCache imageCache = new BitmapLruCache();
		mImageLoader = new ImageLoader(rq, imageCache);
	}

	public int getCount() {
		return mData.size();
	}

	public int getViewTypeCount() {
		return 1;
	}

	public int getItemViewType(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;

		if (convertView == null) 
		{
			convertView = mInflater.inflate(R.layout.video_list_item, null);

			holder = new ViewHolder();
			holder.image = (NetworkImageView) convertView.findViewById(R.id.iv_thumbnail);
			holder.text = (TextView) convertView.findViewById(R.id.tv_title);

			convertView.setTag(holder);
		} else 
		{
			holder = (ViewHolder) convertView.getTag();
		}
		

		holder.image.setImageUrl(mData.get(position), mImageLoader);
		holder.text.setText(mData.get(position));

		return convertView;
	}

	public Object getItem(int position) {
		return mData.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	// Default maximum disk usage in bytes
	private static final int DEFAULT_DISK_USAGE_BYTES = 25 * 1024 * 1024;

	// Default cache folder name
	private static final String DEFAULT_CACHE_DIR = "photos";

	// Most code copied from "Volley.newRequestQueue(..)", we only changed cache directory
	private static RequestQueue newRequestQueue(Context context) {
	    // define cache folder
	    File rootCache = context.getExternalCacheDir();
	    if (rootCache == null) {
	        Log.e("No file dir", "Can't find External Cache Dir, "
	                + "switching to application specific cache directory");
	        rootCache = context.getCacheDir();
	    }

	    File cacheDir = new File(rootCache, DEFAULT_CACHE_DIR);
	    cacheDir.mkdirs();

	    HttpStack stack = new HurlStack();
	    Network network = new BasicNetwork(stack);
	    DiskBasedCache diskBasedCache = new DiskBasedCache(cacheDir, DEFAULT_DISK_USAGE_BYTES);
	    RequestQueue queue = new RequestQueue(diskBasedCache, network);
	    queue.start();

	    return queue;
	}
}