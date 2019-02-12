package xyz.gabrielrohez.apiecobici.ui.main.presenter;


import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;

public interface MapsPresenterListener {
    void setStationsList(List<StatusBikesEntity> list);
}
