package com.muyunluan.movietunnel.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 8/15/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public final class Movie implements Parcelable {
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mImageSource;
    @SerializedName("overview")
    private String mSynopsis; //overview
    @SerializedName("vote_average")
    private Double mRating;
    @SerializedName("release_date")
    private String mReleaseDate;
//    private ArrayList<MovieTrailer> mTrailers;
//    private ArrayList<MovieReview> mReviews;


    public Movie(int mId, String mTitle, String mImageSource, String mSynopsis, Double mRating, String mReleaseDate) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mImageSource = mImageSource;
        this.mSynopsis = mSynopsis;
        this.mRating = mRating;
        this.mReleaseDate = mReleaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mImageSource='" + mImageSource + '\'' +
                ", mSynopsis='" + mSynopsis + '\'' +
                ", mRating=" + mRating +
                ", mReleaseDate='" + mReleaseDate + '\'' +
//                ", mTrailers=" + mTrailers +
//                ", mReviews=" + mReviews +
                '}';
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmImageSource() {
        return mImageSource;
    }

    public void setmImageSource(String mImageSource) {
        this.mImageSource = mImageSource;
    }

    public String getmSynopsis() {
        return mSynopsis;
    }

    public void setmSynopsis(String mSynopsis) {
        this.mSynopsis = mSynopsis;
    }

    public Double getmRating() {
        return mRating;
    }

    public void setmRating(Double mRating) {
        this.mRating = mRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

//    public ArrayList<MovieTrailer> getmTrailers() {
//        return mTrailers;
//    }
//
//    public void setmTrailers(ArrayList<MovieTrailer> mTrailers) {
//        this.mTrailers = mTrailers;
//    }
//
//    public ArrayList<MovieReview> getmReviews() {
//        return mReviews;
//    }
//
//    public void setmReviews(ArrayList<MovieReview> mReviews) {
//        this.mReviews = mReviews;
//    }

    public static class MovieLists {
        private ArrayList<Movie> mResults;
        public ArrayList<Movie> getmResults() {
            return mResults;
        }

        public void setmResults(ArrayList<Movie> results) {
            this.mResults = results;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mTitle);
        dest.writeString(this.mImageSource);
        dest.writeString(this.mSynopsis);
        dest.writeDouble(this.mRating);
        dest.writeString(this.mReleaseDate);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        this.mId = in.readInt();
        this.mTitle = in.readString();
        this.mImageSource = in.readString();
        this.mSynopsis = in.readString();
        this.mRating = in.readDouble();
        this.mReleaseDate = in.readString();
    }

}
