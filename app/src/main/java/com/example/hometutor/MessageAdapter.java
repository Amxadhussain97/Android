package com.example.hometutor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MessageAdapter  extends RecyclerView.Adapter<MessageAdapter.Myviewholder> {
    ArrayList<Chat>  mChat=new ArrayList<>();
    Context mcContext;
    public  static  final  int MSG_TYPE_LEFT = 0;
    public  static  final  int MSG_TYPE_RIGHT = 1;
   FirebaseUser fuser;
    public MessageAdapter(ArrayList<Chat> mChat, Context mcContext) {
        this.mChat = mChat;
        this.mcContext = mcContext;
    }

    @NonNull
    @Override
    public MessageAdapter .Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mcContext).inflate(R.layout.chat_item_right, parent, false);
            MessageAdapter.Myviewholder holder = new MessageAdapter.Myviewholder(view);
            return holder;
        }
        else
        {
            View view = LayoutInflater.from(mcContext).inflate(R.layout.chat_item_left, parent, false);
            MessageAdapter.Myviewholder holder = new MessageAdapter.Myviewholder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.Myviewholder holder, int position) {
           Chat chat = mChat.get(position);
           holder.show_message.setText(chat.getMessage());
    }
    public class Myviewholder extends RecyclerView.ViewHolder{
        TextView show_message;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.msg_text_id);

        }
    }
    @Override
    public int getItemCount() {
        return mChat.size();
    }
    public void additem(Chat model){
        mChat.add(model);
    }
    public void clearall(){
        mChat.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else return MSG_TYPE_LEFT;
    }
}
