package xyz.gabrielrohez.apiecobici.ui.main.presenter;
import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.data.network.model.MyClusterItem;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MapsInteractor;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MapsInteractorIn;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsView;

public class MapsPresenter implements MapsPresenterIn, MapsPresenterListener {

    private MapsView view;
    private MapsInteractorIn interactor;

    public MapsPresenter(MapsView view) {
        this.view = view;
        interactor = new MapsInteractor();
    }

    @Override
    public void getStations(MapsActivity activity) {
        interactor.getStations(this, activity);
    }

    @Override
    public void getInfoToStation(MyClusterItem item, MapsActivity activity) {
        interactor.getInfoToStation(this, item, activity);
    }

    @Override
    public void obtainAccessToken(MapsActivity activity) {
        interactor.obtainAccessToken(this, activity);
    }

    @Override
    public void setStationsList(List<StatusBikesEntity> list) {
        if (view != null){
            view.setStationsList(list);
        }
    }

    @Override
    public void setInfoInPanel(StationsModel model) {
        if (view != null){
            view.setInfoInPanel(model);
        }
    }

    @Override
    public void showError(String message) {
        if (view != null){
            view.showError(message);
        }
    }

    @Override
    public void updatedInformation() {
        if (view != null){
            view.updatedInformation();
        }
    }
}
