package com.muyunluan.movietunnel.ui.ui.review;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.model.review.MovieReview;
import com.muyunluan.movietunnel.model.review.MovieReviewResponse;
import com.muyunluan.movietunnel.utls.data.Constants;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiClient;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fei Deng on 8/22/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieReviewFragment extends Fragment {

    private static final String TAG = MovieReviewFragment.class.getSimpleName();

    private static MovieReviewFragment mFragment;

    private RecyclerView mRecyclerView;
    private MovieReviewAdapter mReviewAdapter;
    private ArrayList<MovieReview> mMovieReviews = new ArrayList<>();

    private static int mMovieId;

    public MovieReviewFragment() {
    }

    public static MovieReviewFragment newInstance(int movieId) {
        if (null == mFragment) {
            mFragment = new MovieReviewFragment();
        }
        mMovieId = movieId;

        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_review, container, false);
        Context context = container.getContext();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_reviews);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        getReviews(mMovieId);

        return rootView;
    }

    private void getReviews(int movieId) {
        RetrofitApiInterface apiInterface =
                RetrofitApiClient.getBaseClient().create(RetrofitApiInterface.class);

        Call<MovieReviewResponse> movieReviewCall =
                apiInterface.getMovieReviews(movieId, Constants.MOVIE_DATABASE_KEY);

        movieReviewCall.enqueue(new Callback<MovieReviewResponse>() {
            @Override
            public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {
                mMovieReviews = response.body().getmResults();
                Log.i(TAG, "onResponse: get review list - " + mMovieReviews.size());
                mReviewAdapter = new MovieReviewAdapter(mMovieReviews);
                mRecyclerView.setAdapter(mReviewAdapter);
                mReviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }
}
