package com.example.myapplication.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.dummy.DummyContent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment
    implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback{


    private GoogleMap googleMap;
    private List<DummyContent.DummyItem> placeList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        //placeList = DummyContent.ITEMS;
        // Try to obtain the map from the SupportMapFragment.
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        mapFragment.getMapAsync(this);

        setHasOptionsMenu(true);

        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean restaurants;
        boolean cafes;
        boolean events;
        switch (item.getItemId()) {
            case R.id.menuRestaurants:
                // User chose the "Settings" item, show the app settings UI...

                googleMap.clear();
                insertMarkers(googleMap, "restaurant");
                return true;

            case R.id.menuCafes:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                googleMap.clear();
                insertMarkers(googleMap, "cafe");


                return true;

            case R.id.menuEvents:
                // User chose the "Favorite" action, mark the current item

                // as a favorite...
                googleMap.clear();
                insertMarkers(googleMap, "event");

                return true;
            case R.id.menuAll:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                googleMap.clear();

                insertMarkers(googleMap, "all");

                return true;

            default:
                //mAdapter.getFilter().filter("all");
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                googleMap.clear();
                insertMarkers(googleMap, "all");
                return super.onOptionsItemSelected(item);
            //return true;
            //return true;
        }
        //
    }

    public void filter(String entry){

    }



    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        float zoomLevel = 16.0f; //This goes up to 21
        LatLng startzoom  = new LatLng(53.341960, -6.253881);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startzoom, zoomLevel));


        insertMarkers(googleMap, "all");



    }


    private void insertMarkers(GoogleMap googleMap, String type){
        int length = DummyContent.ITEMS.size();
        DummyContent.DummyItem place;
        for(int i = 0; i < length; i++){
            place = DummyContent.ITEMS.get(i);
            if(type.equals("all")){
                googleMap.addMarker(new MarkerOptions().position(place.location)
                        .title(place.name)
                        .snippet(place.description));

            } else{
                if(type.equals(place.type)){
                    googleMap.addMarker(new MarkerOptions().position(place.location)
                            .title(place.name)
                            .snippet(place.description));
                }
            }




        }


    }






    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            com.example.myapplication.ui.map.PermissionUtils.requestPermission(getActivity(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (googleMap != null) {
            // Access to the location has been granted to the app.
            googleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getContext(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getContext(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (com.example.myapplication.ui.map.PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    /*
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();

        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        com.example.myapplication.ui.map.PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getFragmentManager(), "dialog");
    }

}
