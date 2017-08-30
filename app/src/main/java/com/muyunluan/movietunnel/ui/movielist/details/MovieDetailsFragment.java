package com.muyunluan.movietunnel.ui.movielist.details;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.ui.movielist.review.MovieReviewFragment;
import com.muyunluan.movietunnel.ui.movielist.trailer.MovieTrailerFragment;

/**
 * Created by Fei Deng on 8/23/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieDetailsFragment extends Fragment {

    private static final String TAG = MovieDetailsFragment.class.getSimpleName();

    private static MovieDetailsFragment mFragment;

    private static int mMovieId;

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

        if (null != getArguments() && getArguments().containsKey("movie_id")) {
            mMovieId = getArguments().getInt("movie_id");
        } else {
            // TODO: is this a legal movie ID?
            mMovieId = 0;
        }
        Log.i(TAG, "onCreate: get movie ID - " + mMovieId);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
