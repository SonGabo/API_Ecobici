package xyz.gabrielrohez.apiecobici.data.network.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyClusterItem implements ClusterItem {

    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private int id;
    private int bikes;
    private int slotes;

    public MyClusterItem(LatLng mPosition, String mTitle, String mSnippet, int id, int bikes, int slotes) {
        this.mPosition = mPosition;
        this.mTitle = mTitle;
        this.mSnippet = mSnippet;
        this.id = id;
        this.bikes = bikes;
        this.slotes = slotes;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public int getId() {
        return id;
    }

    public int getSlotes(){
        return slotes;
    }

    public int getBikes(){
        return bikes;
    }
}
