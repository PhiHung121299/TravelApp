package com.rajendra.vacationtourapp.CnPhu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.core.Context;
import com.rajendra.vacationtourapp.HomePage.HomePage;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.adapter.AdapterSeach;
import com.rajendra.vacationtourapp.model.DiaDiem;

public class TimKiem extends AppCompatActivity {
    TextView cancel;
    EditText edtSeach;
    ImageView btSeach;
    RecyclerView mResultList;
    DatabaseReference mDatabase;
    AdapterSeach adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        anhxa();
        FirebaseRecyclerOptions<DiaDiem> options =
                new FirebaseRecyclerOptions.Builder<DiaDiem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TravelLocation"), DiaDiem.class)
                        .build();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimKiem.this, HomePage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
            }
        });
        btSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String seachText = edtSeach.getText().toString();
                processsearch(seachText);
            }
        });


        adapter = new AdapterSeach(options);
        mResultList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void anhxa() {
        cancel = (TextView) findViewById(R.id.cancel);
        btSeach = (ImageView) findViewById(R.id.btseach);
        edtSeach = (EditText) findViewById(R.id.editSeach);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

    }

    private void processsearch(String s) {
        FirebaseRecyclerOptions<DiaDiem> options =
                new FirebaseRecyclerOptions.Builder<DiaDiem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TravelLocation").orderByChild("title").startAt(s).endAt(s + "\uf8ff"), DiaDiem.class)
                        .build();

        adapter = new AdapterSeach(options);
        adapter.startListening();
        mResultList.setAdapter(adapter);

    }
//    private void firebaseUserSearch(String searchText) {
//
//        Toast.makeText(TimKiem.this, "Started Search", Toast.LENGTH_LONG).show();
//
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("TravelLocation");
//        Query firebaseSearchQuery = mDatabase.orderByChild("title").startAt(searchText).endAt(searchText + "\uf8ff");
//        FirebaseRecyclerOptions<DiaDiem> options =
//                new FirebaseRecyclerOptions.Builder<DiaDiem>()
//                        .setQuery(firebaseSearchQuery, DiaDiem.class)
//                        .build();
//
//        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<DiaDiem, DiaDiemViewHolder>(options) {
//            @NonNull
//            @Override
//            public DiaDiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
//                return new DiaDiemViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull DiaDiemViewHolder holder, int position, @NonNull DiaDiem model) {
//
//            }
//        };
//        mResultList.setAdapter(adapter);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }
}