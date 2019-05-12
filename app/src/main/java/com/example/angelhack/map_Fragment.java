package com.example.angelhack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angelhack.DataModels.globalVars;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class map_Fragment extends Fragment implements OnMapReadyCallback {

    View mView;
    MapView mapView;
    GoogleMap gMap;
    LatLng pos ,oldMetro, stAlbans ,Mall, ajroundaMetro, hudaParking, paidParking, brajeshParking;

    public map_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) mView.findViewById(R.id.Map);
        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        pos = new LatLng(globalVars.getCurrLat(), globalVars.getCurrLon());
        stAlbans = new LatLng(28.3979018, 77.3265451);
        oldMetro = new LatLng(28.410218, 77.3087513);
        Mall = new LatLng(28.3860502, 77.3166397);
        ajroundaMetro = new LatLng(28.3978759, 77.3108327);
        hudaParking = new LatLng(28.3943874, 77.3214824);
        paidParking = new LatLng(28.4262444, 77.3235913);
        brajeshParking = new LatLng(28.4024408, 77.3028743);

        gMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_current))
                .title("YOU")
                .position(pos))
                .setSnippet("You're here");


        gMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parking))
                .title("OLD FBD METRO PARKING")
                .position(oldMetro))
                .setSnippet("    Available");

        gMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parking))
                .title("AJROUNDA METRO PARKING")
                .position(ajroundaMetro))
                .setSnippet("    Available");

        gMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parking))
                .title("SRS PARKING LOT")
                .position(Mall))
                .setSnippet("    Available");

        gMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parking))
                .title("St. ALBANS PUBLIC PARKING")
                .position(stAlbans))
                .setSnippet("    Available");

        gMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parking))
                .title("HUDA OPEN PARKING")
                .position(hudaParking))
                .setSnippet("    Available");

        gMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parking))
                .title("PAID PRIVATE PARKING")
                .position(paidParking))
                .setSnippet("    Available");

        gMap.addMarker(new MarkerOptions()
                .anchor(0.0f, 1.0f)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_parking))
                .title("BRAJESH PARKING SERVICE")
                .position(brajeshParking))
                .setSnippet("    Available");

        CameraPosition myPosition = new CameraPosition.Builder()
                .target(pos).zoom(13).build();
        gMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
    }
}


