package com.example.hometutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetpassActivity extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressbar;
    private EditText text;
    private Button btn;
    FirebaseAuth fuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        text = findViewById(R.id.fp_email_id);
        btn = findViewById(R.id.fp_btn_id);
        progressbar = findViewById(R.id.fp_progress_id);
        fuser = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                fuser.sendPasswordResetEmail(text.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(ForgetpassActivity.this,"Please check your email to reset the password",
                                    Toast.LENGTH_LONG).show();


                        }
                        else
                        {
                            Toast.makeText(ForgetpassActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


    }
}
