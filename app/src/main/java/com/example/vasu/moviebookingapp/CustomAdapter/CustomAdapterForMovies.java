package com.example.vasu.moviebookingapp.CustomAdapter;

import android.content.Context;
import android.media.Image;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
        public ImageView image ;
        public TextView name ;
        public TextView yearAndGenre ;
        public RatingBar rating ;
        public TextView description ;
        public TextView director ;
        public TextView stars ;
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
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.name = (TextView) convertView.findViewById(R.id.title);
            holder.yearAndGenre = (TextView) convertView.findViewById(R.id.yearAndGenre);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.director = (TextView) convertView.findViewById(R.id.director);
            holder.stars = (TextView) convertView.findViewById(R.id.stars);
            holder.rating = (RatingBar) convertView.findViewById(R.id.ratingBar);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(users.getTitle());
        holder.yearAndGenre.setText(users.getYear() + "\n" + users.getGenre().trim());
        holder.description.setText(users.getDescription());
        holder.rating.setRating((users.getImdbRating()/(float)2));

        holder.director.setText(Html.fromHtml("<b>Director : </b> " + users.getDirector()));
        holder.stars.setText(Html.fromHtml("<b>Stars : </b> " + users.getStars()));

        Glide.with(getContext()).load(users.getImage()).into(holder.image) ;

        return convertView;

    }




}
