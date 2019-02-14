package xyz.gabrielrohez.apiecobici.ui.main.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.custom.Loader;
import xyz.gabrielrohez.apiecobici.data.Room.db.AppDB;
import xyz.gabrielrohez.apiecobici.data.Room.entity.AvailabilityBikesEntity;
import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.data.network.model.MyClusterItem;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenter;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterIn;
import xyz.gabrielrohez.apiecobici.ui.splash.view.SplashActivity;
import xyz.gabrielrohez.apiecobici.utils.MyClusterRenderer;
import xyz.gabrielrohez.apiecobici.utils.MyLocation;
import xyz.gabrielrohez.apiecobici.utils.Utils;

public class MapsActivity extends AppCompatActivity implements MapsView, OnMapReadyCallback, SlidingUpPanelLayout.PanelSlideListener {

    @BindView(R.id.panelGoToStation)ImageView ivGoToStation;
    @BindViews({R.id.panelTitle, R.id.panelNumberBikes, R.id.panelNumberSlots})List<TextView> input;

    int zoom;
    private Loader loader;
    private GoogleMap mMap;
    private MapsPresenterIn presenter;
    private SlidingUpPanelLayout slideupPannel;
    private ClusterManager<MyClusterItem> mClusterManager;
    SlidingUpPanelLayout.PanelState stateOpen = SlidingUpPanelLayout.PanelState.EXPANDED;
    SlidingUpPanelLayout.PanelState stateClose = SlidingUpPanelLayout.PanelState.COLLAPSED;
    SlidingUpPanelLayout.PanelState stateHidde = SlidingUpPanelLayout.PanelState.HIDDEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        loader = new Loader(this);
        presenter = new MapsPresenter(this);

        setUpMap();
        setUpPanelUp();
    }

    private void setUpPanelUp() {
        slideupPannel = (SlidingUpPanelLayout) findViewById(R.id.slide_layout);
        slideupPannel.setPanelState(stateHidde);
        slideupPannel.addPanelSlideListener(this);
    }

    private void setUpMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.380929, -99.164088), 12));
        mMap.clear();
        mMap.setOnMapClickListener(mMapClickListener);

        if (Utils.isEnablePermission(this)){
            getMap().setMyLocationEnabled(true);
            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                @Override
                public void gotLocation(Location location){
                    // Position the map.
                    showLoader(false);
                    getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14));
                    presenter.getStations(MapsActivity.this);
                }
            };
            MyLocation myLocation = new MyLocation();
            myLocation.getLocation(this, locationResult);
        }else{
            // Position the map.
            // no permission, show map in Mexico City
            presenter.getStations(this);
            getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.380929, -99.164088), 12));
        }
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
                    Utils.showDialogPermission(this);
                }
                return;
            }
        }
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
            AvailabilityBikesEntity availability = AppDB.getAppDB(this).availableDAO().getStatusStation(result.getId());
            MyClusterItem myClusterItem = new MyClusterItem(new LatLng(result.getLat(), result.getLon()), result.getName(), result.getAddress(), result.getId(), availability.getBikes(), availability.getSlots());
            mClusterManager.addItem(myClusterItem);
        }
    }

    /**
     * Click in ClusterItem
     */
    public ClusterManager.OnClusterItemClickListener<MyClusterItem> mClusterItemClickListener = new ClusterManager.OnClusterItemClickListener<MyClusterItem>() {
        @Override
        public boolean onClusterItemClick(MyClusterItem item) {
            presenter.getInfoToStation(item, MapsActivity.this);
            return true;
        }
    };

    public GoogleMap.OnMapClickListener mMapClickListener = new GoogleMap.OnMapClickListener() {
        @Override
        public void onMapClick(LatLng latLng) {
            slideupPannel.setPanelState(stateClose);
        }
    };

    @Override
    public void setInfoInPanel(final StationsModel model) {
        slideupPannel.setPanelState(stateOpen);
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(model.getLat(), model.getLon()), 18));
        input.get(0).setText(model.getName());
        input.get(1).setText(String.valueOf(model.getBikes()));
        input.get(2).setText(String.valueOf(model.getSlots()));
        ivGoToStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(model.getLat(), model.getLon()), 18));
            }
        });
    }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reload) {
            //  TODO :: get all data
            //presenter.getStations(this);
            slideupPannel.setPanelState(stateHidde);
            if (!getMap().isMyLocationEnabled()){
                onMapReady(mMap);
            }else
                presenter.obtainAccessToken(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updatedInformation() {
        onMapReady(mMap);
    }

    @Override
    public void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.error_in_request);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.obtainAccessToken(MapsActivity.this);
            }
        });
        builder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showLoader(boolean visible) {
        if(visible)
            getLoaderProgressDialog().show();
        else
            getLoaderProgressDialog().dismiss();
    }

    private Loader getLoaderProgressDialog() {
        if(loader != null)
            return loader;
        else
            return loader = new Loader(this);
    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

    }
}
