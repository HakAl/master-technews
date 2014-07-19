package com.jacmobile.technews.util.interaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacmobile.technews.R;

/**
 * Created by User on 7/13/2014.
 */
public class SpinnerAdapter extends ArrayAdapter<String>
{

    private Context mContext;

    public SpinnerAdapter(Context context, int textViewResourceId, String[] objects)
    {
        super(context, textViewResourceId, objects);
        mContext = context.getApplicationContext();
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent)
    {
        // TODO Auto-generated method stub
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        //return super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View row = inflater.inflate(R.layout.row, parent, false);
//        TextView label = (TextView) row.findViewById(R.id.weekofday);
//        label.setText(DayOfWeek[position]);
//
//        ImageView icon = (ImageView) row.findViewById(R.id.icon);
//
//        if (DayOfWeek[position] == "Sunday") {
//            icon.setImageResource(R.drawable.icon);
//        } else {
//            icon.setImageResource(R.drawable.icongray);
//        }

        return null;
    }
}