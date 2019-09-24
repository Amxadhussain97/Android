package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

public class Student_Dashboard extends AppCompatActivity implements View.OnClickListener {
    private CardView s_my_account,s_my_message,s_my_post,s_search_teacher,s_create_post,s_signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__dashboard);
        FirebaseApp.initializeApp(this);
        s_my_account = findViewById(R.id.my_account);
        s_my_message = findViewById(R.id.my_message);
        s_my_post = findViewById(R.id.my_post);
        s_search_teacher = findViewById(R.id.search_teacher);
        s_signout = findViewById(R.id.sign_out);
        s_create_post = findViewById(R.id.create_post);
        s_my_account.setOnClickListener(this);
        s_my_message.setOnClickListener(this);
        s_my_post.setOnClickListener(this);
        s_search_teacher.setOnClickListener(this);
        s_signout.setOnClickListener(this);
        s_create_post.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_account:
            {
                Intent intent = new Intent(getApplicationContext(),student_profile.class);
                startActivity(intent);
                break;
            }
            case R.id.my_message:
            {
                break;
            }
            case R.id.my_post:
            {
                Intent intent = new Intent(getApplicationContext(),PostActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.search_teacher:
            {
                Intent intent = new Intent(getApplicationContext(),fragment_users.class);
                startActivity(intent);
                break;
            }
            case R.id.create_post:
            {
                Intent intent = new Intent(getApplicationContext(),create_post_view.class);
                startActivity(intent);
                break;
            }
            case R.id.sign_out:
            {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }


}
