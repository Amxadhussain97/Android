package com.example.hometutor;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    // private List<SearchTeacherModel>mUsers;
    ArrayList<ChatListModel> mUsers=new ArrayList<>();

    public UserAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ChatListModel user = getItem(position);
        holder.username.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmsg = new Intent(mContext, message_activity.class);
                intentmsg.putExtra("userid",user.getId());
                mContext.startActivity(intentmsg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
    public ChatListModel getItem(int p) {
        return mUsers.get(p);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.teacher_name_id);

        }
    }
    public void additem(ChatListModel model){
        mUsers.add(model);
    }
    public void clearall(){
        mUsers.clear();
        notifyDataSetChanged();
    }

}
