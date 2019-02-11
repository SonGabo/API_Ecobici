package xyz.gabrielrohez.apiecobici.ui.main.view;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;

public interface MapsView {
    void showError(String message);
    void dataReceived(List<InfoStationResponse.Station> infoStations, List<AvailabilityStationsResponse.Stationsstatus> stationStatus);
}
