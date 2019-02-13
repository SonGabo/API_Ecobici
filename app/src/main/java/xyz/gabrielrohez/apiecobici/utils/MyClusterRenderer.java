package xyz.gabrielrohez.apiecobici.utils;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.data.network.model.MyClusterItem;

public class MyClusterRenderer extends DefaultClusterRenderer<MyClusterItem> {

    private Context context;

    public MyClusterRenderer(Context context, GoogleMap map, ClusterManager<MyClusterItem> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
    }

    @Override
    protected void onBeforeClusterItemRendered(MyClusterItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);

        markerOptions.title(item.getTitle());
        int total = item.getBikes() + item.getSlotes();
        if (item.getBikes() < (total/2)){
            if (item.getBikes() == 0){
                markerOptions.icon(Utils.bitmapDescriptorFromVector(context, R.drawable.ic_station_red));
            }else
                markerOptions.icon(Utils.bitmapDescriptorFromVector(context, R.drawable.ic_station_yellow));
        }else
            markerOptions.icon(Utils.bitmapDescriptorFromVector(context, R.drawable.ic_station_green));
    }

    @Override
    protected void onClusterItemRendered(MyClusterItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);

        //here you have access to the marker itself
    }
}
