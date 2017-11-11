package com.dmp.wherewasi.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by DomenicPolidoro on 11/11/17.
 *
 * To facilitate OOP.
 */

public class Location {

    private int locationID;
    private String locationName;
    private String locationCategory;
    private String locationDistance;
    private String locationNotes;
    private String locationLat;
    private String locationLong;

    public Location() {}

    public Location(int locationID, String locationName, String locationCategory, String locationDistance, String locationNotes, String locationLat, String locationLong) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.locationCategory = locationCategory;
        this.locationDistance = locationDistance;
        this.locationNotes = locationNotes;
        this.locationLat = locationLat;
        this.locationLong = locationLong;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationCategory() {
        return locationCategory;
    }

    public void setLocationCategory(String locationCategory) {
        this.locationCategory = locationCategory;
    }

    public String getLocationDistance() {
        return locationDistance;
    }

    public void setLocationDistance(String locationDistance) {
        this.locationDistance = locationDistance;
    }

    public String getLocationNotes() {
        return locationNotes;
    }

    public void setLocationNotes(String locationNotes) {
        this.locationNotes = locationNotes;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(String locationLong) {
        this.locationLong = locationLong;
    }

    //TODO resolve distance based on user location
    public int getDistance(LatLng latLng) {
        return 0;
    }
}
