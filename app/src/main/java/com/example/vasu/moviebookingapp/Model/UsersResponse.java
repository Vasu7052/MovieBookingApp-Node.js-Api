package com.example.vasu.moviebookingapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasu on 04-10-2017.
 */

public class UsersResponse {

    @SerializedName("queriedUsers")
    private ArrayList<Users> results;

    public ArrayList<Users> getResults() {
        return results;
    }

    public void setResults(ArrayList<Users> results) {
        this.results = results;
    }

}
