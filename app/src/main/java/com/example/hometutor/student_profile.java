package com.example.hometutor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class student_profile extends AppCompatActivity {
    private TextView st_name,st_phone,st_address,st_gender,st_institution;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        FirebaseApp.initializeApp(this);
        st_name = findViewById(R.id.sp_name);
        st_phone = findViewById(R.id.sp_phone);
        st_address = findViewById(R.id.sp_address);
        st_gender = findViewById(R.id.sp_gender);
        st_institution = findViewById(R.id.sp_institution);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/Users/Student/"+ FirebaseAuth.getInstance().getCurrentUser().getUid());
         ref.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if(dataSnapshot!=null)
                 {
                     st_name.setText(dataSnapshot.child("Name").getValue(String.class));
                     st_phone.setText(dataSnapshot.child("Phone").getValue(String.class));
                     st_gender.setText(dataSnapshot.child("Gender").getValue(String.class));
                     st_address.setText(dataSnapshot.child("Address").getValue(String.class));
                     st_institution.setText(dataSnapshot.child("Institution").getValue(String.class));
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

    }

}
