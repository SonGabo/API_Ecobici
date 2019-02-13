package xyz.gabrielrohez.apiecobici.ui.main.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

import xyz.gabrielrohez.apiecobici.BuildConfig;
import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.data.network.model.MyClusterItem;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenter;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterIn;
import xyz.gabrielrohez.apiecobici.utils.MyClusterRenderer;
import xyz.gabrielrohez.apiecobici.utils.MyLocation;

public class MapsActivity extends AppCompatActivity implements MapsView, OnMapReadyCallback {

    int zoom;
    private GoogleMap mMap;
    private MapsPresenterIn presenter;
    private ClusterManager<MyClusterItem> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        presenter = new MapsPresenter(this);
        setUpMap();

    }

    private void setUpMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        presenter.getStations(this);

        if (isEnablePermission()){
            getMap().setMyLocationEnabled(true);
            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                @Override
                public void gotLocation(Location location){
                    // Position the map.
                    getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14));

                }
            };
            MyLocation myLocation = new MyLocation();
            myLocation.getLocation(this, locationResult);
        }else
            // Position the map.
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.380929, -99.164088), 10));
    }

    private boolean isEnablePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return false;
        } else
            return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    onMapReady(mMap);
                } else {
                    // permission denied, boo! Disable the
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.need_permission);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            openPermissionSettings(MapsActivity.this);
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                return;
            }
        }
    }

    public static void openPermissionSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + activity.getPackageName()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    /**
     * get map
     * @return
     */
    protected GoogleMap getMap() {
        return mMap;
    }

    /**
     * Cluster config
     * @param list
     */
    @Override
    public void setStationsList(List<StatusBikesEntity> list) {

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyClusterItem>(this, getMap());
        mClusterManager.setRenderer(new MyClusterRenderer(this, getMap(), mClusterManager));

        mClusterManager.setOnClusterClickListener(mClusterClickListener);
        mClusterManager.setOnClusterItemClickListener(mClusterItemClickListener);
        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems(list);
    }

    /**
     * Set values in cluster
     * @param list
     */
    private void addItems(List<StatusBikesEntity> list) {

        for (StatusBikesEntity result : list){
            MyClusterItem myClusterItem = new MyClusterItem(new LatLng(result.getLat(), result.getLon()), result.getName(), result.getAddress(), result.getId());
            mClusterManager.addItem(myClusterItem);
        }
    }

    /**
     * Click in ClusterItem
     */
    public ClusterManager.OnClusterItemClickListener<MyClusterItem> mClusterItemClickListener = new ClusterManager.OnClusterItemClickListener<MyClusterItem>() {
        @Override
        public boolean onClusterItemClick(MyClusterItem item) {
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(item.getPosition().latitude, item.getPosition().longitude), 18));
            Toast.makeText(MapsActivity.this, ""+item.getId(), Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    /**
     * Click in Cluster
     */
    public ClusterManager.OnClusterClickListener<MyClusterItem> mClusterClickListener = new ClusterManager.OnClusterClickListener<MyClusterItem>() {
        @Override
        public boolean onClusterClick(Cluster<MyClusterItem> cluster) {
            zoom = (int) getMap().getCameraPosition().zoom + 2;
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(cluster.getPosition().latitude, cluster.getPosition().longitude), zoom));
            return true;
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        onMapReady(mMap);

    }
}
