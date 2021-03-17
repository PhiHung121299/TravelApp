package com.rajendra.vacationtourapp.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import me.ibrahimsn.lib.SmoothBottomBar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.rajendra.vacationtourapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        chipNavigationBar = findViewById(R.id.bottomBar);
        chipNavigationBar.setItemSelected(R.id.menuExplore, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment1()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.menuExplore:
                        fragment = new Fragment1();
                        break;
                    case R.id.menuSearch:
                        fragment = new Fragment2();
                        break;
                    case R.id.menuFavorite:
                        fragment = new Fragment3();
                        break;


                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
            }
        });
      //  ViewPager2 locationsViewPager = findViewById(R.id.locationViewPager);
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


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);

            }
        });
     //   locationsViewPager.setPageTransformer(compositePageTransformer);


    }


}