package com.example.hometutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatFragment extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private ArrayList<ChatListModel>musers;
    UserAdapter adapter;
    ArrayList<String>userlist;
    FirebaseUser fuser;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_fragment);
        recyclerView = findViewById(R.id.chat_recycle_id);
        recyclerView.setHasFixedSize(true);
        adapter = new UserAdapter(this);
        progressBar = findViewById(R.id.chat_progressbar_id);
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        userlist = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        ref = FirebaseDatabase.getInstance().getReference("Chats/");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot  snapshot : dataSnapshot.getChildren())
                {
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getSender().equals(fuser.getUid()))
                    {
                        System.out.println(chat.getReceiver());
                        userlist.add(chat.getReceiver());
                    }
                    if(chat.getReceiver().equals(fuser.getUid()))
                    {
                        System.out.println(chat.getSender());
                        userlist.add(chat.getSender());
                    }
                }
                readchats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void readchats() {
        musers = new ArrayList<>();
       ref = FirebaseDatabase.getInstance().getReference("Users/All/");
      //  final Map<String,String> store = new HashMap<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                musers.clear();
                for(DataSnapshot  snapshot : dataSnapshot.getChildren()) {
                    ChatListModel user = snapshot.getValue(ChatListModel.class);
                    for(String id : userlist)
                    {
                        if(user.getId().equals(id))
                        {
                            adapter.additem(user);
                            break;
                            /*if(musers.size()!=0)
                            {
                                for(ChatListModel  user1 : musers)
                                {
                                    if(!user.getId().equals(user1.getId()))
                                    {
                                        musers.add(user1);
                                    }
                                }
                            }
                            else
                            {
                                musers.add(user);
                            }*/
                        }
                    }
                }
                //adapter  = new UserAdapter(musers,getApplicationContext());
              //  recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}



