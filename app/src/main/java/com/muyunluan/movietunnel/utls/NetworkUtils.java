package com.muyunluan.movietunnel.utls;

import android.text.TextUtils;
import android.util.Log;

import com.muyunluan.movietunnel.model.movie.Movie;
import com.muyunluan.movietunnel.model.movie.MovieResponse;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiClient;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fei Deng on 8/15/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static String mApiKey = null;
    private ArrayList<Movie> mMovieList = new ArrayList<>();

    public NetworkUtils(String apiKey) {
        if (TextUtils.isEmpty(apiKey)) {
            //Popup error
            Log.e(TAG, "NetworkUtils: invalid api key");
            return;
        }

        mApiKey = apiKey;
    }

    public ArrayList<Movie> getMoviesWithRetrofit() {
        if (TextUtils.isEmpty(mApiKey)) {
            Log.e(TAG, "getMoviesWithRetrofit: invalid api key");
            return null;
        }

        RetrofitApiInterface apiInterface =
                RetrofitApiClient.getBaseClient().create(RetrofitApiInterface.class);

        Call<MovieResponse> movieCall = apiInterface.getTopRatedMovies(mApiKey);
        movieCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i(TAG, "onResponse: " + response.body().toString());
                mMovieList = response.body().getmResults();
                Log.i(TAG, "onResponse: get movie list - " + mMovieList.size());
                Log.i(TAG, "onResponse: test first Movie object - " + mMovieList.get(0).toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        if (null == mMovieList || mMovieList.size() < 1) {
            Log.e(TAG, "getMoviesWithRetrofit: empty result returned");
        } else {
            Log.i(TAG, "getMoviesWithRetrofit: get movie list - " + mMovieList.size());
        }
        return mMovieList;
    }
}
