package xyz.gabrielrohez.apiecobici.ui.main.interactor;

import java.util.ArrayList;
import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.db.AppDB;
import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterListener;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;

public class MapsInteractor implements MapsInteractorIn {

    @Override
    public void getStations(MapsPresenterListener listener, MapsActivity activity) {
        List<StatusBikesEntity> list = new ArrayList<>();
        list = AppDB.getAppDB(activity).statusDAO().getAllBikes();
        listener.setStationsList(list);
    }
}
