package com.example.hometutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class create_post_view extends AppCompatActivity {
   private TextView cp_salary,cp_class,cp_gender,cp_place;
   private String name;
   String current_u_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_view);
        cp_class = findViewById(R.id.p_class);
        cp_gender = findViewById(R.id.p_gender);
        cp_place = findViewById(R.id.p_place);
        cp_salary = findViewById(R.id.p_salary);
        current_u_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users/Names/"+FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null)
                {
                    name= dataSnapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        findViewById(R.id.postbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("/posts");
                Postmodel postmodel=new Postmodel(name,cp_place.getText().toString(),cp_gender.getText().toString(), cp_class.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getUid(),cp_salary.getText().toString(),System.currentTimeMillis());
                ref.push().setValue(postmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful()){
                     Toast.makeText(create_post_view.this,"Post succesful",Toast.LENGTH_SHORT).show();
                 }else
                        Toast.makeText(create_post_view.this,"Post failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
