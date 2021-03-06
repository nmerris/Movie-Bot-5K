package com.nate.moviebot5k.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;

import com.nate.moviebot5k.FragmentMovieFiltersSpinner;
import com.nate.moviebot5k.R;
import com.nate.moviebot5k.data.MovieTheaterContract;

/**
 * A very simple spinner adapter that uses a simple_spinner_item layout.
 * Each view in the adapter is set with a tag that represents themoviedb genreId to
 * use for discovery api calls.
 *
 * Created by Nathan Merris on 5/13/2016.
 */
public class GenreSpinnerAdapter extends SimpleCursorAdapter {

    public GenreSpinnerAdapter(Context context) {
        super(context,
                R.layout.textview_spinner_item,
                null,
                new String[]{MovieTheaterContract.GenresEntry.COLUMN_NAME},
                new int[]{android.R.id.text1},
                0);
    }

    @Override
     public void bindView(View view, Context context, Cursor cursor) {
         // need genre id to be tagged with each genre view so I can update the sharedPrefs
         // key_movie_filter_genre_id value if the user selects a new genre from the spinner
         // (this happens in the spinners onItemSelected callback)
         // I played around with other ways to do this, like using a new cursor and trying to match
         // the selected genre spinner value (which is the genre name) to it's associated id in the db,
         // but finally realized it's much easier to just set a tag on each spinner textview..
        String genreIdString =
                String.valueOf(cursor.getInt(FragmentMovieFiltersSpinner.GENRE_TABLE_COLUMN_GENRE_ID));
         view.setTag(genreIdString);
         super.bindView(view, context, cursor);
     }

}
