/** K-MAP
 *  Developed by: Collin Vossman and Timothy Ripper
 *  CIS 598 - Senior Project
 */

package com.example.cavossman.kmap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class campus_map extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    //private LatLng _campus = new LatLng(39.190213, -96.581414);

    //TRY: Add strict bounds to prevent user from venturing too far from campus on map.
    private LatLngBounds _KSU = new LatLngBounds(new LatLng(39.186090, -96.585406), new LatLng(39.195192,-96.576242)); //LatLngBounds(southwest, northwest)


    class Building {
        private LatLng latLng;
        private String name;

        Building(double lat, double lng, String name) {
            this.latLng = new LatLng(lat,lng);
            this.name = name;
        }

        public LatLng getLatLng() {
            return latLng;
        }

        public String getName() {
            return name;
        }
    }

    ArrayList<Building> buildingList = new ArrayList<Building>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Grab info on each building and create object/add to list of buildings
        //NEED: Remove hardcode - do it with database
        Building cardwell = new Building(39.192267, -96.582102, "Cardwell Hall");
        Building alumniCenter = new Building(39.186561, -96.584281, "Alumni Center");

        buildingList.add(cardwell);
        buildingList.add(alumniCenter);
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
        mMap.setOnMarkerClickListener(this);
        //Add a marker for every building in the building list
        for (Building b: buildingList){
            mMap.addMarker(new MarkerOptions().position(b.getLatLng()).title(b.getName()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(_KSU.getCenter(), 15));
    }

    //NEED: Open new activity onMarkerClick
    @Override
    public boolean onMarkerClick(Marker marker) {
        //Toast.makeText(getApplicationContext(), "You Clicked " + String.valueOf(marker.getTitle()), Toast.LENGTH_LONG).show();
        marker.setSnippet("Ive been touched");
        return false;
    }
}

//////// TIME SPENT ////////
// Learning:        5 hr  //
// Coding:          3 hr  //
// FloorPlans:      2 hr  //
// Database:        0 hr  //
// Total:          10 hr  //
////////////////////////////