package com.nate.moviebot5k;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by Nathan Merris on 5/28/2016.
 */
public class DialogFragmentFavoritesSortby extends DialogFragment
    implements DialogInterface.OnClickListener{
    private final String LOGTAG = ActivitySingleFragment.N8LOG + "DialogFragFavs";

    private SharedPreferences mSharedPrefs;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        builder.setTitle("Sort Favorites By")

            // set the item labels for the dialog content area radio button list
            // set the selected button to whatever is stored in sharedPrefs
            // and set this class to be the item click listener
            .setSingleChoiceItems(R.array.favorites_sortby_labels,
                    mSharedPrefs.getInt(getString(R.string.key_favorites_sortby_selected_item_position), 0),
                    this);

        return builder.create();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.e(LOGTAG, "entered onClick, int which is: " + which);

        // update sharedPrefs with new favorites sortby dialog label position and value
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putInt(getString(R.string.key_favorites_sortby_selected_item_position), which);
        editor.putString(getString(R.string.key_favorites_sortby_value),
                getResources().getStringArray(R.array.favorites_sortby_values)[which]);
        editor.commit();

        // callback to hosting Activity (which is ActivityFavorites)


        dismiss();
    }

}