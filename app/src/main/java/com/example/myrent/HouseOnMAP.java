package com.example.myrent;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HouseOnMAP extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String hlati,hlongi,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_on_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        hlati = getIntent().getStringExtra("lati");
        hlongi = getIntent().getStringExtra("longi");
        name = getIntent().getStringExtra("name");

    }


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
        double latitude = Double.parseDouble(hlati);
        double longtitude = Double.parseDouble(hlongi);

        // Add a marker in Sydney and move the camera
        LatLng house = new LatLng(latitude, longtitude);
        mMap.addMarker(new MarkerOptions().position(house).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(house));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
