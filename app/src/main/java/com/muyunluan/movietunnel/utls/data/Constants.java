package com.muyunluan.movietunnel.utls.data;

/**
 * Created by Fei Deng on 8/14/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class Constants {

    /* The Movie DB related */
    public static final String MOVIE_DATABASE_KEY = "aca963610842dd276b51c32697e0f50d";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String DISCOVER_BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_SORT = "sort_by";
    public static final String SORT_BY_POPULAR = "popularity.desc";
    public static final String SORT_BY_RATING = "vote_average.desc";
    public static final String PARAM_LANGUAGE = "language";

    public static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie";
    public static final String PATH_TRAILERS = "videos";
    public static final String PATH_REVIEWS = "reviews";

    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String IMAGE_SIZE = "w185/";

    /* Keywords for transferring data */
    public static final String ARG_MOVIE_ID = "movie_id";
    public static final String ARG_MOVIE = "movie";

    /* Content Provider */
    // usually the package name
    public static final String CONTENT_AUTHORITY = "com.muyunluan.movietunnel";
    // usually the table name
    public static final String CONTENT_PATH_FAVORITES = "favorites";


}
