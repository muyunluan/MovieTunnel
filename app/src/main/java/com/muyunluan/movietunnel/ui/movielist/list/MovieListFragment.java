package com.muyunluan.movietunnel.ui.movielist.list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muyunluan.movietunnel.R;
import com.muyunluan.movietunnel.model.movie.Movie;
import com.muyunluan.movietunnel.model.movie.MovieResponse;
import com.muyunluan.movietunnel.utls.data.Constants;
import com.muyunluan.movietunnel.utls.data.MovieListType;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiClient;
import com.muyunluan.movietunnel.utls.retrofit.RetrofitApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MovieListFragment extends Fragment {

    private static final String TAG = MovieListFragment.class.getSimpleName();
    private ArrayList<Movie> mMovieList = new ArrayList<>();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;

    private static MovieListType mListType;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieListFragment() {
    }

    public static MovieListFragment newInstance(MovieListType listType) {
        mListType = listType;

        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            getMoviesWithRetrofit(Constants.MOVIE_DATABASE_KEY);
        }
        return view;
    }


    public void getMoviesWithRetrofit(String apiKey) {
        if (TextUtils.isEmpty(apiKey)) {
            Log.e(TAG, "getMoviesWithRetrofit: invalid api key");
            return;
        }

        RetrofitApiInterface apiInterface =
                RetrofitApiClient.getBaseClient().create(RetrofitApiInterface.class);

        Call<MovieResponse> movieCall = null;

        if (mListType == null) {
            mListType = MovieListType.NOW_PLAYING;
        }

        switch (mListType) {
            case NOW_PLAYING:
                movieCall = apiInterface.getNowPlayingMovies(apiKey);
                break;
            case TOP_RATED:
                movieCall = apiInterface.getTopRatedMovies(apiKey);
                break;
            case POPULAR:
                movieCall = apiInterface.getPopularMovies(apiKey);
                break;
            case FAVORITE:
                movieCall = apiInterface.getTopRatedMovies(apiKey);
                break;
            default:
                movieCall = apiInterface.getNowPlayingMovies(apiKey);
                break;
        }

        movieCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                //Log.i(TAG, "onResponse: " + response.body().toString());
                mMovieList = response.body().getmResults();
                Log.i(TAG, "onResponse: get movie list - " + mMovieList.size());
                //Log.i(TAG, "onResponse: test first Movie object - " + mMovieList.get(0).toString());
                mRecyclerView.setAdapter(new MovieListRecyclerViewAdapter(mMovieList, mListener));
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
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Movie item);
    }
}
