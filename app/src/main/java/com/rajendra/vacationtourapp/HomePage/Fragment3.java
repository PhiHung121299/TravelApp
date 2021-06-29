package com.rajendra.vacationtourapp.HomePage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.adapter.AllDiaDiemAdapter;
import com.rajendra.vacationtourapp.model.DiaDiem;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {
    GridView gv_diadiem;
    ArrayList<DiaDiem> listdd;
    private static final String TAG = "ok";
    AllDiaDiemAdapter adapter;
    DatabaseReference myData;
    String nodekey;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        gv_diadiem = (GridView) view.findViewById(R.id.gv_diadiem);
        listdd = new ArrayList<DiaDiem>();
        myData = FirebaseDatabase.getInstance().getReference().child("TravelLocation");
        myData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DiaDiem top = dataSnapshot.getValue(DiaDiem.class);

                    listdd.add(top);
                }

                adapter = new AllDiaDiemAdapter(getContext(), R.layout.item_container_location, listdd);
                gv_diadiem.setAdapter(adapter);
                // setTopRecycler(dsLocationAnUong);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "opss....................", Toast.LENGTH_SHORT).show();
            }
        });
        gv_diadiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                // intent.putExtra("id", listsp.get(i).getMaSP());
                intent.putExtra("dsdd", listdd.get(i));
                startActivity(intent);

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of LoginFragment");
        super.onResume();
    }


}