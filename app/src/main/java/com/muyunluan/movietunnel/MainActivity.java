package com.muyunluan.movietunnel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.muyunluan.movietunnel.model.movie.Movie;
import com.muyunluan.movietunnel.features.details.MovieDetailsActivity;
import com.muyunluan.movietunnel.features.list.MovieListFragment;
import com.muyunluan.movietunnel.features.settings.SettingsActivity;
import com.muyunluan.movietunnel.utls.data.Constants;
import com.muyunluan.movietunnel.utls.data.MovieListType;

public class MainActivity extends AppCompatActivity implements
        MovieListFragment.OnListFragmentInteractionListener,
        SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static boolean isTablet = false;

    private SharedPreferences mSharedPreferences;
    private static String mSortStr = "popularity.desc";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Get value from SharedPreferences to set up
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSortStr = mSharedPreferences.getString(getString(R.string.pref_sort_key), getString(R.string.pref_sort_default_value));
        //Log.i(TAG, "onCreate: get sort value - " + mSortStr);

        if (!isOnline()) {
            Log.e(TAG, "onCreate: Error - No Internet Access");
            //Toast.makeText(this, R.string.error_internet, Toast.LENGTH_LONG).show();
            Snackbar.make(findViewById(R.id.fragment_container), R.string.error_internet, Snackbar.LENGTH_LONG).show();
        } else {
            // Only create new fragments when there is no previously saved state
            if (null == savedInstanceState) {
                // Use Now Playing as default
                MovieListFragment movieListFragment = MovieListFragment.newInstance(MovieListType.POPULAR, mSortStr);
                navigateFragment(movieListFragment);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register listener
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister listener
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_now_showing:
                    fragment = MovieListFragment.newInstance(MovieListType.NOW_PLAYING, mSortStr);
                    break;
                case R.id.navigation_top_rated:
                    fragment = MovieListFragment.newInstance(MovieListType.TOP_RATED, mSortStr);
                    break;
                case R.id.navigation_popular:
                    fragment = MovieListFragment.newInstance(MovieListType.POPULAR, mSortStr);
                    break;
                case R.id.navigation_favorite:
                    fragment = MovieListFragment.newInstance(MovieListType.FAVORITE, mSortStr);
                    break;
                case R.id.navigation_account:
                    break;
            }

            navigateFragment(fragment);

            return true;
        }

    };

    private void navigateFragment(Fragment fragment) {

        isTablet = getResources().getBoolean(R.bool.twoPaneMode);
        if (!isTablet) {
            if (null == fragment) {
                Snackbar.make(findViewById(R.id.fragment_container), R.string.empty_fragment, Snackbar.LENGTH_LONG).show();
                return;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            if (null == fragment) {
                Snackbar.make(findViewById(R.id.fragment_container_grid), R.string.empty_fragment, Snackbar.LENGTH_LONG).show();
                return;
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_grid, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return null != networkInfo && networkInfo.isConnectedOrConnecting();
    }


    @Override
    public void onListFragmentInteraction(Movie item) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Constants.ARG_MOVIE, item);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

}
