package xyz.gabrielrohez.apiecobici.ui.main.presenter;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MainInteractor;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MainInteractorIn;
import xyz.gabrielrohez.apiecobici.ui.main.view.MainView;

public class MainPresenter implements MainPresenterIn, MainPresenterListener{

    private MainView view;
    private MainInteractorIn interactor;

    public MainPresenter(MainView view) {
        this.view = view;
        interactor = new MainInteractor();
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
