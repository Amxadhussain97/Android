package com.example.hometutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class message_activity extends AppCompatActivity {

    TextView m_username;
    CircleImageView profile_image;
    FirebaseUser fuser;
    DatabaseReference dbreference;
    ImageButton btn_send;
    EditText txt_send;
    MessageAdapter messageadapter;
    RecyclerView recyclerView;
    ArrayList<Chat>mchat=new ArrayList<>();
    Intent intent;
    String userid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_activity);
        FirebaseApp.initializeApp(this);
        btn_send = findViewById(R.id.msg_send_id);
        txt_send = findViewById(R.id.msg_edittext_id);
        m_username = findViewById(R.id.messege_ac_username_id);
        recyclerView = findViewById(R.id.msg_recycler_id);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        messageadapter=new MessageAdapter(mchat,this);
        recyclerView.setAdapter(messageadapter);
        intent =getIntent();
        userid = intent.getStringExtra("userid");
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = txt_send.getText().toString();
                if(!msg.equals(""))
                {
                  sendmessage(fuser.getUid(),userid,msg);
                }
                else
                {
                    Toast.makeText(message_activity.this,"You can't send an empty message",Toast.LENGTH_SHORT).show();
                }
                txt_send.setText("");
            }
        });
        dbreference = FirebaseDatabase.getInstance().getReference("/Users/All/"+userid);
        dbreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usermodel user = dataSnapshot.getValue(Usermodel.class);
                System.out.println(user.getName());
                m_username.setText(user.getName());
                readmessage(fuser.getUid(),userid);
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
     private void sendmessage(String sender, String receiver, String message)
     {
       DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
         HashMap<String,Object> hashmap =new HashMap<>();
         hashmap.put("sender",sender);
         hashmap.put("receiver",receiver);
         hashmap.put("message",message);
         ref.child("Chats").push().setValue(hashmap);
     }
     private  void readmessage(final String  myid , final String userid)
     {
         mchat = new ArrayList<>();
         dbreference = FirebaseDatabase.getInstance().getReference("Chats/");
         dbreference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 mchat.clear();
                 for(DataSnapshot snapshot : dataSnapshot.getChildren())
                 {
                     Chat chat = snapshot.getValue(Chat.class);
                     if((chat.getReceiver().equals(myid) && chat.getSender().equals(userid)) || (chat.getReceiver().equals(userid) && chat.getSender().equals(myid)))
                     {
                       mchat.add(chat);
                     }
                     messageadapter = new MessageAdapter(mchat,message_activity.this);
                     recyclerView.setAdapter(messageadapter);
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
     }

}
