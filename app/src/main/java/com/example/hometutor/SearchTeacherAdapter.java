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

public class SearchTeacherAdapter extends RecyclerView.Adapter<SearchTeacherAdapter.ViewHolder> {
    private Context mContext;
   // private List<SearchTeacherModel>mUsers;
    ArrayList<SearchTeacherModel> mUsers=new ArrayList<>();

    public SearchTeacherAdapter(Context mContext) {
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
        final SearchTeacherModel user =getItem(position);
        holder.username.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Teacherprofile.class);
                intent.putExtra("userid",user.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
    public SearchTeacherModel getItem(int p) {
        return mUsers.get(p);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username,message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.teacher_name_id);

        }
    }
    public void additem(SearchTeacherModel model){
        mUsers.add(model);
    }
    public void clearall(){
        mUsers.clear();
        notifyDataSetChanged();
    }

}
