package com.rajendra.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rajendra.vacationtourapp.adapter.RecentsAdapter;
import com.rajendra.vacationtourapp.adapter.TopDiaDiemAdapter;
import com.rajendra.vacationtourapp.model.RecentsData;
import com.rajendra.vacationtourapp.model.DiaDiem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopDiaDiemAdapter topDiaDiemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<RecentsData> recentsDataList = new ArrayList<>();

        setRecentRecycler(recentsDataList);

        ArrayList<DiaDiem> diaDiemList = new ArrayList<>();
        setTopPlacesRecycler(diaDiemList);
    }
    private void setRecentRecycler(List<RecentsData> recentsDataList) {
        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }
    private void setTopPlacesRecycler(ArrayList<DiaDiem> diaDiemList) {
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topDiaDiemAdapter = new TopDiaDiemAdapter(this, diaDiemList);
        topPlacesRecycler.setAdapter(topDiaDiemAdapter);
    }
}
