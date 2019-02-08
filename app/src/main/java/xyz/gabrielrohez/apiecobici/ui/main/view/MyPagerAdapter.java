package xyz.gabrielrohez.apiecobici.ui.main.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;
import xyz.gabrielrohez.apiecobici.ui.availabilitystation.AvailabilityStationsFragment;
import xyz.gabrielrohez.apiecobici.ui.stationinfo.StationInfoFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<InfoStationResponse.Station> infoStations;
    private List<AvailabilityStationsResponse.Stationsstatus> stationStatus;

    public MyPagerAdapter(FragmentManager fm, List<InfoStationResponse.Station> infoStations, List<AvailabilityStationsResponse.Stationsstatus> stationStatus) {
        super(fm);
        this.infoStations = infoStations;
        this.stationStatus = stationStatus;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return StationInfoFragment.newInstance(infoStations);
            case 1:
                return AvailabilityStationsFragment.newInstance(stationStatus);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
