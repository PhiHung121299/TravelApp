package com.rajendra.vacationtourapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.HomePage.Fragment1;
import com.rajendra.vacationtourapp.HomePage.TravelLocation;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.RecentsData;
import com.rajendra.vacationtourapp.model.TopPlacesData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TopPlacesAdapter extends RecyclerView.Adapter<TopPlacesAdapter.TopPlacesViewHolder> {
    private static final String TAG = "MyActivity";
    Context context;
    ArrayList<TopPlacesData> topPlacesDataList;

    public TopPlacesAdapter(Context context, ArrayList<TopPlacesData> topPlacesDataList) {
        this.context = context;
        this.topPlacesDataList = topPlacesDataList;
    }

    @NonNull
    @Override
    public TopPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_item, parent, false);

        // here we create a recyclerview row item layout file
        return new TopPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopPlacesViewHolder holder, final int position) {

        holder.setLocationData(topPlacesDataList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("thongtinchitiet1", topPlacesDataList.get(position).getTitle());
                i.putExtra("thongtinchitiet2", topPlacesDataList.get(position).getLocation());
                i.putExtra("thongtinchitiet3", topPlacesDataList.get(position).getImageUrl());
                i.putExtra("thongtinchitiet4", topPlacesDataList.get(position).getStarRating().toString());
                i.putExtra("tongquan", topPlacesDataList.get(position).getTongQuan());
                context.startActivity(i);
                //    Toast.makeText(context, listsp.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "------------------------------------------------------------------" +topPlacesDataList.get(position).getStarRating());
            }
        });

    }

    @Override
    public int getItemCount() {
        return topPlacesDataList.size();
    }

    public static final class TopPlacesViewHolder extends RecyclerView.ViewHolder {

        //        public String title,location,;
//        public Float starRating;
        ImageView imageUrl;
        TextView title, location, starRating;

        public TopPlacesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageUrl = itemView.findViewById(R.id.url_image);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            starRating = itemView.findViewById(R.id.starRating);

        }

        void setLocationData(TopPlacesData travelLocation) {
            Picasso.get().load(travelLocation.getImageUrl()).into(imageUrl);
            title.setText(travelLocation.getTitle());
            location.setText(travelLocation.getLocation());
            starRating.setText(String.valueOf(travelLocation.starRating));
        }

    }

}
