package com.example.hometutor;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Teacherprofile extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherprofile);
        //startActivity(new Intent(Teacherprofile.this,MainActivity.class));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }
}
