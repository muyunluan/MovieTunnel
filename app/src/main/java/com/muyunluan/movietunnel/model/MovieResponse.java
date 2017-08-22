package com.muyunluan.movietunnel.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 8/21/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieResponse {
    @SerializedName("page")
    private int mPage;
    @SerializedName("total_results")
    private int mTotalResults;
    @SerializedName("total_pages")
    private int mTotalPages;
    @SerializedName("results")
    private ArrayList<Movie> mResults;

    public int getmPage() {
        return mPage;
    }

    public void setmPage(int mPage) {
        this.mPage = mPage;
    }

    public int getmTotalResults() {
        return mTotalResults;
    }

    public void setmTotalResults(int mTotalResults) {
        this.mTotalResults = mTotalResults;
    }

    public int getmTotalPages() {
        return mTotalPages;
    }

    public void setmTotalPages(int mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public ArrayList<Movie> getmResults() {
        return mResults;
    }

    public void setmResults(ArrayList<Movie> mResults) {
        this.mResults = mResults;
    }
}
