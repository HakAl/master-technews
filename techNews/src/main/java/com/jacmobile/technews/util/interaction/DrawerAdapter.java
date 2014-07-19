package com.jacmobile.technews.util.interaction;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jacmobile.technews.R;

public class DrawerAdapter extends ArrayAdapter<DrawerItem>
{

	private Context mContext;
	private List<DrawerItem> mDrawerList;
	private int layoutResID;

	public DrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) 
	{
		super(context, layoutResourceID, listItems);
		this.mDrawerList = listItems;
		this.layoutResID = layoutResourceID;
		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) 
		{
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
			drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

			drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);

			drawerHolder.headerLayout = (LinearLayout) view.findViewById(R.id.headerLayout);
			drawerHolder.itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
//			drawerHolder.spinnerLayout = (LinearLayout) view.findViewById(R.id.spinnerLayout);

            drawerHolder.headerLayout.setClickable(false);
            drawerHolder.headerLayout.setFocusable(false);
			view.setTag(drawerHolder);

		} else 
		{
			drawerHolder = (DrawerItemHolder) view.getTag();
		}
		
		DrawerItem dItem = (DrawerItem) this.mDrawerList.get(position);

		if (dItem.getTitle() != null) 
		{
			drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
//			drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.title.setText(dItem.getTitle());
			Log.d("Getview","Passed4");
		} else 
		{			
			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
//			drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);

			drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
					dItem.getImgResID()));
			drawerHolder.ItemName.setText(dItem.getItemName());
			Log.d("Getview","Passed5");
		}
		return view;
	}

	private static class DrawerItemHolder 
	{
		TextView ItemName, title;
		ImageView icon;
//        , spinnerLayout;
		LinearLayout headerLayout, itemLayout;
	}
}