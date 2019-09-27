package com.example.hometutor;

import android.app.ProgressDialog;
import android.content.Intent;
//import android.support.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
    private Spinner disType,genderType,userType;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    String genderval,typeval,disval;
    String[] gender = { "Male", "Female"};
    String[] type = { "Teacher", "Student"};
    String[] district = { "Sylhet", "Dhaka","Barishal","Khulna","Chittagong","Mymensingh","Rajshahi","Rangpur"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        genderType =  findViewById(R.id.gendertype_spinner_id);
        userType =   findViewById(R.id.usetype_spinner_id);
        disType =   findViewById(R.id.district_spinner_id);
        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.textview_sample_id,gender);
        genderType.setAdapter(adapter);
        ArrayAdapter<String>  adapter2 = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.textview_sample_id,type);
        userType.setAdapter(adapter2);
        ArrayAdapter<String>  adapter3 = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.textview_sample_id,district);
        disType.setAdapter(adapter3);
        registerInstitutionEditText = findViewById(R.id.register_institution_id);
        registerAddressEditText = findViewById(R.id.register_address_id);
        registerFullNameEditText = findViewById(R.id.register_fullname_id);
        registerEmailEditText = findViewById(R.id.register_email_id);
        registerPhoneEditText = findViewById(R.id.register_phone_id);
        registerPasswordEditText = findViewById(R.id.register_password_id);
        registerConfirmPasswordEditText = findViewById(R.id.register_confirm_password_id);
        registerButton = findViewById(R.id.register_button_idd);
        registerButton.setOnClickListener(this);

        mProgress=new ProgressDialog(this);
        mProgress.setTitle("Loading...");
        mProgress.setMessage("Please Wait...");
    }

    @Override
    public void onClick(View v) {

        genderval = genderType.getSelectedItem().toString();
        typeval = userType.getSelectedItem().toString();
        disval = disType.getSelectedItem().toString();
        mProgress.show();
        switch (v.getId())
        {
            case R.id.register_button_idd:
                userregister();
                break;


        }
    }
    private void userregister() {
       // int gid = genderType.getCheckedRadioButtonId();
        //int tid = userType.getCheckedRadioButtonId();
        final String name = registerFullNameEditText.getText().toString().trim();
        final String institution= registerInstitutionEditText.getText().toString().trim();
        final String address= registerAddressEditText.getText().toString().trim();
        final String email = registerEmailEditText.getText().toString().trim();
        final String password = registerPasswordEditText.getText().toString().trim();
        final String phone = registerPhoneEditText.getText().toString().trim();
        String confirmpassword = registerConfirmPasswordEditText.getText().toString().trim();
        if(email.isEmpty())
        {
            mProgress.dismiss();
            registerEmailEditText.setError("Enter an email address");
            registerEmailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mProgress.dismiss();
            registerEmailEditText.setError("Enter a valid email address");
            registerEmailEditText.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            mProgress.dismiss();
            registerPasswordEditText.setError("Enter a password");
            registerPasswordEditText.requestFocus();
            return;
        }
        if(address.isEmpty())
        {
            mProgress.dismiss();
            registerAddressEditText.setError("Enter an Address");
            registerAddressEditText.requestFocus();
            return;
        }
        if(phone.isEmpty())
        {
            mProgress.dismiss();
            registerPhoneEditText.setError("Enter Your Phone Number");
            registerPhoneEditText.requestFocus();
            return;
        }
        if(confirmpassword.isEmpty())
        {
            mProgress.dismiss();
            registerConfirmPasswordEditText.setError("Enter a password");
            registerConfirmPasswordEditText.requestFocus();
            return;
        }
        if(!password.equals(confirmpassword))
        {
            mProgress.dismiss();
            registerConfirmPasswordEditText.setError("Password not matched");
            registerConfirmPasswordEditText.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            mProgress.dismiss();
            registerPasswordEditText.setError("Minimum length of a password should be 6");
            registerPasswordEditText.requestFocus();
            return;
        }
       /* if(gid==-1)
        {
            mProgress.dismiss();
            registerFemale.setError("Select a gender");
            return;
        }
        if(tid==-1)
        {
            mProgress.dismiss();
            registerTeacher.setError("Select a type");
            return;
        }*/
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mProgress.dismiss();
                    String user_id = mAuth.getCurrentUser().getUid();
                    Map newRoot = new HashMap();
                    newRoot.put("Name",name);
                    newRoot.put("EmailAddress",email);
                    newRoot.put("Password",password);
                    newRoot.put("Institution",institution);
                    newRoot.put("Address",address);
                    newRoot.put("Phone",phone);
                    newRoot.put("District",disval);
                    DatabaseReference current_user_db1 = FirebaseDatabase.getInstance().getReference()
                            .child("Users").child("Names").child(user_id);
                    current_user_db1.setValue(name);
                    if(genderval=="Female"){
                            newRoot.put("Gender","Female");
                        }
                    else
                        {
                            newRoot.put("Gender","Male");
                        }
                    DatabaseReference all_user_db = FirebaseDatabase.getInstance().getReference()
                            .child("Users").child("All").child(user_id);
                    newRoot.put("Id",user_id);
                    newRoot.put("Type",typeval);
                    all_user_db.setValue(newRoot);
                    if(typeval=="Teacher") {
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child("Teacher").child(user_id);
                        current_user_db.setValue(newRoot);
                    }
                    else
                    {
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference()
                                .child("Users").child("Student").child(user_id);
                        current_user_db.setValue(newRoot);
                    }

                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(RegistrationForm.this,"Registered successfully please check your email for verification",Toast.LENGTH_SHORT).show();

                                        registerInstitutionEditText.setText("");
                                        registerAddressEditText.setText("");
                                        registerFullNameEditText.setText("");
                                        registerEmailEditText.setText("");
                                        registerPhoneEditText.setText("");
                                        registerPasswordEditText.setText("");
                                        registerConfirmPasswordEditText.setText("");
                                        Intent intent = new Intent(getApplicationContext(),Login.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(RegistrationForm.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



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



                   // Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        mProgress.dismiss();
                        Toast.makeText(getApplicationContext(),"User is already registered",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        mProgress.dismiss();
                        Toast.makeText(getApplicationContext(),"Error: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

