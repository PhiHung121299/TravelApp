package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rajendra.vacationtourapp.HomePage.HomePage;
import com.rajendra.vacationtourapp.HomePage.TravelLocation;

public class DangNhap extends AppCompatActivity {
    Button buttonSingIn;

    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        init();
        controlDangNhap();
        myData = FirebaseDatabase.getInstance().getReference();
        TravelLocation travelLocation = new TravelLocation("Homestay A Phủ", "Nà Tông, Thượng Lâm, Lâm Bình,Tuyên Quang", "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0", 4.5f);
        myData.child("TravelLocation").push().setValue(travelLocation);

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
    }

}