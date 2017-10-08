package com.example.vasu.moviebookingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.vasu.moviebookingapp.Model.Users;
import com.example.vasu.moviebookingapp.Model.UsersResponse;
import com.example.vasu.moviebookingapp.helper.ApiClient;
import com.example.vasu.moviebookingapp.helper.ApiInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etName , etEmail , etPassword ;
    Button submit ;

    CircleImageView profile_photo ;
    ArrayList<String> photoPaths = new ArrayList<>() ;
    String finalPath = "" ;

    SweetAlertDialog pDialog ;

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

        profile_photo = findViewById(R.id.profile_image);

        submit = (Button) findViewById(R.id.register_form_submit_btn);

        etEmail.setText("@gmail.com");
        etPassword.setText("");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UploadPhoto().execute() ;
            }
        });

        profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilePickerBuilder.getInstance().setMaxCount(1)
                        .setActivityTheme(R.style.AppTheme)
                        .enableVideoPicker(false)
                        .enableImagePicker(true)
                        .enableCameraSupport(true)
                        .pickPhoto(RegisterActivity.this);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if(resultCode== Activity.RESULT_OK && data!=null)
                {
                    photoPaths = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);

                    if (photoPaths != null && !photoPaths.isEmpty()) {
                        for (String paths : photoPaths) {
                            finalPath = paths ;
                            break;
                        }
                    }

                }
                break;
        }

        if(!finalPath.equals("")) {
            Glide.with(this).load(Uri.fromFile(new File(finalPath))).apply(new RequestOptions()
                    .placeholder(R.drawable.profile_placeholder)).into(profile_photo);

        }

    }

    public void registerUser(String url){

        Users temp = new Users();
        temp.setName(etName.getText().toString());
        temp.setEmail(etEmail.getText().toString());
        temp.setPassword(etPassword.getText().toString());
        temp.setProfilePhoto(url);
        temp.setType("Normal");

        apiService.addUsersData(temp).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                    finish();
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

    class UploadPhoto extends AsyncTask<String, Void, Map> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Creating your Account");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected Map doInBackground(String... urls) {

            Map uploadResult = null ;

            Map config = new HashMap();
            config.put("cloud_name", "devsvasu");
            config.put("api_key", "152466958576313");
            config.put("api_secret", "COAkJW0AiUbzP3uxrUCA-YbeUo0");
            Cloudinary cloudinary = new Cloudinary(config);
            try {
                uploadResult =  cloudinary.uploader().upload(finalPath, ObjectUtils.emptyMap());
            } catch (Exception e) {
                Toast.makeText(RegisterActivity.this, ""+e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
                return null ;
            }

            return uploadResult ;

        }

        protected void onPostExecute(Map feed) {
            if (feed == null){
                Toast.makeText(RegisterActivity.this, "Please Try Again Later", Toast.LENGTH_SHORT).show();
            }else {
                String type = feed.get("resource_type").toString();
                String url = feed.get("url").toString();

                registerUser(url);

            }

            pDialog.dismissWithAnimation();

        }
    }

}
