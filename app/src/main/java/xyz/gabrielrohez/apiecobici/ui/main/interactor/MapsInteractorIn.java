package xyz.gabrielrohez.apiecobici.ui.main.interactor;

import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenter;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterListener;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;

public interface MapsInteractorIn {
    void getStations(MapsPresenterListener listener, MapsActivity activity);
}
