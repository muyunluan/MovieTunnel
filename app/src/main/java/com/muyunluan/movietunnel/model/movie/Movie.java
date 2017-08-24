package com.muyunluan.movietunnel.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 8/15/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public final class Movie implements Parcelable {

    @SerializedName("")
    private Integer mVoteCount;
    @SerializedName("id")
    private Integer mId;
    @SerializedName("video")
    private Boolean mVideo;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("genre_ids")
    private ArrayList<Integer> mGenreIds = new ArrayList<Integer>();
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("release_date")
    private String mReleaseDate;

    public Movie(Integer mVoteCount, Integer mId, Boolean mVideo,
                 Double mVoteAverage, String mTitle, Double mPopularity,
                 String mPosterPath, String mOriginalLanguage,
                 String mOriginalTitle, ArrayList<Integer> mGenreIds,
                 String mBackdropPath, Boolean mAdult, String mOverview,
                 String mReleaseDate) {
        this.mVoteCount = mVoteCount;
        this.mId = mId;
        this.mVideo = mVideo;
        this.mVoteAverage = mVoteAverage;
        this.mTitle = mTitle;
        this.mPopularity = mPopularity;
        this.mPosterPath = mPosterPath;
        this.mOriginalLanguage = mOriginalLanguage;
        this.mOriginalTitle = mOriginalTitle;
        this.mGenreIds = mGenreIds;
        this.mBackdropPath = mBackdropPath;
        this.mAdult = mAdult;
        this.mOverview = mOverview;
        this.mReleaseDate = mReleaseDate;
    }

    public Integer getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(Integer mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Boolean getmVideo() {
        return mVideo;
    }

    public void setmVideo(Boolean mVideo) {
        this.mVideo = mVideo;
    }

    public Double getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(Double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Double getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(Double mPopularity) {
        this.mPopularity = mPopularity;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public ArrayList<Integer> getmGenreIds() {
        return mGenreIds;
    }

    public void setmGenreIds(ArrayList<Integer> mGenreIds) {
        this.mGenreIds = mGenreIds;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public Boolean getmAdult() {
        return mAdult;
    }

    public void setmAdult(Boolean mAdult) {
        this.mAdult = mAdult;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    protected Movie(Parcel in) {
        mTitle = in.readString();
        mPosterPath = in.readString();
        mOriginalLanguage = in.readString();
        mOriginalTitle = in.readString();
        mBackdropPath = in.readString();
        mOverview = in.readString();
        mReleaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mPosterPath);
        dest.writeString(mOriginalLanguage);
        dest.writeString(mOriginalTitle);
        dest.writeString(mBackdropPath);
        dest.writeString(mOverview);
        dest.writeString(mReleaseDate);
    }
}
