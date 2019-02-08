package xyz.gabrielrohez.apiecobici.ui.splash.interactor;

import android.app.Activity;

import xyz.gabrielrohez.apiecobici.ui.splash.presenter.SplashPresenterListener;

public interface SplashInteractorIn {
    void obtainAccessToken(SplashPresenterListener listener, Activity activity);
}
