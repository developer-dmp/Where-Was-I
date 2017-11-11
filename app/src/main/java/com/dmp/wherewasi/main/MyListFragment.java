package com.dmp.wherewasi.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmp.wherewasi.R;
import com.dmp.wherewasi.model.DBTools;
import com.dmp.wherewasi.model.Location;

import java.util.ArrayList;

/**
 * Created by DomenicPolidoro on 11/11/17.
 */

public class MyListFragment extends Fragment {

    private DBTools dbTools;
    private RecyclerView mRecyclerView;
    private ArrayList<Location> locations;

    public MyListFragment() {
        dbTools = DBTools.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_list, container, false);

        linkUIElements(v);
        configureRecyclerView();
        return v;
    }

    private void linkUIElements(View root) {
        mRecyclerView = (RecyclerView)root.findViewById(R.id.recyclerView);
    }

    private void configureRecyclerView() {
        locations = dbTools.getAllLocations();
    }
}
