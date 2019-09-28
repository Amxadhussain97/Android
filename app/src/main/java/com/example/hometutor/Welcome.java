package com.example.hometutor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Welcome extends AppCompatActivity  {
  LinearLayout lin1,lin2;
    Animation updown,downup;
    CardView gt;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        lin1= findViewById(R.id.l1);
        lin2= findViewById(R.id.l2);
        gt= findViewById(R.id.get_id);
        updown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        lin1.setAnimation(updown);
        lin2.setAnimation(downup);
        gt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.get_id)
                {
                   intent = new Intent(Welcome.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
