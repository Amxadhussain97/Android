package com.example.hometutor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {
        Postmodel postmodel=getitem(position);
        holder.gender.setText(postmodel.getGender());
        holder.name.setText(postmodel.getPoster());
        holder.location.setText(postmodel.getLocation());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    public Postmodel getitem(int p){
        return posts.get(p);
    }

    public class Myviewholder extends RecyclerView.ViewHolder{
        TextView name,salary,clas,location,gender;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.card_name);
            salary=itemView.findViewById(R.id.card_salary);
            clas=itemView.findViewById(R.id.card_class);
            location=itemView.findViewById(R.id.card_location);
            gender=itemView.findViewById(R.id.card_gender);
            itemView.findViewById(R.id.msgbtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
/* deleting a post
                  String id=getitem(getAdapterPosition()).getPostid();
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/posts/"+id);
                    ref.removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if(databaseError!=null)
                            Toast.makeText(mcContext,"Delete Succesfull",Toast.LENGTH_SHORT).show();
                            else
                            Toast.makeText(mcContext,"Erro While Deleting",Toast.LENGTH_SHORT).show();
                        }
                    });
                    */




                    String abc=getitem(getAdapterPosition()).getOwner();
                    Intent intent=new Intent(mcContext,create_post_view.class);
                    intent.putExtra("owner",abc);
                    mcContext.startActivity(intent);
                }
            });


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
