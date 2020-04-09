package com.example.myapplication.ui.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.ItemDetailFragment;
import com.example.myapplication.ui.home.dummy.DummyContent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Objects;

public class MapFragment extends Fragment
    implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnInfoWindowClickListener {


    private GoogleMap googleMap;
    private HashMap<Marker, Integer> mHashMap = new HashMap<Marker, Integer>();

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
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuRestaurants:
                // User chose the "Settings" item, show the app settings UI...
                //filter("restaurants");
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

                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                googleMap.clear();
                insertMarkers(googleMap, "all");
                return super.onOptionsItemSelected(item);
            //return true;
        }
        //
    }




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setOnMyLocationButtonClickListener(this);
        googleMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        float zoomLevel = 16.0f; //This goes up to 21
        LatLng startzoom  = new LatLng(53.341960, -6.253881);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startzoom, zoomLevel));
        //get_json();

        insertMarkers(googleMap, "all");
        googleMap.setOnInfoWindowClickListener(this);



    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getContext(), "Info window clicked",
                Toast.LENGTH_SHORT).show();


        DummyContent.DummyItem item = DummyContent.ITEMS.get(mHashMap.get(marker));




        Bundle arguments = new Bundle();
        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);

        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(arguments);

        AppCompatActivity activity = (AppCompatActivity) getContext();
        //TestFragment fragment = new TestFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).addToBackStack(null).commit();
        //activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

        //R.id.nav_host_fragment
    }

    private void insertMarkers(GoogleMap googleMap, String type){
        int length = DummyContent.ITEMS.size();
        DummyContent.DummyItem place;
        Marker marker;
        for(int i = 0; i < length; i++){
            place = DummyContent.ITEMS.get(i);
            if(type.equals("all")){
                marker = googleMap.addMarker(new MarkerOptions().position(place.location)
                        .title(place.name)
                        .snippet(place.description));
                //mHashMap.put(marker, Integer.getInteger(place.id));
                mHashMap.put(marker, i);


            } else{
                if(type.equals(place.type)){
                    googleMap.addMarker(new MarkerOptions().position(place.location)
                            .title(place.name)
                            .snippet(place.description));
                }
            }



        }


    }






    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION)
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        assert getFragmentManager() != null;
        com.example.myapplication.ui.map.PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getFragmentManager(), "dialog");
    }

}
