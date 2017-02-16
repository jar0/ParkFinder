package com.example.jarek.parkingfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jarek on 02.01.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Parking_Markers.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "Parking_Markers";

    private static final String CREATE_TABLE_PARKING_MARKERS =
            "CREATE TABLE "+ TABLE_NAME
                    +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "PARKING_NAME TEXT, " +
                    "LATITUDE DECIMAL(10,8), " +
                    "LONGITUDE DECIMAL(11,8), " +
                    "SNIPPET TEXT)";

    private static final String DROP_TABLE_PARKING_MARKERS =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PARKING_MARKERS);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_PARKING_MARKERS);
        onCreate(db);
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }
}
