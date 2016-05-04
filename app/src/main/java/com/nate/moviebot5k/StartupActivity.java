package com.nate.moviebot5k;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * Initializes this app the first time it is installed on device, and detects if internet is
 * available.  If internet is available, launches Intent to HomeActivity, if not it will check to
 * see if user has at least one favorite, and then asks user via an AlertDialog if they would like
 * to view their favorites.  If so, launches and Intent to FavoritesActivity.
 */
public class StartupActivity extends AppCompatActivity {
    private static final String LOGTAG = SingleFragmentActivity.N8LOG + "StartupActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOGTAG, "entered onCreate");

        initializeSharedPrefs();






//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);
//        finish();

    }


    // initialize all sharedPrefs, need this to happen the first time app is installed
    // or if user clears the app data, they will either ALL exist, or NONE will exist
    private void initializeSharedPrefs() {
        Log.i(LOGTAG, "entered initializeSharedPrefs, will report if they do not exist yet");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // if any single sharedPrefs exists, then they all do and have already been initialized
        if(!sharedPreferences.contains(getString(R.string.key_num_favorites))) {
            Log.i(LOGTAG, "  sharedPrefs are being created, writing defaults...");
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt(getString(R.string.key_num_favorites),
                    getResources().getInteger(R.integer.default_num_favorites));
            editor.putString(getString(R.string.key_movie_filter_sortby),
                    getString(R.string.default_movie_filter_sortby));
            editor.putInt(getString(R.string.key_movie_filter_year),
                    getResources().getInteger(R.integer.default_movie_filter_year));
            editor.putString(getString(R.string.key_movie_filter_cert),
                    getString(R.string.default_movie_filter_cert));
            editor.putString(getString(R.string.key_movie_filter_genre),
                    getString(R.string.default_movie_filter_genre));
            editor.putString(getString(R.string.key_favorites_sortby),
                    getString(R.string.default_favorites_sortby));
            editor.putInt(getString(R.string.key_currently_selected_movie_id),
                    getResources().getInteger(R.integer.default_currently_selected_movie_id));
            editor.putInt(getString(R.string.key_currently_selected_favorite_id),
                    getResources().getInteger(R.integer.default_currently_selected_favorite_id));
            editor.putBoolean(getString(R.string.key_fetch_new_movies),
                    getResources().getBoolean(R.bool.default_fetch_new_movies));
            editor.commit();
        }
    }


}
