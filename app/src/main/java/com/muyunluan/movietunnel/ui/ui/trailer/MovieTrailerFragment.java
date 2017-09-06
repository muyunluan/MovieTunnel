package com.muyunluan.movietunnel.ui.ui.trailer;

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
import com.muyunluan.movietunnel.model.trailer.MovieTrailer;
import com.muyunluan.movietunnel.model.trailer.MovieTrailerResponse;
import com.muyunluan.movietunnel.utls.data.Constants;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiClient;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fei Deng on 8/29/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieTrailerFragment extends Fragment {
    private static final String TAG = MovieTrailerFragment.class.getSimpleName();

    private static MovieTrailerFragment mFragment;

    private RecyclerView mRecyclerView;
    private MovieTrailerAdapter mTrailerAdapter;
    private ArrayList<MovieTrailer> mMovieTrailers = new ArrayList<>();

    private static int mMovieId;

    private OnTrailerListItemClick mItemClick;

    public MovieTrailerFragment() {

    }

    public static MovieTrailerFragment newInstance(int movieId) {
        if (null == mFragment) {
            mFragment = new MovieTrailerFragment();
        }
        mMovieId = movieId;

        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_trailer, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_trailers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        getTrailers(mMovieId);

        return rootView;
    }

    private void getTrailers(int movieId) {
        RetrofitApiInterface apiInterface =
                RetrofitApiClient.getBaseClient().create(RetrofitApiInterface.class);

        Call<MovieTrailerResponse> movieResponseCall =
                apiInterface.getMovieTrailers(movieId, Constants.MOVIE_DATABASE_KEY);

        movieResponseCall.enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {
                mMovieTrailers = response.body().getmResults();
                Log.i(TAG, "onResponse: get trailer list - " + mMovieTrailers.size());
                mTrailerAdapter = new MovieTrailerAdapter(mMovieTrailers, mItemClick);
                mRecyclerView.setAdapter(mTrailerAdapter);
                mTrailerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTrailerListItemClick) {
            mItemClick = (OnTrailerListItemClick) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnTrailerListItemClick");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mItemClick = null;
    }

    public interface OnTrailerListItemClick {
        void onTrailerListItemClick(String itemKey);
    }
}
