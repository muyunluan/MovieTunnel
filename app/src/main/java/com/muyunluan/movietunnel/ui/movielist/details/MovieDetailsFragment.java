package com.muyunluan.movietunnel.ui.movielist.details;

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
import com.muyunluan.movietunnel.ui.movielist.review.MovieReviewAdapter;
import com.muyunluan.movietunnel.utls.data.Constants;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiClient;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fei Deng on 8/23/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieDetailsFragment extends Fragment {

    private static final String TAG = MovieDetailsFragment.class.getSimpleName();

    private static MovieDetailsFragment mFragment;

    private static int mMovieId;

    private RecyclerView mTrailerRecyclerView;

    private RecyclerView mReviewRecyclerView;
    private MovieReviewAdapter mReviewAdapter;
    private ArrayList<MovieReview> mMovieReviews = new ArrayList<>();

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
            // TODO: is this legal?
            mMovieId = 0;
        }

        getReviews(mMovieId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        Context context = view.getContext();

        mTrailerRecyclerView = (RecyclerView) view.findViewById(R.id.rcy_trailer);

        mReviewRecyclerView = (RecyclerView) view.findViewById(R.id.rcy_review);
        mReviewRecyclerView.setLayoutManager(new LinearLayoutManager(context));

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
                mReviewRecyclerView.setAdapter(new MovieReviewAdapter(mMovieReviews));
            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {

            }
        });
    }

}
