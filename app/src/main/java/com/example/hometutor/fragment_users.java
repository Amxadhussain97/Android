package com.example.hometutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_users extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    SearchTeacherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_users);
        FirebaseApp.initializeApp(this);
        recyclerView = findViewById(R.id.search_teacher_recyclerview);
        progressBar = findViewById(R.id.search_teacher_progressbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchTeacherAdapter(this);
        recyclerView.setAdapter(adapter);
        loaddata();
        

    }

    private void loaddata() {
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users/Teacher/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null)
                {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        SearchTeacherModel tmodel=snapshot.getValue( SearchTeacherModel.class);
                        //System.out.println(postmodel);
                        tmodel.setPosstid(snapshot.getKey());
                        adapter.additem(tmodel);
                    }
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(fragment_users.this,"Loading Failed "+databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
