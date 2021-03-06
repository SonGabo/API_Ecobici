package xyz.gabrielrohez.apiecobici.ui.main.view;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;

public interface MapsView {
    void setStationsList(List<StatusBikesEntity> list);
    void setInfoInPanel(StationsModel model);

    void updatedInformation();

    void showError(String message);

    void showLoader(boolean visible);
}
