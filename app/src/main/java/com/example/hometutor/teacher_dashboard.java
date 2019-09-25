package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

public class teacher_dashboard extends AppCompatActivity implements View.OnClickListener{
    private CardView t_my_account,t_my_message,t_posts,t_search_teacher,t_create_post,t_signout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        FirebaseApp.initializeApp(this);
        t_my_account = findViewById(R.id.t_my_account_id);
        t_my_message = findViewById(R.id.t_my_message_id);
        t_signout = findViewById(R.id.t_sign_out_id);
        t_posts = findViewById(R.id.t_all_posts_id);

       t_my_account.setOnClickListener(this);
       t_my_message.setOnClickListener(this);
       t_posts.setOnClickListener(this);
       t_signout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t_my_account_id: {
                Intent intent = new Intent(getApplicationContext(), Teacherprofile.class);
                startActivity(intent);
                break;
            }
            case R.id.t_my_message_id: {
                break;
            }
            case R.id.t_all_posts_id: {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.t_sign_out_id: {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
                break;
                 /*Intent intent = new Intent(this,Login.class);
                startActivity(intent);
                //finish();
                break;*/
            }
        }
    }


}
