package com.dmp.wherewasi.model;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dmp.wherewasi.R;

import java.util.ArrayList;

/**
 * Created by DomenicPolidoro on 11/11/17.
 */

public class DBTools extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "locations.db";
    private final static int DATABASE_VERSION = 1;

    public final static String TABLE_NAME_LOCATIONS = "locations";
    public final static String COL_LOCATION_ID = "ID";
    public final static String COL_LOCATION_NAME = "Name";
    public final static String COL_LOCATION_CATEGORY = "Category";
    public final static String COL_LOCATION_NOTES = "Notes";
    public final static String COL_LOCATION_LAT = "Latitude";
    public final static String COL_LOCATION_LONG = "Longitude";

    public final static String TABLE_NAME_CATEGORIES    = "categories";
    public final static String COL_CATEGORY_NAME        = "Name";

    private static DBTools mInstance = null;
    private static Context context;

    private ContentResolver mContentResolver;

    public static synchronized DBTools getInstance(Context c) {

        context = c;
        // Using application context, to ensure no context leak
        if (mInstance == null) {
            mInstance = new DBTools(c.getApplicationContext());
        }
        return mInstance;
    }

    public DBTools(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContentResolver = context.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String locationTable =
                "create table "+ TABLE_NAME_LOCATIONS +"("+
                        COL_LOCATION_ID +"           INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_LOCATION_NAME +"         TEXT NOT NULL, " +
                        COL_LOCATION_CATEGORY +"     TEXT NOT NULL, " +
                        COL_LOCATION_NOTES +"        TEXT, " +
                        COL_LOCATION_LAT +"          TEXT NOT NULL, " +
                        COL_LOCATION_LONG +"         TEXT NOT NULL)";

        String categoryTable =
                "create table "+TABLE_NAME_CATEGORIES+"("+
                        COL_CATEGORY_NAME+"     TEXT PRIMARY KEY)";


        sqLiteDatabase.execSQL(locationTable);
        sqLiteDatabase.execSQL(categoryTable);

        initCategories(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_NAME_LOCATIONS);
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_NAME_CATEGORIES);
        onCreate(sqLiteDatabase);
    }

    /*
        Initialization of the categories table.
     */
    private void initCategories(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(COL_CATEGORY_NAME, "Entertainment");
        db.insert(TABLE_NAME_CATEGORIES, null, values);

        values.put(COL_CATEGORY_NAME, "Night Life");
        db.insert(TABLE_NAME_CATEGORIES, null, values);

        values.put(COL_CATEGORY_NAME, "Food");
        db.insert(TABLE_NAME_CATEGORIES, null, values);

        values.put(COL_CATEGORY_NAME, "Car");
        db.insert(TABLE_NAME_CATEGORIES, null, values);

        values.put(COL_CATEGORY_NAME, "Other");
        db.insert(TABLE_NAME_CATEGORIES, null, values);
    }

    // ***************************** PROFILE TABLE HELPER METHODS *****************************
    /*
        Allows the user to insert a designated location.
     */
    public void insertLocation(Location l) {


        ContentValues values = new ContentValues();

        values.put(COL_LOCATION_NAME, l.getLocationName());
        values.put(COL_LOCATION_CATEGORY, l.getLocationCategory());
        values.put(COL_LOCATION_NOTES, l.getLocationNotes());
        values.put(COL_LOCATION_LAT, l.getLocationLat());
        values.put(COL_LOCATION_LONG, l.getLocationLong());

        SQLiteDatabase database = this.getWritableDatabase();

        database.insert(TABLE_NAME_LOCATIONS, null, values);
        database.close();
    }

    /*
        Perform update on a given location
     */
    public void updateLocation(Location l) {
        ContentValues values = new ContentValues();

        values.put(COL_LOCATION_NAME, l.getLocationName());
        values.put(COL_LOCATION_CATEGORY, l.getLocationCategory());
        values.put(COL_LOCATION_NOTES, l.getLocationNotes());
        values.put(COL_LOCATION_LAT, l.getLocationLat());
        values.put(COL_LOCATION_LONG, l.getLocationLong());

        SQLiteDatabase database = this.getWritableDatabase();
        database.update(TABLE_NAME_LOCATIONS, values, COL_LOCATION_ID +" = ?", new String[]{String.valueOf(l.getLocationID())});
    }

    /*
        Grabs all locations stored in the database and returns a list of them.
     */
    public ArrayList<Location> getAllLocations() {

        String query = "select * from "+TABLE_NAME_LOCATIONS;
        ArrayList<Location> locations = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Location l = new Location(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));

                locations.add(l);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return locations;
    }

    /*
        Obtains a location object from the database given a unique ID.
     */
    public Location getLocation(int locationID) {

        String query = "select * from "+TABLE_NAME_LOCATIONS+" where "+COL_LOCATION_ID+" = "+locationID;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        Location loc;

        if (cursor.moveToFirst()) {
            loc = new Location(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
        } else {
            loc = null;
        }

        cursor.close();
        database.close();
        return loc;
    }

    /*
        Deletes the selected locationID from the database.
        Returns a boolean depending on if the deletion occurred properly.
     */
    public boolean deleteLocation(int locationID) {

        SQLiteDatabase database = this.getWritableDatabase();
        int rowsDeleted = database.delete(TABLE_NAME_LOCATIONS, COL_LOCATION_ID +" = ?", new String[]{String.valueOf(locationID)});
        database.close();
        return rowsDeleted > 0;
    }

    // ***************************** PROFILE TABLE HELPER METHODS *****************************

    // ***************************** CATEGORY TABLE HELPER METHODS *****************************

    /*
        Returns a list of all categories stored in the database.
     */
    public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        String query = "select * from "+TABLE_NAME_CATEGORIES+" order by "+COL_CATEGORY_NAME+" asc";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            categories.add(context.getString(R.string.select_category));
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return categories;
    }

    /*
        Allows the user to add a category for future use.
     */
    public void insertCategory(String categoryName) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORY_NAME, categoryName);

        database.insert(TABLE_NAME_CATEGORIES, null, values);
        database.close();
    }
}