package xyz.gabrielrohez.apiecobici.ui.splash.view;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;

public interface SplashView {
    void showError(String message);

    void openNextActivity();
}
