package com.example.hometutor;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teacherprofile extends AppCompatActivity implements View.OnClickListener
{
    TextView t_name,t_phone,t_gender,t_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherprofile);
        FirebaseApp.initializeApp(this);
        t_name=findViewById(R.id.p_name);
        t_phone=findViewById(R.id.p_mobile);
        t_address=findViewById(R.id.p_address);
        t_gender=findViewById(R.id.p_gender);
        loadprofileinfo();
    }
    private void loadprofileinfo() {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/Users/Teacher/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      if(dataSnapshot!=null){
                          t_name.setText(dataSnapshot.child("Name").getValue(String.class));
                          t_phone.setText(dataSnapshot.child("Phone").getValue(String.class));
                          t_gender.setText(dataSnapshot.child("Gender").getValue(String.class));
                          t_address.setText(dataSnapshot.child("Address").getValue(String.class));
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
