package com.dmp.wherewasi.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmp.wherewasi.R;
import com.dmp.wherewasi.model.Location;

import java.util.ArrayList;

/**
 * Created by DomenicPolidoro on 11/11/17.
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private ArrayList<Location> locations;
    private CustomRecyclerAdapterInterface listener;

    public CustomRecyclerAdapter(ArrayList<Location> locations, CustomRecyclerAdapterInterface listener) {
        this.locations = locations;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_view_layout, parent, false);

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.locationName.setText(locations.get(position).getLocationName());
        holder.locationCategory.setText(locations.get(position).getLocationCategory());
        holder.locationDistance.setText(locations.get(position).getLocationDistance());
        holder.locationImageView.setImageDrawable(null);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView locationName;
        private TextView locationCategory;
        private TextView locationDistance;
        private ImageView locationImageView;

        public ViewHolder(final View card) {
            super(card);

            locationName = (TextView)card.findViewById(R.id.locationNameTextView);
            locationCategory = (TextView)card.findViewById(R.id.locationCategoryTextView);
            locationDistance = (TextView)card.findViewById(R.id.locationDistanceTextView);
            locationImageView = (ImageView)card.findViewById(R.id.locationImageView);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLocationSelected(locations.get(getAdapterPosition()));
                }
            });

            locationImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onLocationImageSelected(locations.get(getAdapterPosition()));
                }
            });
        }
    }
}
