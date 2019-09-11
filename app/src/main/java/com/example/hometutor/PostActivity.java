package com.example.hometutor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostActivity extends AppCompatActivity {
   RecyclerView recyclerView;
   ProgressBar progressBar;
   PostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        FirebaseApp.initializeApp(this);
        recyclerView=findViewById(R.id.recyclerview);
        progressBar=findViewById(R.id.progressbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new PostAdapter(this);
        recyclerView.setAdapter(adapter);
        loaddata();
    }

    private void loaddata() {
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("posts/");

        ref.orderByChild("owner").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clearall();
                if(dataSnapshot!=null){
                    System.out.println(dataSnapshot);
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Postmodel postmodel=snapshot.getValue(Postmodel.class);
                        postmodel.setPostid(snapshot.getKey());
                        adapter.additem(postmodel);
                    }
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(PostActivity.this,"Loading Failed "+databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });




    }
}
