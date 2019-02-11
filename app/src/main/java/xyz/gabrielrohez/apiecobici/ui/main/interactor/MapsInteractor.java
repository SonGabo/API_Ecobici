package xyz.gabrielrohez.apiecobici.ui.main.interactor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.gabrielrohez.apiecobici.data.network.ApiEndpointInterface;
import xyz.gabrielrohez.apiecobici.data.network.RetrofitClient;
import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;
import xyz.gabrielrohez.apiecobici.data.preferences.MySharedPreferences;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterListener;

public class MapsInteractor implements MapsInteractorIn {

    @Override
    public void getAvailabilityStations(final MapsPresenterListener listener) {
        RetrofitClient.getInstance().retrofit.create(ApiEndpointInterface.class).getAvailabilityStations(MySharedPreferences.getInstance().getAccessToken()).enqueue(new Callback<AvailabilityStationsResponse>() {
            @Override
            public void onResponse(Call<AvailabilityStationsResponse> call, Response<AvailabilityStationsResponse> response) {
                if (response.code() == 200){
                    getStatusStations(listener, response.body().getStationsStatus());
                }else
                    listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
            }

            @Override
            public void onFailure(Call<AvailabilityStationsResponse> call, Throwable t) {
                listener.showError("Intente de nuevo mas tarde.");
            }
        });
    }

    private void getStatusStations(final MapsPresenterListener listener, final List<AvailabilityStationsResponse.Stationsstatus> stationsStatus) {
        RetrofitClient.getInstance().retrofit.create(ApiEndpointInterface.class).getInfoStation(MySharedPreferences.getInstance().getAccessToken()).enqueue(new Callback<InfoStationResponse>() {
            @Override
            public void onResponse(Call<InfoStationResponse> call, Response<InfoStationResponse> response) {
                if (response.code() == 200){
                    listener.dataReceived(response.body().getStations(), stationsStatus);
                }else
                    listener.showError("No se pudo establecer conexión con el servidor, intente de nuevo mas tarde.");
            }

            @Override
            public void onFailure(Call<InfoStationResponse> call, Throwable t) {
                listener.showError("Intente de nuevo mas tarde.");
            }
        });
    }
}
