package xyz.gabrielrohez.apiecobici.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.db.AppDB;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;

public class AsyncMethods {

    private static Context context;

    public AsyncMethods(Context context) {
        this.context = context;
    }

    public static class saveMyNewList extends AsyncTask<Void, Void, List<StationsModel>> {

        @Override
        protected List<StationsModel> doInBackground(Void... voids) {
            List<StationsModel> stationsModels = new ArrayList<>();
            for (int i = 0; i < AppDB.getAppDB(context).statusDAO().getAllBikes().size(); i++){
                StationsModel model = new StationsModel();
                model.setAddress(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getAddress());
                model.setAddressNumber(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getAddressNumber());
                model.setBikes(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getBikes());
                model.setDistrictCode(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getDistrictCode());
                model.setDistrictName(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getDistrictName());
                model.setId(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getId());
                model.setLat(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getLat());
                model.setLon(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getLon());
                model.setName(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getName());
                model.setSlots(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getSlots());
                model.setStationType(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getStationType());
                model.setStatus(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getStatus());
                model.setZipCode(AppDB.getAppDB(context).availableDAO().getStation(AppDB.getAppDB(context).statusDAO().getAllBikes().get(i).getId()).getZipCode());

                stationsModels.add(model);
            }
            return stationsModels;
        }

        @Override
        protected void onPostExecute(List<StationsModel> stationsModels) {
            showStations(stationsModels);
        }

        private void showStations(List<StationsModel> stationsModels) {
            Log.d("modelo_salida", stationsModels.toString());
        }
    }
}
