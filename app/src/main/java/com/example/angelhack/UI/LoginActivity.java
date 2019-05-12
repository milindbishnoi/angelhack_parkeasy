package com.example.angelhack.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angelhack.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class LoginActivity extends AppCompatActivity {
    EditText etUser, etPass;
    Button btnSignin;
    TextView tvPrompt;
    private FirebaseAuth firebaseAuth1;
    ProgressDialog progressDialog1;
    static boolean check1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        tvPrompt = findViewById(R.id.tvPrompt);
        btnSignin = findViewById(R.id.btnSignin);
        firebaseAuth1 = FirebaseAuth.getInstance();
        progressDialog1 = new ProgressDialog(this);
    }
    public void loginUser(View view) {
        String user, pass;
        user = etUser.getText().toString().trim();
        pass = etPass.getText().toString().trim();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(user))
            Toast.makeText(this, "Please enter valid inputs", Toast.LENGTH_SHORT).show();
        else if (true) {
            firebaseAuth1.fetchSignInMethodsForEmail(user).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                @Override
                public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                    check1 = task.getResult().getSignInMethods().isEmpty();
                    if (check1) {
                        Toast.makeText(LoginActivity.this, "E-Mail address not found in our records", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            progressDialog1.setTitle("Loggin in");
            progressDialog1.setMessage("Please wait while we get a few things done...");
            progressDialog1.show();
            firebaseAuth1.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog1.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(LoginActivity.this,"An error occurred. Please try again later.",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void prompt(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
