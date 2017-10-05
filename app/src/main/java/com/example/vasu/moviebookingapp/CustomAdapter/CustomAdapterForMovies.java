package com.example.vasu.moviebookingapp.CustomAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.vasu.moviebookingapp.Model.Movies;
import com.example.vasu.moviebookingapp.R;

import java.util.ArrayList;


/**
 * Created by VASU on 1/2/2017.
 */
public class CustomAdapterForMovies extends ArrayAdapter<Movies> implements View.OnClickListener,Filterable {

    private ArrayList<Movies> usersList;

    public CustomAdapterForMovies(Context context, int textViewResourceId,
                                  ArrayList<Movies> usersList) {
        super(context, textViewResourceId, usersList);
        this.usersList = new ArrayList<Movies>();
        this.usersList.addAll(usersList);
    }

    @Override
    public void onClick(View v) {

    }


    private class ViewHolder {
        public TextView name ;
        public TextView yearAndGenre ;
        public RatingBar rating ;
        public TextView description ;
        public TextView director ;
        public TextView stars ;
        public TextView imdbRating ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        final Movies users = usersList.get(position);

        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.list_item_for_movies, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(users.getName());

        return convertView;

    }




}
