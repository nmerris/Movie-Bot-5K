package com.nate.moviebot5k;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Displays a simple radio button list of choices user can select to sort their locally saved
 * favorite movies in the GridView.  onFavoriteSortbyChanged callback is invoked when the user
 * actually changes their selection, so clicking on the item that was already selected will do nothing.
 * SharedPreferences for each sortby parameter are updated as needed.
 *
 * Created by Nathan Merris on 5/28/2016.
 */
public class DialogFragmentFavoritesSortby extends DialogFragment
    implements DialogInterface.OnClickListener{

    private final String LOGTAG = ActivitySingleFragment.N8LOG + "DialogFragFavs";
    private SharedPreferences mSharedPrefs;
    private Callbacks mCallbacks;


    /**
     * Required interface for any activity that hosts this fragment
     */
    public interface Callbacks {
        /**
         * Invoked when any favorites sorby parameter is changed by user.  The sortby must actually
         * change values in order for this callback to fire.
         */
        void onFavoriteSortbyChanged();
    }

    @Override
    public void onAttach(Context context) {
        // associate the fragment's mCallbacks object with the activity it was just attached to
        mCallbacks = (Callbacks) getActivity();
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        mCallbacks = null; // need to make sure this member variable is up to date with the correct activity
        // so nullify it every time this fragment gets detached from it's hosting activity
        super.onDetach();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        builder.setTitle(getString(R.string.favorites_sortby_dialog_title))
            .setSingleChoiceItems(
                    // set the item labels for the dialog content area radio button list
                    R.array.favorites_sortby_labels,
                    // set the selected button to whatever is stored in sharedPrefs
                    mSharedPrefs.getInt(getString(R.string.key_favorites_sortby_selected_item_position), 0),
                    // and set this class to be the item click listener
                    this);

        return builder.create();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        int positionBeforeClick = mSharedPrefs
                .getInt(getString(R.string.key_favorites_sortby_selected_item_position), 0);

        // only proceed if the position actually changed
        if(positionBeforeClick != which) {
            // update sharedPrefs with new favorites sortby dialog label position and value
            SharedPreferences.Editor editor = mSharedPrefs.edit();
            editor.putInt(getString(R.string.key_favorites_sortby_selected_item_position), which);
            editor.putString(getString(R.string.key_favorites_sortby_value),
                    getResources().getStringArray(R.array.favorites_sortby_values)[which]);
            editor.commit();

            // callback to hosting Activity (which is ActivityFavorites)
            mCallbacks.onFavoriteSortbyChanged();
        }
        // if no actual change in sortby, just dismiss the dialog
        dismiss();
    }

}
