package com.example.wac;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //GoogleMap object
    private GoogleMap mMap;
    private static final LatLng INITIAL_LOCATION = new LatLng(35.847149,127.129391);

    /*private int area;
    private int MotorState;
    //private int Lightflag;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Maps Fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);


        //Button Click listener
        Button map1 = (Button) findViewById(R.id.map1);
        map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReserveActivity.class);
                startActivity(intent);
            }
        });
        Button map2 = (Button) findViewById(R.id.map2);
        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReserveActivity.class);
                startActivity(intent);
            }
        });
        Button map3 = (Button) findViewById(R.id.map3);
        map3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReserveActivity.class);
                startActivity(intent);
            }
        });
        Button map4 = (Button) findViewById(R.id.map4);
        map4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReserveActivity.class);
                startActivity(intent);
            }
        });
        Button map5 = (Button) findViewById(R.id.map5);
        map5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReserveActivity.class);
                startActivity(intent);
            }
        });

    }

    //Google Map Initial Location
    @Override
    public  void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(INITIAL_LOCATION,15));
    }

}

