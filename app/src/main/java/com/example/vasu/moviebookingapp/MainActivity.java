package com.example.vasu.moviebookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vasu.moviebookingapp.Model.Users;
import com.example.vasu.moviebookingapp.Model.UsersResponse;
import com.example.vasu.moviebookingapp.helper.ApiClient;
import com.example.vasu.moviebookingapp.helper.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etEmail , etPassword ;
    Button submit ;
    Button newUser ;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    ArrayList<Users> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyData" , MODE_PRIVATE) ;
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("LoginStatus" , "").equals("yes")){
            startActivity(new Intent(MainActivity.this , MovieListActivity.class));
            finish();
        }

        etEmail = (EditText) findViewById(R.id.login_form_email);
        etPassword = (EditText) findViewById(R.id.login_form_password);

        newUser = findViewById(R.id.login_new_user_btn);

        submit = (Button) findViewById(R.id.login_form_submit_btn);

        etEmail.setText("vasu@gmail.com");
        etPassword.setText("vasu123");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials() ;
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , RegisterActivity.class));
            }
        });

    }

    public void checkCredentials(){


        apiService.getAllUsersByEmailPass(etEmail.getText().toString() , etPassword.getText().toString()).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()){
                    if (!response.body().getResults().isEmpty()){
                        getData();
                    }else{
                        Toast.makeText(MainActivity.this, "Wrong Email/Password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getData(){

        apiService.getUsersByEmail(etEmail.getText().toString()).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()){
                    userList = response.body().getResults();
                    editor.putString("Name" , userList.get(0).getName()) ;
                    editor.putString("Email" , userList.get(0).getEmail()) ;
                    editor.putString("Password" , userList.get(0).getPassword()) ;
                    editor.putString("Type" , userList.get(0).getType()) ;
                    editor.putString("LoginStatus" , "yes") ;
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this , MovieListActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
