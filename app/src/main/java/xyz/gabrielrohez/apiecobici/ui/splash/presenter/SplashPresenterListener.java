package xyz.gabrielrohez.apiecobici.ui.splash.presenter;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;

public interface SplashPresenterListener {
    void dataReceived(List<AvailabilityStationsResponse.StationsStatus> stationsStatus, List<InfoStationResponse.Stations> stationsInfo);
}
