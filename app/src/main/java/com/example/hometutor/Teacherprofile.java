package com.example.hometutor;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teacherprofile extends AppCompatActivity implements View.OnClickListener
{
    TextView unname,mobile,address,gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherprofile);
        unname=findViewById(R.id.p_uniname);
        mobile=findViewById(R.id.p_mobile);
        address=findViewById(R.id.p_address);
        gender=findViewById(R.id.p_gender);
        loadprofileinfo();
    }
    private void loadprofileinfo() {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/Users/Teacher/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      if(dataSnapshot!=null){
                          unname.setText(dataSnapshot.child("Institution").getValue(String.class));
                          mobile.setText(dataSnapshot.child("Email").getValue(String.class));
                          gender.setText(dataSnapshot.child("Gender").getValue(String.class));
                      }
                      else{
                          //user not exist
                      }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }
}
