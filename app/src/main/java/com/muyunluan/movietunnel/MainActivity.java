package com.muyunluan.movietunnel;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.muyunluan.movietunnel.model.Movie;
import com.muyunluan.movietunnel.ui.movielist.MovieListFragment;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnListFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static boolean isTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (!isOnline()) {
            Log.e(TAG, "onCreate: Error - No Internet Access");
            Toast.makeText(this, R.string.error_internet, Toast.LENGTH_LONG).show();
        } else {
            // Only create new fragments when there is no previously saved state
            if (null == savedInstanceState) {
                MovieListFragment movieListFragment = MovieListFragment.newInstance();
                navigateFragment(movieListFragment);
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_now_showing:
                    fragment = MovieListFragment.newInstance();
                    break;
                case R.id.navigation_top_rated:
                    fragment = MovieListFragment.newInstance();
                    break;
                case R.id.navigation_popular:
                    fragment = MovieListFragment.newInstance();
                    break;
                case R.id.navigation_favorite:
                    fragment = MovieListFragment.newInstance();
                    break;
                case R.id.navigation_account:
                    fragment = MovieListFragment.newInstance();
                    break;
            }

            navigateFragment(fragment);

            return true;
        }

    };

    private void navigateFragment(Fragment fragment) {
        if (null == fragment) {
            //TODO: Snackbar display error msg
            return;
        }

        isTablet = getResources().getBoolean(R.bool.twoPaneMode);
        if (!isTablet) {
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
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

    }
}
