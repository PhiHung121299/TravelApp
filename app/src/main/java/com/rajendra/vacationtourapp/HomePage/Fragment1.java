package com.rajendra.vacationtourapp.HomePage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.rajendra.vacationtourapp.CnPhu.TimKiem;
import com.rajendra.vacationtourapp.DangNhap;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.adapter.RecentsAdapter;
import com.rajendra.vacationtourapp.adapter.TopDiaDiemAdapter;
import com.rajendra.vacationtourapp.model.DiaDiem;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopDiaDiemAdapter topDiaDiemAdapter;
    DatabaseReference myData;
    ArrayList<DiaDiem> dsData;
    TextView TextAllnoibat;
    TextView editSeach;
    ImageView imageViewDangNhap;
    public ChipNavigationBar chipNavigationBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_1, container, false);

        editSeach = view.findViewById(R.id.editSeach);
        // chipNavigationBar = view.findViewById(R.id.bottomBar);
        recentRecycler = view.findViewById(R.id.locationAnUong);
        imageViewDangNhap = view.findViewById(R.id.btDangNhap);

        topPlacesRecycler = view.findViewById(R.id.TopDiaDiem);
        TextAllnoibat = view.findViewById(R.id.textAllnoibat);
        topPlacesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        dsData = new ArrayList<DiaDiem>();
        myData = FirebaseDatabase.getInstance().getReference().child("TravelLocation");
        //lấy mảng 4 phần tử có lượt bình chọn lớn hơn hoặc bằng 4.5 diểm đánh giá
        Query query = myData.limitToFirst(4).orderByChild("starRating").startAt(4.5);
        getTopDiaDiem(query);


        //
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);

            }
        });

        TextAllnoibat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  chipNavigationBar.setItemSelected(R.id.menuFavorite, true);
                getParentFragmentManager().beginTransaction().replace(R.id.container, new Fragment3()).addToBackStack(null).commit();
                //   HomePage.setFragmen();
            }
        });
        imageViewDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DangNhap.class);
                startActivity(intent);
            }
        });
        editSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TimKiem.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
        // Tìm kiếm
        return view;

    }

    private void getTopDiaDiem(Query query) {
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DiaDiem top = dataSnapshot.getValue(DiaDiem.class);
                    dsData.add(top);
                }
                topDiaDiemAdapter = new TopDiaDiemAdapter(getActivity(), dsData);
                topPlacesRecycler.setAdapter(topDiaDiemAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "opss....................", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void setRecentRecycler(List<RecentsData> recentsDataList) {
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
//        recentRecycler.setLayoutManager(layoutManager);
//        recentsAdapter = new RecentsAdapter(getActivity(), recentsDataList);
//        recentRecycler.setAdapter(recentsAdapter);
//    }

}
