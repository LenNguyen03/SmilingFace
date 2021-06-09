package com.se.smilingface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

public class Face extends AppCompatActivity {

    ImageView imgH, imgN, imgU;
    Button btnFinish;
    int h = 0, n = 0, u = 0;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.face);

        AnhXa();
//////////////////// Sử dụng Real Time database của firebase /////////////////////////////////
        imgH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference data = db.getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("happy");
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            h = Integer.parseInt(snapshot.getValue().toString());
                            h++;
                            data.setValue(h);
                            Toast.makeText(Face.this,"Happy", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }
        });
//////////////////// Sử dụng Real Time database của firebase /////////////////////////////////
        imgN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference data = db.getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("normal");
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            n = Integer.parseInt(snapshot.getValue().toString());
                            n++;
                            data.setValue(n);
                            Toast.makeText(Face.this,"Normal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }
        });
//////////////////// Sử dụng Real Time database của firebase /////////////////////////////////
        imgU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference data = db.getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("normal");
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            u = Integer.parseInt(snapshot.getValue().toString());
                            u++;
                            data.setValue(u);
                            Toast.makeText(Face.this,"Unhappy", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Face.this, Face.class));
            }
        });

    }

    private void AnhXa() {
        imgH = findViewById(R.id.imgHappy);
        imgN = findViewById(R.id.imgNormal);
        imgU = findViewById(R.id.imgUnHappy);
        btnFinish = findViewById(R.id.btnFinish);
    }
}