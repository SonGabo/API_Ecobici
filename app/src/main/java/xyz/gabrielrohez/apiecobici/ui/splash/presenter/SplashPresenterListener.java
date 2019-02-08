package xyz.gabrielrohez.apiecobici.ui.splash.presenter;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;

public interface SplashPresenterListener {
    void showError(String message);

    void openNextActivity();
}
