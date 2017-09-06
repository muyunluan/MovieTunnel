package com.muyunluan.movietunnel.ui.ui.details;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.model.movie.Movie;
import com.muyunluan.movietunnel.ui.ui.review.MovieReviewFragment;
import com.muyunluan.movietunnel.ui.ui.trailer.MovieTrailerFragment;
import com.muyunluan.movietunnel.utls.network.NetworkBasic;
import com.muyunluan.movietunnel.utls.sqlite.MovieDBHelper;
import com.squareup.picasso.Picasso;

import static com.muyunluan.movietunnel.utls.data.Constants.ARG_MOVIE;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry._ID;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_ID;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_OVERVIEW;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_POSTER_PATH;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_RELEASE_DATE;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_TITLE;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.COLUMN_VOTE_AVERAGE;
import static com.muyunluan.movietunnel.utls.sqlite.MovieFavoriteContract.MovieEntry.TABLE_NAME_FAVORITE;

/**
 * Created by Fei Deng on 8/23/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieDetailsFragment extends Fragment {

    private static final String TAG = MovieDetailsFragment.class.getSimpleName();

    private static MovieDetailsFragment mFragment;

    private static int mMovieId;
    private Movie mMovie;

    private MovieDBHelper mDBHelper;
    private SQLiteDatabase mDB;

    private ImageButton mFavoriteBt;

    public MovieDetailsFragment() {
    }

    public static MovieDetailsFragment newInstance() {
        if (null == mFragment) {
            mFragment = new MovieDetailsFragment();
        }

        return mFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null != getArguments() && getArguments().containsKey(ARG_MOVIE)) {
            mMovie = getArguments().getParcelable(ARG_MOVIE);
            mMovieId = mMovie.getmId();
        } else {
            // TODO: is this a legal movie ID?
            mMovieId = 0;
        }
        Log.i(TAG, "onCreate: get movie ID - " + mMovieId);

        // Create a DB helper (this will create the DB if run for the first time)
        mDBHelper = new MovieDBHelper(getContext());

        // Keep a reference to the mDb in write mode until paused or killed.
        mDB = mDBHelper.getWritableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        TextView titleTv = (TextView) view.findViewById(R.id.tv_title);
        titleTv.setText(mMovie.getmTitle());

        ImageView posterImg = (ImageView) view.findViewById(R.id.img_poster);
        Picasso.with(getContext()).load(NetworkBasic.buildImageUrlStr(mMovie.getmPosterPath())).into(posterImg);

        TextView ratingTv = (TextView) view.findViewById(R.id.tv_rating);
        ratingTv.setText(String.valueOf(mMovie.getmVoteAverage()));

        TextView releaseTv = (TextView) view.findViewById(R.id.tv_release_date);
        releaseTv.setText(mMovie.getmReleaseDate());

        mFavoriteBt = (ImageButton) view.findViewById(R.id.imgbt_favorite);
        toggleFavorite();
        mFavoriteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavorite()) {
                    addToFavorites();
                } else {
                    removeFromFavorites();
                }
                toggleFavorite();
            }
        });

        TextView overviewTv = (TextView) view.findViewById(R.id.tv_overview);
        overviewTv.setText(mMovie.getmOverview());

        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();

        //TODO: How to go back to previous activity not fragment?
        // Configure MovieTrailerFragment
        MovieTrailerFragment movieTrailerFragment = MovieTrailerFragment.newInstance(mMovieId);
        fragmentManager.beginTransaction()
                .add(R.id.trailer_container, movieTrailerFragment)
                .commit();
        // Configure MovieReviewFragment
        MovieReviewFragment movieReviewFragment = MovieReviewFragment.newInstance(mMovieId);
        fragmentManager.beginTransaction()
                .add(R.id.review_container, movieReviewFragment)
                .commit();

        return view;
    }


    /* Leverage SQLite methods to operate Favorites */
    private void toggleFavorite() {
        if (isFavorite()) {
            mFavoriteBt.setImageResource(R.mipmap.ic_star);
        } else {
            mFavoriteBt.setImageResource(R.mipmap.ic_unstar);
        }
    }

    private boolean isFavorite() {
        // Define a projection that specifies which columns from the database
        // will be used after this query
        String[] projection = {
                _ID,
                COLUMN_ID};

        // Filter results WHERE "COLUMN_ID" = "movie id"
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(mMovieId)};

        Cursor cursor = mDB.query(
          TABLE_NAME_FAVORITE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Start from the first result which should be the query one since
        // there is only one item which matches provide movie ID
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
        }
        return false;
    }

    private void addToFavorites() {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, mMovieId);
        values.put(COLUMN_TITLE, mMovie.getmTitle());
        values.put(COLUMN_POSTER_PATH, mMovie.getmPosterPath());
        values.put(COLUMN_OVERVIEW, mMovie.getmOverview());
        values.put(COLUMN_VOTE_AVERAGE, mMovie.getmVoteAverage());
        values.put(COLUMN_RELEASE_DATE, mMovie.getmReleaseDate());

        long newRowId = mDB.insert(TABLE_NAME_FAVORITE, null, values);
        Log.i(TAG, "addToFavorites: added new Favorite ID - " + newRowId);
    }

    private void removeFromFavorites() {
        // The optional WHERE clause to apply when deleting. Passing null will delete all rows.
        String selectionClause = COLUMN_ID + " = ? ";
        // The values will replace ? which is provided in WHERE clause
        String[] selectionArgs = {mMovie.getmId() + ""};
        // The number of rows affected if a whereClause is passed in, 0 otherwise.
        int deletedId = mDB.delete(TABLE_NAME_FAVORITE, selectionClause, selectionArgs);
        Log.i(TAG, "removeFromFavorites: deleted ID - " + deletedId);
    }

    /* End of SQLite */

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
