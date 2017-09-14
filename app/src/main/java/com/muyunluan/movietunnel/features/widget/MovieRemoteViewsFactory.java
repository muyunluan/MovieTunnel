package com.muyunluan.movietunnel.features.widget;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.model.movie.Movie;
import com.muyunluan.movietunnel.utls.network.NetworkBasic;
import com.muyunluan.movietunnel.utls.preference.SharedPreferenceUtl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Fei Deng on 9/11/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

/**
  In the context of an app widget, the Adapter is replaced by a RemoteViewsFactory,
  which is simply a thin wrapper around the Adapter interface.
  When requested for a specific item in the collection, the RemoteViewsFactory creates
  and returns the item for the collection as a RemoteViews object.
 */

public class MovieRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = MovieRemoteViewsFactory.class.getSimpleName();

    private Context mContext;
    private Cursor mCursor;
    private SharedPreferenceUtl mSharedPreferenceUtl;

    private ArrayList<Movie> favoriteList = new ArrayList<>();

    public MovieRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
        mSharedPreferenceUtl = SharedPreferenceUtl.getInstance(mContext);
    }

    @Override
    public void onCreate() {
        //fetchFromSharedPreference();
    }

    @Override
    public void onDataSetChanged() {
        fetchFromSharedPreference();
    }

    private void fetchFromContentProvider() {
    }

    private void fetchFromSharedPreference() {
        Movie movie = new Movie();
        if (mSharedPreferenceUtl.getAllItems().size() > 0) {
            for (Map.Entry<String, ?> entry :mSharedPreferenceUtl.getAllItems().entrySet()) {
                Log.d(TAG, "getViewAt: Map value - " + entry.getKey() + ": " + entry.getValue());
                movie.setmId( Integer.valueOf(entry.getKey()) );
                movie.setmPosterPath((String) entry.getValue());
                favoriteList.add(movie);
            }
        } else {
            Log.e(TAG, "fetchFromSharedPreference: no saved Movie in SharedPreference");
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount: " + favoriteList.size());
        return 1;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        // TODO: Why position 0 is called three times?
        Log.i(TAG, "getViewAt: position - " + position);
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_movie_list_item);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(
                    new URL(
                            NetworkBasic.buildImageUrlStr(
                                    favoriteList.get(position).getmPosterPath()
                            )
                    ).openConnection().getInputStream());
            remoteViews.setImageViewBitmap(R.id.img_movie, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "getViewAt: " + NetworkBasic.buildImageUrlStr(favoriteList.get(position).getmPosterPath()));

        // TODO: click image to this movie detail page
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
