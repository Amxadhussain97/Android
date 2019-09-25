package com.example.hometutor;
import android.app.ProgressDialog;
import android.content.Intent;
//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText logInEmailEditText,logInPasswordEditText;
    private TextView registerTextView;
    private Button logInButton;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
       /* if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(this,teacher_dashboard.class));
            finish();
        }*/
       /* if (FirebaseAuth.getInstance().getCurrentUser() != null){

            Intent intent = new Intent(getApplicationContext(),Student_Dashboard.class);
            mProgress.dismiss();
            startActivity(intent);
            finish();
       . }*/
        mAuth = FirebaseAuth.getInstance();
        logInEmailEditText = findViewById(R.id.u_email_id);
        logInPasswordEditText = findViewById(R.id.password_id);
        registerTextView = findViewById(R.id.register_id);
        logInButton = findViewById(R.id.login_btn_id);
        logInButton.setOnClickListener(this);
        registerTextView.setOnClickListener(this);

        mProgress=new ProgressDialog(this);
        mProgress.setTitle("Loading...");
        mProgress.setMessage("Please Wait...");
    }

    @Override
    public void onClick(View v) {
        mProgress.show();
        switch (v.getId())
        {
            case R.id.login_btn_id:
                userLogin();
                break;
            case R.id.register_id:
                Intent intent = new Intent(getApplicationContext(),RegistrationForm.class);
                startActivity(intent);
                break;

        }
    }

    private void userLogin() {
        String email = logInEmailEditText.getText().toString().trim();
        String password = logInPasswordEditText.getText().toString().trim();

        if(email.isEmpty())
        {
            mProgress.dismiss();
            //Log.e("Error","Is here?");
            logInEmailEditText.setError("Enter an email address");
            logInEmailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mProgress.dismiss();
            logInEmailEditText.setError("Enter a valid email address");
            logInEmailEditText.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            mProgress.dismiss();
            logInPasswordEditText.setError("Enter a password");
            logInPasswordEditText.requestFocus();
        }
        if(password.length()<6)
        {
            mProgress.dismiss();
            logInPasswordEditText.setError("Minimum length of a password should be 6");
            logInPasswordEditText.requestFocus();
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    final String  current_u_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Student");
                    rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(current_u_id)) {
                                Intent intent = new Intent(getApplicationContext(),Student_Dashboard.class);
                                mProgress.dismiss();
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Intent intent = new Intent(getApplicationContext(),teacher_dashboard.class);
                                mProgress.dismiss();
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }
                else
                {
                    mProgress.dismiss();
                    Toast.makeText(getApplicationContext(), "No such user", Toast.LENGTH_LONG).show();
                    logInPasswordEditText.setText("");
                    logInEmailEditText.setText("");
                }
            }
        });
    }
}
