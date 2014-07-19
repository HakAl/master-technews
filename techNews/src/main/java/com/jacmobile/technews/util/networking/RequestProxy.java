/**
 * 
 *  Wrapper for request queue
 *  Holds list of all application requests
 */
package com.jacmobile.technews.util.networking;

import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class RequestProxy 
{
	private final String feedUrl = "https://gdata.youtube.com/feeds/api/users/eyesee360/uploads?v=2&alt=jsonc&start-index=1&max-results=25";
	
	private Context mContext;
    private RequestQueue mRequestQueue;
    private ConnectivityManager mConnectivity;

 // package access constructor
    RequestProxy(Context context) 
    {
    	mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());

    	mContext = context.getApplicationContext();
    }
    public void signin() 
    {
        // login request
    }

//  Returns the JSONObject result
    private JSONObject result = new JSONObject();
    public void explorer() 
    {
		if (isOnline())
		{
			JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, feedUrl, null, 
				new Response.Listener<JSONObject>() 
				{
					@Override
					public void onResponse(JSONObject response) 
					{
						result = response;	
					}
				}, new Response.ErrorListener() 
				{
					@Override
					public void onErrorResponse(VolleyError error) 
					{
						Toast.makeText(mContext, error.toString(), Toast.LENGTH_SHORT).show();
					}
					
				});
			jsonReq.setRetryPolicy(new DefaultRetryPolicy(2500, 1, 1.0f));
			
			mRequestQueue.add(jsonReq);
		}
		
    }



	public boolean isOnline() 
	{
    	mConnectivity = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = mConnectivity.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) 
		{
			return true;
		}
		return false;
	}
}
