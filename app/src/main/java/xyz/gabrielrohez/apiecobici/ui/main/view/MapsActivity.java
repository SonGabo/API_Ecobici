package xyz.gabrielrohez.apiecobici.ui.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.data.Room.db.AppDB;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenter;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterIn;

public class MapsActivity extends AppCompatActivity implements MapsView, OnMapReadyCallback {


    private GoogleMap mMap;
    private MapsPresenterIn presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        presenter = new MapsPresenter(this);
        presenter.getStations(this);
        setUpMap();

        Log.d("primer_consuota", AppDB.getAppDB(this).availableDAO().getStation(1).toString());
    }

    private void setUpMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}