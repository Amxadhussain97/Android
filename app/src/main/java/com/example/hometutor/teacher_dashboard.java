package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

public class teacher_dashboard extends AppCompatActivity implements View.OnClickListener{
    private TextView t_my_account,t_my_message,all_posts,t_search_teacher,t_create_post,t_signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        FirebaseApp.initializeApp(this);
        t_my_account = findViewById(R.id.my_account);
        t_my_message = findViewById(R.id.my_message);
        all_posts = findViewById(R.id.posts);
       /* s_my_post = findViewById(R.id.my_post);
        s_search_teacher = findViewById(R.id.search_teacher);
        s_signout = findViewById(R.id.sign_out);
        s_create_post = findViewById(R.id.create_post);
        s_my_account.setOnClickListener(this);
        s_my_message.setOnClickListener(this);
        s_my_post.setOnClickListener(this);
        s_search_teacher.setOnClickListener(this);
        s_signout.setOnClickListener(this);
        s_create_post.setOnClickListener(this);*/
       t_my_account.setOnClickListener(this);
       t_my_message.setOnClickListener(this);
       all_posts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_account: {
                Intent intent = new Intent(getApplicationContext(), Teacherprofile.class);
                startActivity(intent);
                break;
            }
            case R.id.my_message: {
                break;
            }
            case R.id.posts: {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);
            }
        }
    }


}
