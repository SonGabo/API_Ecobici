package xyz.gabrielrohez.apiecobici.ui.main.presenter;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;

public interface MapsPresenterListener {
    void showError(String message);
    void dataReceived(List<InfoStationResponse.Station> infoStations, List<AvailabilityStationsResponse.Stationsstatus> stationStatus);
}
