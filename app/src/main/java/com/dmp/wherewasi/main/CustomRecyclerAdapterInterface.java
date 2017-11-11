package com.dmp.wherewasi.main;

import com.dmp.wherewasi.model.Location;

/**
 * Created by DomenicPolidoro on 11/11/17.
 */

public interface CustomRecyclerAdapterInterface {

    void onLocationSelected(Location l);

    void onLocationImageSelected(Location l);
}
