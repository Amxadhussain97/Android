package com.example.hometutor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PostActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    PostAdapter adapter;
    String  userid,District;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        FirebaseApp.initializeApp(this);
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressbar);
        userid =FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        loaddata();
    }


    private void loaddata() {
        progressBar.setVisibility(View.VISIBLE);
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("posts/");

        final String  current_u_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Student");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                   @Override
                                                   public void onDataChange(@NonNull DataSnapshot  snapshot) {
                                                       if (snapshot.hasChild(current_u_id)) {
                                                           ref.orderByChild("owner").equalTo(userid).addValueEventListener(new ValueEventListener() {
                                                               @Override
                                                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                   adapter.clearall();
                                                                   if (dataSnapshot != null) {
                                                                       // Log.d("now","bitre dukse");
                                                                       //System.out.println(dataSnapshot);
                                                                       for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                                           Postmodel postmodel = snapshot.getValue(Postmodel.class);
                                                                           postmodel.setPostid(snapshot.getKey());
                                                                           adapter.additem(postmodel);
                                                                       }
                                                                       adapter.notifyDataSetChanged();
                                                                   }
                                                                   progressBar.setVisibility(View.GONE);

                                                               }

                                                               @Override
                                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                                               }
                                                           });
                                                       } else {

                                                           DatabaseReference refd=FirebaseDatabase.getInstance().getReference("Users/All/"+current_u_id);
                                                           refd.addValueEventListener(new ValueEventListener() {
                                                               @Override
                                                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                   if(dataSnapshot!=null )
                                                                   {
                                                                       for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                                           if (snapshot.getKey().equals("District")) {
                                                                               District = snapshot.getValue().toString();
                                                                           }
                                                                       }


                                                                   }
                                                               }

                                                               @Override
                                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                                               }
                                                           });
                                                           System.out.println(District);
                                                           ref.addValueEventListener(new ValueEventListener() {
                                                               @Override
                                                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                   adapter.clearall();
                                                                   if (dataSnapshot != null) {
                                                                       // Log.d("now","bitre dukse");
                                                                       //System.out.println(dataSnapshot);
                                                                       for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                                           Postmodel postmodel = snapshot.getValue(Postmodel.class);
                                                                           postmodel.setPostid(snapshot.getKey());
                                                                           adapter.additem(postmodel);
                                                                       }
                                                                       adapter.notifyDataSetChanged();
                                                                   }
                                                                   progressBar.setVisibility(View.GONE);

                                                               }

                                                               @Override
                                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                                               }
                                                           });
                                                       }

                                                   }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* Query query;
        query=ref.child("owner").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());*/




            }




}
