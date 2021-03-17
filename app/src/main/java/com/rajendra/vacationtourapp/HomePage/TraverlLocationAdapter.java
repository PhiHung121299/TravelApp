package com.rajendra.vacationtourapp.HomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.rajendra.vacationtourapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TraverlLocationAdapter extends RecyclerView.Adapter<TraverlLocationAdapter.TraverlLocationViewHolder> {
    private List<TravelLocation> travelLocations;

    public TraverlLocationAdapter(List<TravelLocation> travelLocations) {
        this.travelLocations = travelLocations;
    }

    @NonNull
    @Override
    public TraverlLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TraverlLocationViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_location,
                        parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TraverlLocationViewHolder holder, int position) {
        holder.setLocationData(travelLocations.get(position));
    }

    @Override
    public int getItemCount() {

        return travelLocations.size();
    }

    static class TraverlLocationViewHolder extends RecyclerView.ViewHolder {
        private KenBurnsView kbvLocation;
        private TextView textTitle, textLocation, textStarRating;

        public TraverlLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            kbvLocation = itemView.findViewById(R.id.kbvLocation);
            textTitle = itemView.findViewById(R.id.textTitle);
            textLocation = itemView.findViewById(R.id.textLocation);
            textStarRating = itemView.findViewById(R.id.textStarRating);

        }

        void setLocationData(TravelLocation travelLocation) {
            Picasso.get().load(travelLocation.imageUrl).into(kbvLocation);
            textTitle.setText(travelLocation.title);
            textLocation.setText(travelLocation.location);
            textStarRating.setText(String.valueOf(travelLocation.starRating));
        }
    }
}
