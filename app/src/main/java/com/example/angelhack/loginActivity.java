package com.example.angelhack;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class loginActivity extends AppCompatActivity {
    EditText etUser, etPass;
    Button btnSignin;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnSignin = findViewById(R.id.btnSignin);
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void loginUser(View view){
        String user, password;
        user = etUser.getText().toString().trim();
        password = etPass.getText().toString().trim();
        firebaseAuth.fetchSignInMethodsForEmail(user).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = task.getResult().getSignInMethods().isEmpty();
                if(check)
                    Toast.makeText(getApplicationContext(),"Email doest not match with out records",Toast.LENGTH_SHORT).show();
            }
        });

        if(TextUtils.isEmpty(user)||TextUtils.isEmpty(password))
                Toast.makeText(getApplicationContext(),"Please enter valid inputs",Toast.LENGTH_SHORT).show();
        else {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Sign-in");
            progressDialog.setMessage("Please wait while we get you logged in...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),profile_activity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
