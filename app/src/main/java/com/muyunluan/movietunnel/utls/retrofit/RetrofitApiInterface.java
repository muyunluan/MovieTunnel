package com.muyunluan.movietunnel.utls.retrofit;

import com.muyunluan.movietunnel.model.movie.Movie;
import com.muyunluan.movietunnel.model.movie.MovieResponse;
import com.muyunluan.movietunnel.model.review.MovieReviewResponse;
import com.muyunluan.movietunnel.model.trailer.MovieTrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Fei Deng on 8/17/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public interface RetrofitApiInterface {
    @GET("discover/movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortStr);
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortStr);
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortStr);
    @GET("movie/upcoming")
    Call<MovieResponse> getUpComingMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortStr);
    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
    @GET("movie/{id}/reviews")
    Call<MovieReviewResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);
    @GET("movie/{id}/videos")
    Call<MovieTrailerResponse> getMovieTrailers(@Path("id") int id, @Query("api_key") String apiKey);
}
