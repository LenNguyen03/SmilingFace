package com.se.smilingface;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class Register extends AppCompatActivity {

    Button btnSignIn, btnRegister;
    EditText edtName, edtEmail, edtPass, edtRePass;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth = FirebaseAuth.getInstance();
        mData = FirebaseDatabase.getInstance().getReference();

        AnhXa();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });

        DangNhap();
        
    }

    private void DangKy() {
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();
        String repass = edtRePass.getText().toString();
        String name = edtName.getText().toString();
        /// Reg ex checking Input ///////////////////////////////////
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Please enter name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password! ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, must be 6 digit !", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!repass.equals(password)){
            Toast.makeText(getApplicationContext(), "Password is duplicate !", Toast.LENGTH_SHORT).show();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
///////// Create Password and Email /////////////////////////
        mAuth.createUserWithEmailAndPassword(email, repass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = current_user.getUid();
                            HashMap<String, String> map = new HashMap<>();

                            map.put("name", name);
                            map.put("happy", 0 + "");
                            map.put("normal", 0 + "");
                            map.put("unhappy", 0 + "");
                            mData.child("Users").child(uid).setValue(map);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Register.this,"Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, com.se.smilingface.SignIn.class));
                        }else {
                            Toast.makeText(Register.this,"Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void DangNhap() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, com.se.smilingface.SignIn.class));
            }
        });
    }

    private void AnhXa() {
        btnSignIn = findViewById(R.id.btn_register_signin);
        btnRegister = findViewById(R.id.btn_register_register);
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email_register);
        edtPass = findViewById(R.id.edt_pass_register);
        edtRePass = findViewById(R.id.edt_repass);
        progressBar = findViewById(R.id.progressBar);
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}