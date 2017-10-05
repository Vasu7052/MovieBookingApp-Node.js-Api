package com.example.vasu.moviebookingapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vasu on 04-10-2017.
 */

public class Movies {

    @SerializedName("_id")
    private String _id;

    @SerializedName("title")
    private String title ;

    @SerializedName("year")
    private int year ;

    @SerializedName("genre")
    private String genre ;

    @SerializedName("description")
    private String description ;

    @SerializedName("director")
    private String director ;

    @SerializedName("stars")
    private String stars ;

    @SerializedName("imdbRating")
    private float imdbRating ;

    @SerializedName("image")
    private String image ;

    @SerializedName("create_date")
    private String create_date ;

    public Movies(String _id, String title, int year, String genre, String description,
                  String director, String stars, float imdbRating,String image, String create_date){
        this._id = _id;
        this.title = title ;
        this.year = year ;
        this.genre = genre ;
        this.description = description ;
        this.director = director ;
        this.stars = stars ;
        this.imdbRating = imdbRating ;
        this.image = image ;
        this.create_date = create_date ;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

}
