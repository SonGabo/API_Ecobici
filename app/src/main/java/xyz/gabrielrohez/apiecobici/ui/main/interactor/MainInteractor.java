package xyz.gabrielrohez.apiecobici.ui.main.interactor;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.gabrielrohez.apiecobici.data.network.ApiEndpointInterface;
import xyz.gabrielrohez.apiecobici.data.network.RetrofitClient;
import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.preferences.MySharedPreferences;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MainPresenterListener;

public class MainInteractor implements MainInteractorIn {

    @Override
    public void getAvailabilityStations(MainPresenterListener listener) {
        RetrofitClient.getInstance().retrofit.create(ApiEndpointInterface.class).getAvailabilityStations(MySharedPreferences.getInstance().getAccessToken()).enqueue(new Callback<AvailabilityStationsResponse>() {
            @Override
            public void onResponse(Call<AvailabilityStationsResponse> call, Response<AvailabilityStationsResponse> response) {
                if (response.code() == 200){
                    Log.d("respuesta", response.body().getStationsStatus().toString());
                }
            }

            @Override
            public void onFailure(Call<AvailabilityStationsResponse> call, Throwable t) {

            }
        });
    }
}
