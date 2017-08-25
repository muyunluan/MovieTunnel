package com.muyunluan.movietunnel.model.movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 8/21/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieResponse {
    @SerializedName("page")
    private Integer mPage;
    @SerializedName("total_results")
    private Integer mTotalResults;
    @SerializedName("total_pages")
    private Integer mTotalPages;
    @SerializedName("results")
    private ArrayList<Movie> mResults;

    public Integer getmPage() {
        return mPage;
    }

    public void setmPage(Integer mPage) {
        this.mPage = mPage;
    }

    public Integer getmTotalResults() {
        return mTotalResults;
    }

    public void setmTotalResults(Integer mTotalResults) {
        this.mTotalResults = mTotalResults;
    }

    public Integer getmTotalPages() {
        return mTotalPages;
    }

    public void setmTotalPages(Integer mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public ArrayList<Movie> getmResults() {
        return mResults;
    }

    public void setmResults(ArrayList<Movie> mResults) {
        this.mResults = mResults;
    }
}
