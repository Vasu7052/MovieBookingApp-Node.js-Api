package com.example.vasu.moviebookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vasu.moviebookingapp.Model.Users;
import com.example.vasu.moviebookingapp.Model.UsersResponse;
import com.example.vasu.moviebookingapp.helper.ApiClient;
import com.example.vasu.moviebookingapp.helper.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etName , etEmail , etPassword ;
    Button submit ;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sharedPreferences = getSharedPreferences("MyData" , MODE_PRIVATE) ;
        editor = sharedPreferences.edit();

        etName = (EditText) findViewById(R.id.register_form_name);
        etEmail = (EditText) findViewById(R.id.register_form_email);
        etPassword = (EditText) findViewById(R.id.register_form_password);

        submit = (Button) findViewById(R.id.register_form_submit_btn);

        etEmail.setText("@gmail.com");
        etPassword.setText("");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser() ;
            }
        });

    }

    public void registerUser(){

        Users temp = new Users();
        temp.setName(etName.getText().toString());
        temp.setEmail(etEmail.getText().toString());
        temp.setPassword(etPassword.getText().toString());
        temp.setType("Normal");

        apiService.addUsersData(temp).enqueue(new Callback<UsersResponse>() {
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
                Toast.makeText(RegisterActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
