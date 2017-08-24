package com.muyunluan.movietunnel.ui.movielist.review;

import android.support.v4.app.Fragment;

/**
 * Created by Fei Deng on 8/22/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieReviewFragment extends Fragment {

    private static final String TAG = MovieReviewFragment.class.getSimpleName();

    private static MovieReviewFragment mFragment;

    public MovieReviewFragment() {
    }

    public static MovieReviewFragment newInstance() {
        if (null == mFragment) {
            mFragment = new MovieReviewFragment();
        }

        return mFragment;
    }


}
