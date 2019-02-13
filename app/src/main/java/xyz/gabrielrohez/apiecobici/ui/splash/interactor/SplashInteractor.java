package xyz.gabrielrohez.apiecobici.ui.splash.interactor;

import android.app.Activity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.gabrielrohez.apiecobici.data.Room.db.AppDB;
import xyz.gabrielrohez.apiecobici.data.Room.entity.AvailabilityBikesEntity;
import xyz.gabrielrohez.apiecobici.data.Room.entity.StatusBikesEntity;
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
                    getAvailabilityStations(listener, activity);
                }else{
                    if (AppDB.getAppDB(activity).statusDAO().getAllBikes().isEmpty()){
                        listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
                    }else
                        listener.openNextActivity();
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                if (AppDB.getAppDB(activity).statusDAO().getAllBikes().isEmpty()){
                    listener.showError("Intente de nuevo mas tarde.");
                }else
                    listener.openNextActivity();
            }
        });
    }

    public void getAvailabilityStations(final SplashPresenterListener listener, final Activity activity) {
        RetrofitClient.getInstance().retrofit.create(ApiEndpointInterface.class).getAvailabilityStations(MySharedPreferences.getInstance().getAccessToken()).enqueue(new Callback<AvailabilityStationsResponse>() {
            @Override
            public void onResponse(Call<AvailabilityStationsResponse> call, Response<AvailabilityStationsResponse> response) {
                if (response.code() == 200){

                    AvailabilityBikesEntity list = new AvailabilityBikesEntity();
                    for (AvailabilityStationsResponse.Stationsstatus result : response.body().getStationsStatus()){
                        list.setId_available(result.getId());
                        list.setBikes(result.getAvailability().getBikes());
                        list.setSlots(result.getAvailability().getSlots());
                        list.setStatus(result.getStatus());
                        AppDB.getAppDB(activity).availableDAO().insert(list);
                    }
                    getStatusStations(listener, activity);
                }else if (AppDB.getAppDB(activity).availableDAO().getAllAvailable().isEmpty()){
                    listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
                }else
                    listener.openNextActivity();
            }

            @Override
            public void onFailure(Call<AvailabilityStationsResponse> call, Throwable t) {
                if (AppDB.getAppDB(activity).availableDAO().getAllAvailable().isEmpty()){
                    listener.showError("Intente de nuevo mas tarde.");
                }else
                    listener.openNextActivity();
            }
        });
    }

    private void getStatusStations(final SplashPresenterListener listener, final Activity activity) {
        RetrofitClient.getInstance().retrofit.create(ApiEndpointInterface.class).getInfoStation(MySharedPreferences.getInstance().getAccessToken()).enqueue(new Callback<InfoStationResponse>() {
            @Override
            public void onResponse(Call<InfoStationResponse> call, Response<InfoStationResponse> response) {
                if (response.code() == 200){

                    StatusBikesEntity list = new StatusBikesEntity();
                    for (InfoStationResponse.Station result : response.body().getStations()){
                        list.setId(result.getId());
                        list.setAvailable_id(result.getId());
                        list.setAddress(result.getAddress());
                        list.setAddressNumber(result.getAddressNumber());
                        list.setDistrictCode(result.getDistrictCode());
                        list.setDistrictName(result.getDistrictName());
                        list.setLat(result.getLocation().getLat());
                        list.setLon(result.getLocation().getLon());
                        list.setName(result.getName());
                        list.setStationType(result.getStationType());
                        list.setZipCode(result.getZipCode());

                        AppDB.getAppDB(activity).statusDAO().insert(list);
                    }
                    listener.openNextActivity();

                }else if (AppDB.getAppDB(activity).statusDAO().getAllBikes().isEmpty()){
                    listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
                }else
                    listener.openNextActivity();
            }

            @Override
            public void onFailure(Call<InfoStationResponse> call, Throwable t) {
                if (AppDB.getAppDB(activity).statusDAO().getAllBikes().isEmpty()){
                    listener.showError("Intente de nuevo mas tarde.");
                }else
                    listener.openNextActivity();
            }
        });
    }
}
