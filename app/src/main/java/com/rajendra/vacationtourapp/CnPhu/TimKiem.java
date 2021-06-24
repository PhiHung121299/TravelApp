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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.rajendra.vacationtourapp.adapter.AdapterSeachNhaNghi;
import com.rajendra.vacationtourapp.model.DiaDiem;
import com.rajendra.vacationtourapp.model.NhaNghi;

import java.util.ArrayList;
import java.util.List;

public class TimKiem extends AppCompatActivity {
    TextView cancel;
    EditText edtSeach;
    ImageView btSeach;
    RecyclerView mResultList;
    DatabaseReference mDatabase;
    AdapterSeach adapter;
    AdapterSeachNhaNghi adapterSeachNhaNghi;
    Spinner spnCategory;
    String loai = "Địa điểm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        anhxa();
        FirebaseRecyclerOptions<DiaDiem> options =
                new FirebaseRecyclerOptions.Builder<DiaDiem>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TravelLocation"), DiaDiem.class)
                        .build();
        adapter = new AdapterSeach(options);
        mResultList.setAdapter(adapter);
        Hientheoloai();
        ButtomOnclick();
        TaoSpiner();

    }

    private void TaoSpiner() {

        List<String> list = new ArrayList<>();
        list.add("Địa điểm");
        list.add("Nhà nghỉ");
        ArrayAdapter<String> adapterspin = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapterspin.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCategory.setAdapter(adapterspin);
    }

    private void ButtomOnclick() {
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

    }

    private void Hientheoloai() {
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                loai = spnCategory.getSelectedItem().toString();
                Toast.makeText(TimKiem.this, loai, Toast.LENGTH_SHORT).show();


                if (loai.equals("Địa điểm")) {

                    FirebaseRecyclerOptions<DiaDiem> options =
                            new FirebaseRecyclerOptions.Builder<DiaDiem>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("TravelLocation"), DiaDiem.class)
                                    .build();

                    adapter = new AdapterSeach(options);
                    mResultList.setAdapter(adapter);

                } else if (loai.equals("Nhà nghỉ")) {

                    FirebaseRecyclerOptions<NhaNghi> options =
                            new FirebaseRecyclerOptions.Builder<NhaNghi>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("NhaNghi"), NhaNghi.class)
                                    .build();

                    adapterSeachNhaNghi = new AdapterSeachNhaNghi(options);
                    mResultList.setAdapter(adapterSeachNhaNghi);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        spnCategory = (Spinner) findViewById(R.id.spnCategory);
        cancel = (TextView) findViewById(R.id.cancel);
        btSeach = (ImageView) findViewById(R.id.btseach);
        edtSeach = (EditText) findViewById(R.id.editSeach);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

    }

    private void processsearch(String s) {
        if (loai.equals("Địa điểm")) {
            FirebaseRecyclerOptions<DiaDiem> options =
                    new FirebaseRecyclerOptions.Builder<DiaDiem>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("TravelLocation")
                                    .orderByChild("title").startAt(s).endAt(s + "\uf8ff"), DiaDiem.class)
                            .build();

            adapter = new AdapterSeach(options);
            adapter.startListening();
            mResultList.setAdapter(adapter);

        } else if (loai.equals("Nhà nghỉ")) {
            FirebaseRecyclerOptions<NhaNghi> options =
                    new FirebaseRecyclerOptions.Builder<NhaNghi>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("NhaNghi")
                                    .orderByChild("ten").startAt(s).endAt(s + "\uf8ff"), NhaNghi.class)
                            .build();

            adapterSeachNhaNghi = new AdapterSeachNhaNghi(options);
            adapterSeachNhaNghi.startListening();
            mResultList.setAdapter(adapterSeachNhaNghi);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }
}