package com.muyunluan.movietunnel.ui.movielist.details;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.muyunluan.movietunnel.R;

/**
 * Created by Fei Deng on 8/24/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_movie_details);

        if (null == savedInstanceState) {

            MovieDetailsFragment fragment = MovieDetailsFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
