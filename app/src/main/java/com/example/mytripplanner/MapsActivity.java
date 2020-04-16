/*
 *   NAME    : FlightInfoResult.java
 *   Project: Mobile Application Development - Assignment 5
 *   By: Charng Gwon Lee, Hyungbum Kim, Younchul Cho
 *   Date: April. 17, 2020
 *   PURPOSE : The MapsActivity class has been created to Google map.
 *             It is going to set the location of our headquarter (Conestoga college waterloo campus).
 *             Also, it zooms in to proper scale and show a marker.
 */

package com.example.mytripplanner;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * we just add a marker Conestoga college waterloo campus.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set the position
        LatLng headquarter = new LatLng(43.479273, -80.518376);

        // Create the marker for the location
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(headquarter);
        markerOptions.title("Conestoga Travel");
        markerOptions.snippet("Headquarter");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(headquarter));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
