package xyz.gabrielrohez.apiecobici.ui.splash.presenter;

import android.app.Activity;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;
import xyz.gabrielrohez.apiecobici.ui.splash.interactor.SplashInteractor;
import xyz.gabrielrohez.apiecobici.ui.splash.interactor.SplashInteractorIn;
import xyz.gabrielrohez.apiecobici.ui.splash.view.SplashView;

public class SplashPresenter implements SplashPresenterIn, SplashPresenterListener {

    private SplashView view;
    private SplashInteractorIn interactor;

    public SplashPresenter(SplashView view) {
        this.view = view;
        interactor = new SplashInteractor();
    }

    @Override
    public void obtainAccessToken(Activity activity) {
        interactor.obtainAccessToken(this, activity);
    }

    @Override
    public void openNextActivity() {
        if (view != null){
            view.openNextActivity();
        }
    }

    @Override
    public void showError(String message) {
        if (view != null){
            view.showError(message);
        }
    }
}
