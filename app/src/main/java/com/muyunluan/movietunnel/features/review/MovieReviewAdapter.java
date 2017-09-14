package com.muyunluan.movietunnel.features.review;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.model.review.MovieReview;

import java.util.ArrayList;

/**
 * Created by Fei Deng on 8/22/17.
 * Copyright (c) 2017 Muyunluan. All rights reserved.
 */

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.ReviewViewHolder> {
    private static final String TAG = MovieReviewAdapter.class.getSimpleName();

    private ArrayList<MovieReview> mMovieReviews = new ArrayList<>();

    public MovieReviewAdapter(ArrayList<MovieReview> movieReviews) {
        this.mMovieReviews = movieReviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.fragment_movie_review_item, parent, false);

        ReviewViewHolder holder = new ReviewViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        MovieReview movieReview = mMovieReviews.get(position);

        holder.authorTv.setText(movieReview.getmAuthor());
        holder.contentTv.setText(movieReview.getmContent());
    }

    @Override
    public int getItemCount() {
        return mMovieReviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView authorTv;
        private TextView contentTv;

        public ReviewViewHolder(View itemView) {
            super(itemView);

            authorTv = (TextView) itemView.findViewById(R.id.tv_author);
            contentTv = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
