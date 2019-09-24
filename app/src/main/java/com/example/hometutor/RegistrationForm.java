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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistrationForm extends AppCompatActivity implements View.OnClickListener {
    private EditText registerInstitutionEditText,registerFullNameEditText,registerEmailEditText,registerPasswordEditText,registerConfirmPasswordEditText;
    private TextView registerButton;
    private EditText registerPhoneEditText;
    private EditText registerAddressEditText;
    private RadioButton registerMale,registerFemale,registerTeacher,registerStudent;
    private RadioGroup genderType,userType;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        genderType = (RadioGroup) findViewById(R.id.gender_radiogroup_id);
        userType =  (RadioGroup) findViewById(R.id.usetype_radiogroup_id);
        registerInstitutionEditText = findViewById(R.id.register_institution_id);
        registerAddressEditText = findViewById(R.id.register_address_id);
        registerFullNameEditText = findViewById(R.id.register_fullname_id);
        registerEmailEditText = findViewById(R.id.register_email_id);
        registerPhoneEditText = findViewById(R.id.register_phone_id);
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
        int gid = genderType.getCheckedRadioButtonId();
        int tid = userType.getCheckedRadioButtonId();
        registerFemale = (RadioButton) findViewById(R.id.register_female_id);
        registerTeacher =(RadioButton)  findViewById(R.id.register_teacher_id);
        final String name = registerFullNameEditText.getText().toString().trim();
        final String institution= registerInstitutionEditText.getText().toString().trim();
        final String address= registerAddressEditText.getText().toString().trim();
        final String email = registerEmailEditText.getText().toString().trim();
        final String password = registerPasswordEditText.getText().toString().trim();
        final String phone = registerPhoneEditText.getText().toString().trim();
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
        if(address.isEmpty())
        {
            registerAddressEditText.setError("Enter an Address");
            registerAddressEditText.requestFocus();
            return;
        }
        if(phone.isEmpty())
        {
            registerPhoneEditText.setError("Enter Your Phone Number");
            registerPhoneEditText.requestFocus();
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

                    String user_id = mAuth.getCurrentUser().getUid();
                    Map newRoot = new HashMap();
                    newRoot.put("Name",name);
                    newRoot.put("EmailAddress",email);
                    newRoot.put("Password",password);
                    newRoot.put("Institution",institution);
                    newRoot.put("Address",address);
                    newRoot.put("Phone",phone);
                    DatabaseReference current_user_db1 = FirebaseDatabase.getInstance().getReference()
                            .child("Users").child("Names").child(user_id);
                    current_user_db1.setValue(name);
                    if(registerFemale.isChecked()){
                            newRoot.put("Gender","Female");
                        }
                    else
                        {
                            newRoot.put("Gender","Male");
                        }
                    DatabaseReference all_user_db = FirebaseDatabase.getInstance().getReference()
                            .child("Users").child("All").child(user_id);
                    newRoot.put("Id",user_id);
                    all_user_db.setValue(newRoot);
                    if(registerTeacher.isChecked()){
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference()
                                    .child("Users").child("Teacher").child(user_id);
                            current_user_db.setValue(newRoot);
                            Intent intent = new Intent(getApplicationContext(),teacher_dashboard.class);
                            startActivity(intent);
                        }
                    else
                        {
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Student").child(user_id);
                            current_user_db.setValue(newRoot);
                            Intent intent = new Intent(getApplicationContext(),Student_Dashboard.class);
                            startActivity(intent);
                        }

                   /* if(registerFemale.isChecked())
                    {
                        newRoot.put("Gender","Femalee");
                    }
                    else
                    {
                        newRoot.put("Gender","male");
                    }
                    if(registerTeacher.isChecked())
                    {
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child("Teacher").child(user_id);
                        current_user_db.setValue(newRoot).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   Intent intent = new Intent(getApplicationContext(),teacher_dashboard.class);
                                   startActivity(intent);
                               }else
                               {
                                   Toast.makeText(RegistrationForm.this,"Signup failed",Toast.LENGTH_SHORT).show();
                               }
                            }
                        });


                    }
                    else
                    {
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Student").child(user_id);
                        current_user_db.setValue(newRoot);
                        Intent intent = new Intent(getApplicationContext(),Student_Dashboard.class);
                        startActivity(intent);
                    }*/



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

