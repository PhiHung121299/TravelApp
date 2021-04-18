package com.rajendra.vacationtourapp.HomePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rajendra.vacationtourapp.model.NhaNghi;
import com.rajendra.vacationtourapp.R;

public class Fragment2 extends Fragment {
    DatabaseReference myData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        // Inflate the layout for this fragment
//        myData = FirebaseDatabase.getInstance().getReference();
//        NhaNghi nn = new NhaNghi("NN01", "Tào Nghài", "4.5f", "Nà Tông, Thượng Lâm, Lâm Bình", "0392061716", "1", "abc");
//        myData.child("NhaNghi").push().setValue(nn);

        return view;
    }
}