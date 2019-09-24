package com.example.hometutor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Myviewholder> {
    ArrayList<Postmodel> posts=new ArrayList<>();
    Context mcContext;

    public PostAdapter(Context mcContext) {
        this.mcContext = mcContext;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcContext).inflate(R.layout.activity_post_view,parent,false);
        Myviewholder holder=new Myviewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull  Myviewholder holder, int position) {
        final Postmodel postmodel=getitem(position);
       // holder.gender.setText(postmodel.getGender());
        holder.name.setText(postmodel.getPoster());
        holder.upost.setText(postmodel.getPost());
        String  current_u_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(postmodel.getOwner().equals(current_u_id))
        {
            holder.delete.setVisibility(View.VISIBLE);
        }
        else holder.delete.setVisibility(View.GONE);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=postmodel.getPostid();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/posts/"+id);
                ref.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError!=null)
                            Toast.makeText(mcContext," Error While Deleting",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(mcContext,"Delete Succesfull",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcContext,Teacherprofile.class);
                intent.putExtra("userid",postmodel.getOwner());
                mcContext.startActivity(intent);
            }
        });
      //  holder.cllas.setText(postmodel.getClas());
       // holder.salary.setText(postmodel.getSalry());
       /* holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    public Postmodel getitem(int p){
        return posts.get(p);
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        TextView name,salary,cllas,location,gender,delete,message;
        TextView upost;
        //go
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            upost = itemView.findViewById(R.id.postid);
            name=itemView.findViewById(R.id.card_name);
            delete=itemView.findViewById(R.id.post_delete_id);
            message = itemView.findViewById(R.id.msgbtn_id);
           // final String abc=getitem(getAdapterPosition()).getOwner();
          /*itemView.findViewById(R.id.msgbtn_id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  String id=getitem(getAdapterPosition()).getPostid();
                   /* DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/posts/"+id);
                    ref.removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if(databaseError!=null)
                            Toast.makeText(mcContext,"Delete Succesfull",Toast.LENGTH_SHORT).show();
                            else
                            Toast.makeText(mcContext,"Erro While Deleting",Toast.LENGTH_SHORT).show();
                        }
                    });





                    String abc=getitem(getAdapterPosition()).getOwner();
                    Intent intent=new Intent(mcContext,Teacherprofile.class);
                    intent.putExtra("userid",abc);
                    mcContext.startActivity(intent);
                }
            }); */


        }
    }

    public void additem(Postmodel model){
        posts.add(model);
    }
    public void clearall(){
        posts.clear();
        notifyDataSetChanged();
    }

}
