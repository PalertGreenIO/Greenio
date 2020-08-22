package com.dcoders.greenio;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

public class MyAdapterdata {

    private static MyAdapterdata mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    public MyAdapterdata(Context mContext) {
        this.mContext = mContext;
    }

    public RequestQueue getmRequestQueue(){
        if(mRequestQueue == null){
            Cache cache = new DiskBasedCache(mContext.getCacheDir(),1024*1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache,network);
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }
    public static synchronized MyAdapterdata getInstance(Context context){
        if(mInstance==null){
            mInstance = new MyAdapterdata(context);
        }
        return mInstance;
    }
    public<T> void addToRequestQueue(Request<T>request){
        mRequestQueue.add(request);
    }
}
