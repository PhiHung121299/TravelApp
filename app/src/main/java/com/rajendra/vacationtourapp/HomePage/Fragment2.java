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

import com.rajendra.vacationtourapp.ChitietNhaNghi;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.adapter.AllNhaNghiAdapter;
import com.rajendra.vacationtourapp.model.NhaNghi;
import com.rajendra.vacationtourapp.R;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    GridView gv_nhanghi;
    ArrayList<NhaNghi> listdd;
    AllNhaNghiAdapter adapter;
    DatabaseReference myData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        gv_nhanghi = (GridView) view.findViewById(R.id.gv_nhanghi);

        listdd = new ArrayList<NhaNghi>();
        myData = FirebaseDatabase.getInstance().getReference().child("NhaNghi");
        myData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    NhaNghi top = dataSnapshot.getValue(NhaNghi.class);
                    listdd.add(top);

                }
                adapter = new AllNhaNghiAdapter(getContext(), R.layout.item_container_location, listdd);
                gv_nhanghi.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "opss....................", Toast.LENGTH_SHORT).show();
            }
        });
        gv_nhanghi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ChitietNhaNghi.class);
                intent.putExtra("dsnn", listdd.get(i));
                startActivity(intent);
                Toast.makeText(getActivity(), "" + listdd.get(i).getTen(), Toast.LENGTH_SHORT).show();
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