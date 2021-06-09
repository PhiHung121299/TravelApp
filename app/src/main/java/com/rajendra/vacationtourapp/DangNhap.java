package com.rajendra.vacationtourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.HomePage.Fragment4;
import com.rajendra.vacationtourapp.HomePage.HomePage;
import com.rajendra.vacationtourapp.model.Nguoidung;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    Button buttonSingIn;
    private EditText username,
            password;
    DatabaseReference myData;
    Switch active;
    ArrayList<Nguoidung> dsData;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        init();
//        Query query = FirebaseDatabase.getInstance().getReference().child("NguoiDung").child(input1);
//        getThongtin(query);
        //controlDangNhap();
        //myData = FirebaseDatabase.getInstance().getReference().child("NguoiDung");
        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("NguoiDung").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = username.getText().toString();
                        String input2 = password.getText().toString();

                        if (dataSnapshot.child(input1).exists()) {
                            if (dataSnapshot.child(input1).child("matKhau").getValue(String.class).equals(input2)) {
                                Intent intent = new Intent(DangNhap.this, Fragment4.class);
                                intent.putExtra("id", input1);
                                startActivity(intent);
                                Toast.makeText(DangNhap.this, input1 + "", Toast.LENGTH_SHORT).show();

                                if (active.isChecked()) {
                                    if (dataSnapshot.child(input1).child("loai").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(DangNhap.this, true);
                                        preferences.setDataAs(DangNhap.this, "admin");
                                        startActivity(new Intent(DangNhap.this, AdminPage.class));
                                    } else if (dataSnapshot.child(input1).child("loai").getValue(String.class).equals("user")) {
                                        preferences.setDataLogin(DangNhap.this, true);
                                        preferences.setDataAs(DangNhap.this, "user");
                                        startActivity(new Intent(DangNhap.this, HomePage.class));
                                    }
                                } else {
                                    if (dataSnapshot.child(input1).child("loai").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(DangNhap.this, false);
                                        startActivity(new Intent(DangNhap.this, AdminPage.class));

                                    } else if (dataSnapshot.child(input1).child("loai").getValue(String.class).equals("user")) {
                                        preferences.setDataLogin(DangNhap.this, false);
                                        startActivity(new Intent(DangNhap.this, HomePage.class));
                                    }
                                }

                            } else {
                                Toast.makeText(DangNhap.this, "Mời nhập lại !", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DangNhap.this, "Data belum terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private void controlDangNhap() {
        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

    void init() {
        buttonSingIn = (Button) findViewById(R.id.buttonSingIn);
        username = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        active = findViewById(R.id.active);
    }

    private void getThongtin(Query query) {
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataS : snapshot.getChildren()) {
//                    Nguoidung top = dataS.getValue(Nguoidung.class);
//                    dsData.add(top);
                    Intent intent = new Intent(DangNhap.this, Fragment4.class);
                    intent.putExtra("thongtin", dataS.getValue(Nguoidung.class));
                    startActivity(intent);
                    Toast.makeText(DangNhap.this, "" + dataS.getValue(Nguoidung.class).getTenTaiKhoan(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DangNhap.this, "opss....................", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(this, AdminPage.class));
                finish();
            } else {
                startActivity(new Intent(this, HomePage.class));
                finish();
            }
        }
    }
}
