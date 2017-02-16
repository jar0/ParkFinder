package com.example.jarek.parkingfinder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.content.Intent;
import android.location.Criteria;
import android.location.Geocoder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private String miasto;
    private ZoomControls zoom;
    private DatabaseHelper databaseHelper;
    private String snippett,parking_name;
    public String id,pname,psnippet;
    public float lat,lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        databaseHelper = new DatabaseHelper(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        zoom = (ZoomControls) findViewById(R.id.simpleZoomControl);
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });



    }




    class ClickMap implements GoogleMap.OnMapLongClickListener {

        @Override
        public void onMapLongClick(final LatLng latLng) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
            final Context context = builder.getContext();
            final LayoutInflater li = LayoutInflater.from(context);
            final View v = li.inflate(R.layout.create_marker_alert, null, false);
            final EditText parkingName = (EditText) v.findViewById(R.id.etParkingName);
            final EditText hourPrice = (EditText) v.findViewById(R.id.etPriceHour);
            final EditText dayPrice = (EditText) v.findViewById(R.id.etPriceDay);

            builder.setView(v);
            builder.setCancelable(false);

            builder.setPositiveButton("Dodaj parking", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    mMap.addMarker(new MarkerOptions()
                            .title(parkingName.getText().toString())
                            .snippet("Godzina: "
                                    + hourPrice.getText().toString() + " PLN.   Doba: "
                                    + dayPrice.getText().toString() + " PLN.")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                            .position(latLng)
                    );
                    snippett = "Godzina: "
                            + hourPrice.getText().toString() + " PLN.   Doba: "
                            + dayPrice.getText().toString() + " PLN.";
                    parking_name = parkingName.getText().toString();
                    Toast.makeText(MapsActivity.this,"Parking został dodany",Toast.LENGTH_SHORT).show();

                    Log.i("SendMailActivity", "Send Button Clicked.");

                    String fromEmail = "parking.finder.markers@gmail.com";
                    String fromPassword = "admin321321";
                    String toEmails = "parking.finder.markers@gmail.com";
                    List toEmailList = Arrays.asList(toEmails
                            .split("\\s*,\\s*"));
                    Log.i("SendMailActivity", "To List: " + toEmailList);
                    String emailSubject = "New marker on map";
                    String emailBodyJSON = "{\"parking_name\": \""
                            +parking_name+"\",\"snippet\":\""
                            +snippett+"\",\"latitude\":\""
                            +latLng.latitude+"\",\"longitude\":\""
                            +latLng.longitude+"\"}";
                    new SendMailTask(MapsActivity.this).execute(fromEmail, fromPassword, toEmailList, emailSubject, emailBodyJSON);
                }


            });
            builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }





    // dodawanie do bazy punktów dodanych przez użytkownika

//                    boolean isInserted = databaseHelper.insertData(title, latLng.longitude, latLng.latitude, snippett);
//                    if(isInserted)
//                        Toast.makeText(MapsActivity.this,"Parking został dodany",Toast.LENGTH_SHORT).show();
//                    else
//                        Toast.makeText(MapsActivity.this,"Parking nie został dodany",Toast.LENGTH_SHORT).show();




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Cursor res = databaseHelper.getAllData();
        if(res.getCount() == 0)
        {
            Toast.makeText(MapsActivity.this,"Brak danych do załadowania",Toast.LENGTH_SHORT).show();
            return;
        }
        while(res.moveToNext())
        {
            id = res.getString(0);
            pname = res.getString(1);
            lat = res.getFloat(2);
            lon = res.getFloat(3);
            psnippet = res.getString(4);

            mMap.addMarker(new MarkerOptions()
                    .title(pname)
                    .snippet(psnippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .position(new LatLng(lat,lon))
            );
        }
        Intent intent = getIntent();
        miasto = intent.getStringExtra("miasto");
        List<Address> addressList = null;
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission
                        (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.INTERNET
                },10);
            }
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapLongClickListener(new ClickMap());

        if(miasto != null || !miasto.equals(""))
        {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(miasto, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));
        }

    }
}
