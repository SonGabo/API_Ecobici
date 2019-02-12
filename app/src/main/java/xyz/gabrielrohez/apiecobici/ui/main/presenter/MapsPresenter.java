package xyz.gabrielrohez.apiecobici.ui.main.presenter;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MapsInteractor;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MapsInteractorIn;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsView;

public class MapsPresenter implements MapsPresenterIn, MapsPresenterListener {

    private MapsView view;
    private MapsInteractorIn interactor;

    public MapsPresenter(MapsView view) {
        this.view = view;
        interactor = new MapsInteractor();
    }

    @Override
    public void getStations(MapsActivity activity) {
        interactor.getStations(this, activity);
    }
}
