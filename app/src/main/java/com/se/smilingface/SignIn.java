package com.se.smilingface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    Button btnRegister, btnSignIn;
    EditText edtEmail, edtPass;
    FirebaseAuth mAuth;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mAuth = FirebaseAuth.getInstance();

        AnhXa();
        DangKy();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });
    }

    private void DangNhap() {
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        progressBar2.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignIn.this, Face.class));
                        } else {
                            Toast.makeText(SignIn.this,"Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void DangKy() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar2.setVisibility(View.GONE);
                startActivity(new Intent(SignIn.this, Register.class));
            }
        });
    }

    private void AnhXa() {
        btnRegister = findViewById(R.id.btn_signin_register);
        btnSignIn = findViewById(R.id.btn_signin_signin);
        edtEmail = findViewById(R.id.edt_email_signin);
        edtPass = findViewById(R.id.edt_password_signin);
        progressBar2 = findViewById(R.id.progressBar2);
    }
}