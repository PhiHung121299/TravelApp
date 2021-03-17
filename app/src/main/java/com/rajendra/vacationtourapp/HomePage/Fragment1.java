package com.rajendra.vacationtourapp.HomePage;

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

import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.adapter.RecentsAdapter;
import com.rajendra.vacationtourapp.model.RecentsData;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_1, container, false);
        recentRecycler = view.findViewById(R.id.locationViewPager);
     //   ViewPager2 locationsViewPager = view.findViewById(R.id.locationViewPager);
        ViewPager2 locationsAnUong = view.findViewById(R.id.locationAnUong);
        List<TravelLocation> travelLocations = new ArrayList<>();
        TravelLocation travelLocationEiffelTower = new TravelLocation();
        travelLocationEiffelTower.imageUrl = "https://scontent.fhan3-1.fna.fbcdn.net/v/t1.0-9/58701294_2573804219512789_138328767616516096_o.jpg?_nc_cat=110&ccb=1-3&_nc_sid=730e14&_nc_ohc=xheaUq9OPs8AX_ASg6t&_nc_ht=scontent.fhan3-1.fna&oh=8ef19a01242899ea338376d9fb704e88&oe=606E4451";
        travelLocationEiffelTower.title = "Lâm Bình";
        travelLocationEiffelTower.location = "Khuổi Nhi";
        travelLocationEiffelTower.starRating = 4.8f;
        travelLocations.add(travelLocationEiffelTower);

        TravelLocation travelLocationMountainView = new TravelLocation();
        travelLocationMountainView.imageUrl = "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0";
        travelLocationMountainView.title = "Homestay Nậm Đíp";
        travelLocationMountainView.location = "Lăng Can, Lâm Bình, Tuyên Quang, Việt Nam";
        travelLocationMountainView.starRating = 4.5f;
        travelLocations.add(travelLocationMountainView);


//        locationsViewPager.setAdapter(new TraverlLocationAdapter(travelLocations));
//        locationsViewPager.setClipToPadding(false);
//        locationsViewPager.setClipChildren(false);
//        locationsViewPager.setOffscreenPageLimit(3);
//        locationsViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        locationsAnUong.setAdapter(new TraverlLocationAdapter(travelLocations));
        locationsAnUong.setClipToPadding(false);
        locationsAnUong.setClipChildren(false);
        locationsAnUong.setOffscreenPageLimit(3);
        locationsAnUong.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);

            }
        });
        //locationsViewPager.setPageTransformer(compositePageTransformer);

        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("AM Lake", "India", 4.8f, "https://scontent.fhan3-1.fna.fbcdn.net/v/t1.0-9/58701294_2573804219512789_138328767616516096_o.jpg?_nc_cat=110&ccb=1-3&_nc_sid=730e14&_nc_ohc=xheaUq9OPs8AX_ASg6t&_nc_ht=scontent.fhan3-1.fna&oh=8ef19a01242899ea338376d9fb704e88&oe=606E4451"));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", 4.9f, "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0"));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", 3.6f, "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0"));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", 4.2f, "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0"));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", 3.4f, "https://scontent.fhan4-1.fna.fbcdn.net/v/t1.0-9/128617160_776388316281778_1641442538226278373_n.jpg?_nc_cat=104&ccb=1-3&_nc_sid=8bfeb9&_nc_ohc=AFbmKau_XGwAX9HitBi&_nc_ht=scontent.fhan4-1.fna&oh=8ea3f9578b14d41d727929822f6c51fb&oe=606C96D0"));

        //        recentsDataList.add(new RecentsData("AM Lake", "India", "From $200", R.drawable.recentimage1));
//        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "From $300", R.drawable.recentimage2));
//        recentsDataList.add(new RecentsData("AM Lake", "India", "From $200", R.drawable.recentimage1));
//        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "From $300", R.drawable.recentimage2));
        setRecentRecycler(recentsDataList);
        // Inflate the layout for this fragment
        return view;


    }

    private void setRecentRecycler(List<RecentsData> recentsDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(getActivity(), recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);
    }

}