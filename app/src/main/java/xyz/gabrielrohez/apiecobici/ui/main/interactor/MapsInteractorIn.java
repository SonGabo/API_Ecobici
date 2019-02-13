package xyz.gabrielrohez.apiecobici.ui.main.interactor;

import xyz.gabrielrohez.apiecobici.data.network.model.MyClusterItem;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterListener;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;

public interface MapsInteractorIn {
    void getStations(MapsPresenterListener listener, MapsActivity activity);

    void getInfoToStation(MapsPresenterListener listener, MyClusterItem item, MapsActivity activity);
}
