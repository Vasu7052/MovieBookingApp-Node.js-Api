package com.example.vasu.moviebookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vasu.moviebookingapp.Model.UsersResponse;
import com.example.vasu.moviebookingapp.helper.ApiClient;
import com.example.vasu.moviebookingapp.helper.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etName , etEmail , etPassword ;
    Button submit ;
    Button newUser ;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences("MyData" , MODE_PRIVATE) ;
        editor = sharedPreferences.edit();

        etName = (EditText) findViewById(R.id.login_form_name);
        etEmail = (EditText) findViewById(R.id.login_form_email);
        etPassword = (EditText) findViewById(R.id.login_form_password);

        newUser = findViewById(R.id.login_new_user_btn);

        submit = (Button) findViewById(R.id.login_form_submit_btn);

        etEmail.setText("@gmail.com");
        etPassword.setText("");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser() ;
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void registerUser(){

        apiService.addUsersData(etName.getText().toString(),etEmail.getText().toString(),etPassword.getText().toString(),"Normal").enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegisterActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });

    }
}
