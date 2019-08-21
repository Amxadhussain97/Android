package com.example.hometutor;

import android.content.Intent;
//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegistrationForm extends AppCompatActivity implements View.OnClickListener {
    private EditText registerInstitutionEditText,registerFullNameEditText,registerEmailEditText,registerPasswordEditText,registerConfirmPasswordEditText;
    private Button registerButton;
    private RadioButton registerMale,registerFemale,registerTeacher,registerStudent;
    private RadioGroup genderType,userType;
    private int gid,tid;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        genderType = (RadioGroup) findViewById(R.id.gender_radiogroup_id);
        userType = findViewById(R.id.usetype_radiogroup_id);
        registerFemale = findViewById(R.id.register_female_id);
        registerTeacher = findViewById(R.id.register_teacher_id);
        registerStudent = findViewById(R.id.register_student_id);
        registerInstitutionEditText = findViewById(R.id.register_institution_id);
        registerFullNameEditText = findViewById(R.id.register_fullname_id);
        registerEmailEditText = findViewById(R.id.register_email_id);
        registerPasswordEditText = findViewById(R.id.register_password_id);
        registerConfirmPasswordEditText = findViewById(R.id.register_confirm_password_id);
        registerButton = findViewById(R.id.register_button_idd);
        registerButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_button_idd:
                userregister();
                break;


        }
    }

    private void userregister() {
        gid = genderType.getCheckedRadioButtonId();
        tid = userType.getCheckedRadioButtonId();
        String email = registerEmailEditText.getText().toString().trim();
        String password = registerPasswordEditText.getText().toString().trim();
        String confirmpassword = registerConfirmPasswordEditText.getText().toString().trim();
        if(email.isEmpty())
        {
            registerEmailEditText.setError("Enter an email address");
            registerEmailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            registerEmailEditText.setError("Enter a valid email address");
            registerEmailEditText.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            registerPasswordEditText.setError("Enter a password");
            registerPasswordEditText.requestFocus();
            return;
        }
        if(confirmpassword.isEmpty())
        {
            registerConfirmPasswordEditText.setError("Enter a password");
            registerConfirmPasswordEditText.requestFocus();
            return;
        }
        if(!password.equals(confirmpassword))
        {
            registerConfirmPasswordEditText.setError("Password not matched");
            registerConfirmPasswordEditText.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            registerPasswordEditText.setError("Minimum length of a password should be 6");
            registerPasswordEditText.requestFocus();
            return;
        }
        if(gid==-1)
        {
            registerFemale.setError("Select a gender");
            return;
        }
        if(tid==-1)
        {
            registerTeacher.setError("Select a type");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();
                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already registered",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

