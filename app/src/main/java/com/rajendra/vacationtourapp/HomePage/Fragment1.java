package com.rajendra.vacationtourapp.HomePage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.provider.ContactsContract;
import android.renderscript.Script;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.DangNhap;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.adapter.RecentsAdapter;
import com.rajendra.vacationtourapp.adapter.TopPlacesAdapter;
import com.rajendra.vacationtourapp.model.RecentsData;
import com.rajendra.vacationtourapp.model.TopPlacesData;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    DatabaseReference myData;
    ArrayList<TopPlacesData> dsLocationAnUong;

    EditText editSeach;
    private Fragment fragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_1, container, false);

        editSeach = view.findViewById(R.id.editSeach);

        recentRecycler = view.findViewById(R.id.locationAnUong);
        topPlacesRecycler = view.findViewById(R.id.TopDiaDiem);
        topPlacesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        dsLocationAnUong = new ArrayList<TopPlacesData>();
        myData = FirebaseDatabase.getInstance().getReference().child("TravelLocation");
        myData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    TopPlacesData top = dataSnapshot.getValue(TopPlacesData.class);
                    dsLocationAnUong.add(top);
                }
                topPlacesAdapter = new TopPlacesAdapter(getActivity(), dsLocationAnUong);
                topPlacesRecycler.setAdapter(topPlacesAdapter);
                // setTopRecycler(dsLocationAnUong);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "opss....................", Toast.LENGTH_SHORT).show();
            }
        });
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);

            }
        });

        // Tìm kiếm

        //locationsViewPager.setPageTransformer(compositePageTransformer);
//        List<RecentsData> recentsDataList = new ArrayList<>();
//        recentsDataList.add(new RecentsData("AM Lake", "India", "https://scontent.fhan3-1.fna.fbcdn.net/v/t1.0-9/58701294_2573804219512789_138328767616516096_o.jpg?_nc_cat=110&ccb=1-3&_nc_sid=730e14&_nc_ohc=xheaUq9OPs8AX_ASg6t&_nc_ht=scontent.fhan3-1.fna&oh=8ef19a01242899ea338376d9fb704e88&oe=606E4451", 4.5f));
//        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0", 4.3f));
//        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0", 4.5f));
//        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0", 4.9f));
//        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0", 4.2f));
//        setRecentRecycler(recentsDataList);
        return view;

    }

    private void setRecentRecycler(List<RecentsData> recentsDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(getActivity(), recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);
    }


}