package com.example.wheresurdaddy;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.atomic.AtomicBoolean;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    GoogleMap mapApi;
    SupportMapFragment mapFragment;
    double latitude = 0;
    double longitude = 0;


    DatabaseReference reff;
    Button btnGetLoc;
    String text;


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
        setContentView(R.layout.activity_main);
        //Toast.makeText(MainActivity.this,"Firebase connection success",Toast.LENGTH_LONG).show();
      /*  FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!"); */


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        btnGetLoc = (Button) findViewById(R.id.btnGetLoc);
        final TextView komentar = (TextView) findViewById(R.id.txtDodatnKomentar);

        reff = FirebaseDatabase.getInstance().getReference().child("UserLocation");

        Bundle bundle = getIntent().getExtras();

        final String id = bundle.getString("id_uporabnika");


        text = String.format(getString(R.string.user_id_placeholder), id);

        getSupportActionBar().setTitle(text);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);


        btnGetLoc.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                for (int i = 0; i < 5; i++) {
                    GPStracker g = new GPStracker(getApplicationContext());
                    UserLocation ul = new UserLocation();


                    ul.setKomentar(komentar.getText().toString());

                    ul.setId(id);
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
                        ul.setDolzina(longitude);
                        ul.setSirina(latitude);
                        reff.push().setValue(ul);
                        // reff.child(ul.getId()).setValue(ul);
                        Toast.makeText(MainActivity.this, "Lokacija uspešno deljena.", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

    }


}
