package com.muyunluan.movietunnel.utls.retrofit;

import com.muyunluan.movietunnel.model.Movie;
import com.muyunluan.movietunnel.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fei Deng on 8/17/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public interface RetrofitApiInterface {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);
    @GET("movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);
    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Query("id") int id, @Query("api_key") String apiKey);
}
