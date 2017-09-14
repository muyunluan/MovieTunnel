package com.muyunluan.movietunnel.features.details;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.model.movie.Movie;
import com.muyunluan.movietunnel.features.trailer.MovieTrailerFragment;

import static com.muyunluan.movietunnel.utls.data.Constants.ARG_MOVIE;

/**
 * Created by Fei Deng on 8/24/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieDetailsActivity extends AppCompatActivity implements MovieTrailerFragment.OnTrailerListItemClick {
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    private int mMovieId;

    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if (null != getIntent() && getIntent().hasExtra(ARG_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(ARG_MOVIE);
            Log.i(TAG, "onCreate: " + mMovie.toString());
            if (null != mMovie) {
                mMovieId = mMovie.getmId();
                Log.i(TAG, "onCreate: get movie ID - " + mMovieId);
            } else {
                Log.e(TAG, "onCreate: receive empty Movie object");
            }
        } else {
            Log.e(TAG, "onCreate: no selected movie found");
            Snackbar.make(findViewById(R.id.fragment_container), "No selected Movie found", Snackbar.LENGTH_LONG).show();
        }

        // Only create Fragment when there is no saved info
        if (null == savedInstanceState) {
            Bundle args = new Bundle();
            if (null != getIntent()) {
                args.putParcelable(ARG_MOVIE, mMovie);
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
    public void onTrailerListItemClick(String itemKey) {
        Uri uri = Uri.parse("vnd.youtube:" + itemKey);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.putExtra("VIDEO_ID", itemKey);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Log.e(TAG, "onTrailerListItemClick: Youtube app not installed");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            ShareCompat.IntentBuilder.from(this)
                    .setType("text/plain")
                    .setChooserTitle(getString(R.string.share))
                    .setText(String.valueOf(mMovieId))
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO: Go to MainActivity.java
    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
