package com.example.vasu.moviebookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName , tvEmail , tvType ;

    CircleImageView profile_photo ;

    Button logout ;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences("MyData" , MODE_PRIVATE) ;
        editor = sharedPreferences.edit();

        tvName = findViewById(R.id.textViewName);
        tvEmail = findViewById(R.id.textViewEmail);
        tvType = findViewById(R.id.textViewType);

        logout = findViewById(R.id.buttonLogout);

        profile_photo = findViewById(R.id.profile_image);

        tvName.setText(sharedPreferences.getString("Name" , ""));
        tvEmail.setText(sharedPreferences.getString("Email" , ""));
        tvType.setText(sharedPreferences.getString("Type" , ""));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("LoginStatus" , "no") ;
                editor.commit();
                startActivity(new Intent(ProfileActivity.this , MainActivity.class));
                finish();
            }
        });

    }
}
