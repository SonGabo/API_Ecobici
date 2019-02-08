package xyz.gabrielrohez.apiecobici.ui.main.presenter;

import xyz.gabrielrohez.apiecobici.ui.main.interactor.MainInteractor;
import xyz.gabrielrohez.apiecobici.ui.main.interactor.MainInteractorIn;
import xyz.gabrielrohez.apiecobici.ui.main.view.MainView;

public class MainPresenter implements MainPresenterIn, MainPresenterListener{

    private MainView view;
    private MainInteractorIn interactor;

    public MainPresenter(MainView view) {
        this.view = view;
        interactor = new MainInteractor();
    }

    @Override
    public void getAvailabilityStations() {
        interactor.getAvailabilityStations(this);
    }

    @Override
    public void showError(String message) {
        if (view != null){
            view.showError(message);
        }
    }
}
