package com.muyunluan.movietunnel.ui.movielist.details;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.ui.movielist.trailer.MovieTrailerFragment;
import com.muyunluan.movietunnel.utls.data.Constants;

/**
 * Created by Fei Deng on 8/24/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieDetailsActivity extends AppCompatActivity implements MovieTrailerFragment.OnTrailerListItemClick {
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    private int mMovieId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if (getIntent().hasExtra(Constants.ARG_MOVIE_ID)) {
            mMovieId = getIntent().getIntExtra(Constants.ARG_MOVIE_ID, 0);
            Log.i(TAG, "onCreate: get movie ID - " + mMovieId);
        } else {
            Log.e(TAG, "onCreate: no selected movie ID found");
            Snackbar.make(findViewById(R.id.fragment_container), "No selected Movie ID found", Snackbar.LENGTH_LONG).show();
        }

        Log.i(TAG, "onCreate: get movie ID - " + mMovieId);

        if (null == savedInstanceState) {
            Bundle args = new Bundle();
            if (null != getIntent()) {
                args.putInt(Constants.ARG_MOVIE_ID, getIntent().getIntExtra(Constants.ARG_MOVIE_ID, 0));
            }
            MovieDetailsFragment fragment = MovieDetailsFragment.newInstance();
            fragment.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onTailerListItemClick(String itemKey) {
        Uri uri = Uri.parse("vnd.youtube:" + itemKey);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.putExtra("VIDEO_ID", itemKey);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Log.e(TAG, "onTailerListItemClick: Youtube app not installed");
        }
    }
}
