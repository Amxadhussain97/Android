package com.example.hometutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_post_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_view);
        findViewById(R.id.postbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("/posts");
                Postmodel postmodel=new Postmodel("Ag maruf","Taltola","Male", FirebaseAuth.getInstance().getCurrentUser().getUid(),124223l,System.currentTimeMillis());
                ref.push().setValue(postmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                 if(task.isSuccessful()){
                     Toast.makeText(create_post_view.this,"Pos succesful",Toast.LENGTH_SHORT).show();
                 }else
                        Toast.makeText(create_post_view.this,"Pos failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
