package xyz.gabrielrohez.apiecobici.ui.main.interactor;

import android.app.Activity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import xyz.gabrielrohez.apiecobici.data.network.model.MyClusterItem;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;
import xyz.gabrielrohez.apiecobici.data.preferences.MySharedPreferences;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterListener;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;
import xyz.gabrielrohez.apiecobici.ui.splash.presenter.SplashPresenterListener;
import xyz.gabrielrohez.apiecobici.utils.AppConstants;
import xyz.gabrielrohez.apiecobici.utils.Utils;

public class MapsInteractor implements MapsInteractorIn {

    @Override
    public void getStations(MapsPresenterListener listener, MapsActivity activity) {
        List<StatusBikesEntity> list = new ArrayList<>();
        list = AppDB.getAppDB(activity).statusDAO().getAllBikes();
        listener.setStationsList(list);
    }

    @Override
    public void getInfoToStation(MapsPresenterListener listener, MyClusterItem item, MapsActivity activity) {
        StationsModel model = AppDB.getAppDB(activity).availableDAO().getStation(item.getId());
        listener.setInfoInPanel(model);
    }

    @Override
    public void obtainAccessToken(final MapsPresenterListener listener, final MapsActivity activity) {
        listener.showLoader(true);
        if (Utils.isOnline(activity)){
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
                        listener.showLoader(false);
                        listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
                    }
                }

                @Override
                public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                    listener.showError("Intente de nuevo mas tarde.");
                    listener.showLoader(false);
                }
            });
        } else{
            listener.showError("No tiene acceso a internet, verifique su conexión.");
            listener.showLoader(false);
        }

    }

    public void getAvailabilityStations(final MapsPresenterListener listener, final MapsActivity activity) {
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
                }else{
                    listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
                    listener.showLoader(false);
                }
            }

            @Override
            public void onFailure(Call<AvailabilityStationsResponse> call, Throwable t) {
                listener.showError("Intente de nuevo mas tarde.");
                listener.showLoader(false);
            }
        });
    }

    private void getStatusStations(final MapsPresenterListener listener, final MapsActivity activity) {
        RetrofitClient.getInstance().retrofit.create(ApiEndpointInterface.class).getInfoStation(MySharedPreferences.getInstance().getAccessToken()).enqueue(new Callback<InfoStationResponse>() {
            @Override
            public void onResponse(Call<InfoStationResponse> call, Response<InfoStationResponse> response) {
                if (response.code() == 200){
                    listener.showLoader(false);
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
                    listener.updatedInformation();

                }else{
                    listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
                    listener.showLoader(false);
                }
            }

            @Override
            public void onFailure(Call<InfoStationResponse> call, Throwable t) {
                listener.showError("Intente de nuevo mas tarde.");
                listener.showLoader(false);
            }
        });
    }
}
