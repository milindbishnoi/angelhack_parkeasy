package com.example.angelhack.UI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.angelhack.DataModels.globalVars;
import com.example.angelhack.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class ProfileActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    boolean granted = false;
    int REQ_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQ_CODE);
        }
        if (granted = true) {
            client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        globalVars.setCurrLat(location.getLatitude());
                        globalVars.setCurrLon(location.getLongitude());
                    } else
                        Toast.makeText(ProfileActivity.this, "Error retrieving current location !", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQ_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                granted=true;
            else
                granted = false;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
