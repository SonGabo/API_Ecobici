package xyz.gabrielrohez.apiecobici.ui.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.data.Room.db.AppDB;
import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenter;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterIn;
import xyz.gabrielrohez.apiecobici.utils.Utils;

public class MapsActivity extends AppCompatActivity implements MapsView, OnMapReadyCallback {


    private GoogleMap mMap;
    private MapsPresenterIn presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        presenter = new MapsPresenter(this);
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
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        presenter.getStations(this);
    }

    @Override
    public void setStationsList(List<StatusBikesEntity> list) {
        addMarker(list);
    }

    private void addMarker(List<StatusBikesEntity> list) {
        mMap.clear();
        for (StatusBikesEntity status : list){
            MarkerOptions markerOptions = null;
            try {
                markerOptions = new MarkerOptions()
                        .position(new LatLng(status.getLat(), status.getLon()))
                        .draggable(false)
                        .icon(Utils.bitmapDescriptorFromVector(this, R.drawable.ic_bike))
                        .title(status.getName());
            }catch (Exception e){
                e.printStackTrace();
            }
            Marker marker = mMap.addMarker(markerOptions);
            marker.setTag(status);
        }
    }
}
