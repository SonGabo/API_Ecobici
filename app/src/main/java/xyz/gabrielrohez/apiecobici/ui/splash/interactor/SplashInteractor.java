package xyz.gabrielrohez.apiecobici.ui.splash.interactor;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.gabrielrohez.apiecobici.data.network.ApiEndpointInterface;
import xyz.gabrielrohez.apiecobici.data.network.RetrofitClient;
import xyz.gabrielrohez.apiecobici.data.network.model.AccessTokenResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;
import xyz.gabrielrohez.apiecobici.data.preferences.MySharedPreferences;
import xyz.gabrielrohez.apiecobici.ui.splash.presenter.SplashPresenterListener;
import xyz.gabrielrohez.apiecobici.utils.AppConstants;

public class SplashInteractor implements SplashInteractorIn {

    @Override
    public void obtainAccessToken(final SplashPresenterListener listener, final Activity activity) {
        MySharedPreferences.getInstance(activity);

        RetrofitClient.getInstance().retrofit_token.create(ApiEndpointInterface.class).obtainAccessToken(AppConstants.CLIENT_ID, AppConstants.CLIENT_SECRET, AppConstants.GRANT_TYPE).enqueue(new Callback<AccessTokenResponse>() {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {

                if (response.body().getAccessToken()!=null){
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    final String currentDateandTime = sdf.format(new Date());

                    MySharedPreferences.getInstance().setAccessToken(response.body().getAccessToken());
                    MySharedPreferences.getInstance().setRefreshToken(response.body().getAccessToken());
                    MySharedPreferences.getInstance().setLastTime(currentDateandTime);
                    Log.d("dato_recibido", response.body().getAccessToken());
                    listener.openNextActivity();
                }else
                    listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                listener.showError("Intente de nuevo mas tarde.");
            }
        });
    }
}
