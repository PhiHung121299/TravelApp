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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.adapter.TopDiaDiemAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    static  public ChipNavigationBar chipNavigationBar;
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


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);

            }
        });


    }

public static void setItemFragmen(){
    chipNavigationBar.setItemSelected(R.id.menuFavorite, true);
}

    @Override
    protected void onStart() {
        super.onStart();
    }
}