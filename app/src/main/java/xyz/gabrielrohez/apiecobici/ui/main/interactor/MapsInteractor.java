package xyz.gabrielrohez.apiecobici.ui.main.interactor;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import xyz.gabrielrohez.apiecobici.data.Room.db.AppDB;
import xyz.gabrielrohez.apiecobici.data.network.model.StationsModel;
import xyz.gabrielrohez.apiecobici.ui.main.presenter.MapsPresenterListener;
import xyz.gabrielrohez.apiecobici.ui.main.view.MapsActivity;
import xyz.gabrielrohez.apiecobici.utils.AsyncMethods;

public class MapsInteractor implements MapsInteractorIn {
    private AsyncMethods asyncMethods;
    private List<StationsModel> list;
    private AsyncMethods.saveMyNewList saveMyNewList;

    @Override
    public void getStations(MapsPresenterListener listener, MapsActivity activity) {
        list  = new ArrayList<>();
        asyncMethods = new AsyncMethods(activity);




        saveMyNewList = new AsyncMethods.saveMyNewList();
        saveMyNewList.execute();
    }
}
