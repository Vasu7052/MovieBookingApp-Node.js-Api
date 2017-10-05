package com.example.vasu.moviebookingapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasu on 04-10-2017.
 */

public class MoviesResponse {

    @SerializedName("queriedMovies")
    private ArrayList<Movies> results;

    public ArrayList<Movies> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movies> results) {
        this.results = results;
    }

}
