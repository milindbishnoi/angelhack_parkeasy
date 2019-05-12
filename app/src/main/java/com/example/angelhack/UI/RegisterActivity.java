package com.example.angelhack.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angelhack.DataModels.User;
import com.example.angelhack.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsername, etPassword, etConfirm, etMail, etContact, etOtp;
    Button btnSignup, btnSubmit;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String otp, id;
    Random random;
    static boolean check;
    public String username, email, password, confirm, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        etMail = findViewById(R.id.etMail);
        etContact = findViewById(R.id.etContact);
        btnSignup = findViewById(R.id.btnRegister);
        btnSubmit = findViewById(R.id.btnSubmit);
        etOtp = findViewById(R.id.etOtp);
        firebaseAuth = FirebaseAuth.getInstance();
        etOtp.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(this);

    }
    public void register(View view){

        username = etUsername.getText().toString().trim();
        email = etMail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirm = etConfirm.getText().toString().trim();
        contact = etContact.getText().toString().trim();
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(email)||TextUtils.isEmpty(contact))
            Toast.makeText(this,"Please enter valid inputs",Toast.LENGTH_SHORT).show();
        else if(!(password.equals(confirm)))
            Toast.makeText(this,"Passwords don't match",Toast.LENGTH_SHORT).show();
        else if(true) {
            firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                @Override
                public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                    check = task.getResult().getSignInMethods().isEmpty();
                    if (!check) {
                        Toast.makeText(RegisterActivity.this, "E Mail already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            if (check) {
                SmsManager manager = SmsManager.getDefault();
                int random = new Random().nextInt(9000) + 1000;
                id = Integer.toString(random);
                manager.sendTextMessage(contact, null, id, null, null);
                Toast.makeText(RegisterActivity.this, "OTP sent", Toast.LENGTH_SHORT).show();
                etOtp.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.VISIBLE);
            }
        }

    }
    public void submit(View view){
        String enteredOTP = etOtp.getText().toString().trim();
        if(enteredOTP.equals(id)){
            progressDialog.setTitle("Registering..");
            progressDialog.setMessage("Please wait while we are setting things up :)");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this,"Registration successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(RegisterActivity.this,"An error occurred. Please try again later.",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            Toast.makeText(RegisterActivity.this,"Invalid OTP",Toast.LENGTH_SHORT).show();
    }

}