package com.dmp.wherewasi.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmp.wherewasi.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by DomenicPolidoro on 11/11/17.
 */

public class MyMapFragment extends Fragment implements OnMapReadyCallback {

    public MyMapFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_map, container, false);

        linkUIElements(v);
        return v;
    }

    private void linkUIElements(View root) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
