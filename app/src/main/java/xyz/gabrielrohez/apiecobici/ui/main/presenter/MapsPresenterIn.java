package xyz.gabrielrohez.apiecobici.ui.main.presenter;

import xyz.gabrielrohez.apiecobici.data.network.model.MyClusterItem;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;

public interface MapsPresenterIn {
    void getStations(MapsActivity mapsActivity);
    void getInfoToStation(MyClusterItem item, MapsActivity activity);
}
