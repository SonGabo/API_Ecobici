package xyz.gabrielrohez.apiecobici.ui.main.presenter;


import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;

public interface MapsPresenterListener {
    void setStationsList(List<StatusBikesEntity> list);

    void setInfoInPanel(StationsModel model);

    void showError(String message);

    void updatedInformation();

    void showLoader(boolean visible);
}
