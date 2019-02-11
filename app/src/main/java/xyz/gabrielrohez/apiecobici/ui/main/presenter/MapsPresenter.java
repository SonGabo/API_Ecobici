package xyz.gabrielrohez.apiecobici.ui.main.presenter;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MapsInteractor;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MapsInteractorIn;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsView;

public class MapsPresenter implements MapsPresenterIn, MapsPresenterListener {

    private MapsView view;
    private MapsInteractorIn interactor;

    public MapsPresenter(MapsView view) {
        this.view = view;
        interactor = new MapsInteractor();
    }

    @Override
    public void getAvailabilityStations() {
        interactor.getAvailabilityStations(this);
    }

    @Override
    public void showError(String message) {
        if (view != null){
            view.showError(message);
        }
    }

    @Override
    public void dataReceived(List<InfoStationResponse.Station> infoStations, List<AvailabilityStationsResponse.Stationsstatus> stationStatus) {
        if (view != null){
            view.dataReceived(infoStations, stationStatus);
        }
    }
}
