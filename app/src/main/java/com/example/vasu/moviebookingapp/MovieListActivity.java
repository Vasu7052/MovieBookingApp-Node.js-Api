package com.example.vasu.moviebookingapp;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.vasu.moviebookingapp.CustomAdapter.CustomAdapterForMovies;
import com.example.vasu.moviebookingapp.Model.Movies;
import com.example.vasu.moviebookingapp.Model.MoviesResponse;
import com.example.vasu.moviebookingapp.helper.ApiClient;
import com.example.vasu.moviebookingapp.helper.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    ArrayList<Movies> movieList = new ArrayList<>() ;
    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        lv = (ListView) findViewById(R.id.listView) ;

        getData();


    }

    public void getData(){

        apiService.getAllMoviesData().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()){
                    movieList = response.body().getResults();
                    lv.setAdapter(new CustomAdapterForMovies(MovieListActivity.this , R.layout.list_item_for_movies , movieList));
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            startActivity(new Intent(MovieListActivity.this , MainActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
