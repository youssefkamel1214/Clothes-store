package com.youssef.cloath_store.payment;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.youssef.cloath_store.Constants;
import com.youssef.cloath_store.R;
import com.youssef.cloath_store.databinding.ActivityPaymentBinding;

import java.util.Locale;

public class paymentActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityPaymentBinding binding;
    private FusedLocationProviderClient client;
    Marker poimaker=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        client= LocationServices.getFusedLocationProviderClient(getApplicationContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if(!isPermissionGranted())
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Constants.locationpremssion);
        mapFragment.getMapAsync(this);
        binding.payment.setOnClickListener(view -> {
            if(binding.visacrd.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"please enter visa number",Toast.LENGTH_LONG).show();
                return;
            }
            if(poimaker==null){
                Toast.makeText(this,"please pick location",Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(this,"we take your location stay tunned",Toast.LENGTH_LONG).show();
            finish();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            makehomelocation();
    }

    @SuppressLint("MissingPermission")
    private void makehomelocation() {
        if(mMap!=null){
            mMap.setMyLocationEnabled(true);
            LocationCallback  locationCallback=new LocationCallback() {

                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    LatLng home=new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
                    client.removeLocationUpdates(this);
                     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home,15f));
                   poimaker= mMap.addMarker(new MarkerOptions().position(home).title("home"));
                }
            };
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval( 5000);
            client.requestLocationUpdates(locationRequest, locationCallback,
                    Looper.getMainLooper());
        }
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
    private boolean isPermissionGranted(){
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                    return true;
                else
                    return false;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(latLng -> {
            poimaker.remove();
          poimaker=  mMap.addMarker(new MarkerOptions().position(latLng).title("picked position"));
        });
        // Add a marker in Sydney and move the camera
        if(isPermissionGranted())
            makehomelocation();
    }
}