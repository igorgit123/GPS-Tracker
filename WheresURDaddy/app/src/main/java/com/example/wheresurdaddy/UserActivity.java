package com.example.wheresurdaddy;

import android.Manifest;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;


import static java.time.LocalDate.now;

public class UserActivity extends AppCompatActivity implements OnMapReadyCallback {
    DatabaseReference reff;

    GoogleMap mapApi;
    SupportMapFragment mapFragment;

    double latitude = 0;
    double longitude = 0;

    Button buttonLoc;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapApi = googleMap;
        GPStracker g = new GPStracker(getApplicationContext());
        Location l = g.getLocation();
        latitude = l.getLatitude();
        longitude = l.getLongitude();
        Log.e("lokacija", "lat " + latitude + " long " + longitude);
        LatLng lokacija = new LatLng(latitude, longitude);

        mapApi.addMarker(new MarkerOptions().position(lokacija).title("Lokacija"));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);


        reff = FirebaseDatabase.getInstance().getReference().child("PrijavljenUser");
        final PrijavljenUser prijavljen = new PrijavljenUser();

        Bundle bundle = getIntent().getExtras();
        final String email = bundle.getString("email");
        prijavljen.setEmail(email);
        getSupportActionBar().setTitle(String.format(getString(R.string.user_id_placeholder), email));


        final TextView komentar = (TextView) findViewById(R.id.komentar);
        final TextView userid = (TextView) findViewById(R.id.userid);
        buttonLoc = (Button) findViewById(R.id.buttonLoc);

        ActivityCompat.requestPermissions(UserActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);


        buttonLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid.getText().toString().length() < 5) {
                    Toast.makeText(getApplicationContext(), "ID mora vsebovati vsaj 5 znakov.", Toast.LENGTH_SHORT).show();
                    ;
                } else {
                    prijavljen.setId(userid.getText().toString());

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        LocalDateTime time = LocalDateTime.now();
                        prijavljen.setDatum(time);
                    }

                    prijavljen.setKomentar(komentar.getText().toString());


                    for (int i = 0; i < 5; i++) {

                        GPStracker g = new GPStracker(getApplicationContext());
                        Location l = g.getLocation();

                        if (l != null) {
                            latitude = l.getLatitude();
                            longitude = l.getLongitude();

                            LatLng lokacija = new LatLng(latitude, longitude);

                            mapApi.clear();
                            mapApi.addMarker(new MarkerOptions().position(lokacija).title(komentar.getText().toString()));
                            mapApi.moveCamera(CameraUpdateFactory.newLatLng(lokacija));

                            try {
                                Thread.sleep(3000);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Napaka: " + e.toString(), Toast.LENGTH_LONG).show();
                            }

                            prijavljen.setDolzina(longitude);
                            prijavljen.setSirina(latitude);

                            reff.push().setValue(prijavljen);

                            Toast.makeText(UserActivity.this, "Lokacija uspešno deljena.", Toast.LENGTH_LONG).show();

                        }
                    }


                }
            }
        });

    }


}
