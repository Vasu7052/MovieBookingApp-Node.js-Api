package com.example.vasu.moviebookingapp.helper;

import com.example.vasu.moviebookingapp.Model.MoviesResponse;
import com.example.vasu.moviebookingapp.Model.Users;
import com.example.vasu.moviebookingapp.Model.UsersResponse;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Vasu on 04-10-2017.
 */

public interface ApiInterface {

    @GET("api/users/all/")
    Call<UsersResponse> getAllUsersData() ;

    @GET("api/users/byId/{id}")
    Call<UsersResponse> getAllUsersById(@Path("id") String id) ;

    @GET("api/users/byEmailPass/{email}&{password}")
    Call<UsersResponse> getAllUsersByEmailPass(@Path("email") String email , @Path("password") String password) ;

    @POST("api/users/add/")
    Call<UsersResponse> addUsersData(
            @Body Users users
    ) ;

    @FormUrlEncoded
    @PUT("api/genres/update/{id}")
    Call<UsersResponse> updateUsersData(
            @Path("id") String _id,
            @Field("name") String name
    ) ;

    @DELETE("api/genres/delete/{id}")
    Call<UsersResponse> deleteUsersData(@Path("id") String genreId);

    //============================================================================================================

    @GET("api/movies/all/")
    Call<MoviesResponse> getAllMoviesData() ;

    @FormUrlEncoded
    @POST("api/books/add/")
    Call<MoviesResponse> addMoviesData(
            @Field("title") String title,
            @Field("genre") String genre,
            @Field("decription") String decription,
            @Field("author") String author,
            @Field("publisher") String publisher,
            @Field("pages") String pages
    ) ;

    @FormUrlEncoded
    @PUT("api/books/update/{id}")
    Call<MoviesResponse> updateMoviesData(
            @Path("id") String _id,
            @Field("title") String title
    ) ;

    @DELETE("api/books/delete/{id}")
    Call<MoviesResponse> deleteMoviesData(@Path("id") String genreId);

}
