package xyz.gabrielrohez.apiecobici.ui.stationinfo;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.data.network.model.InfoStationResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class StationInfoFragment extends Fragment {

    private View view;
    private List<InfoStationResponse.Station> info;

    public static StationInfoFragment newInstance(List<InfoStationResponse.Station> infoStations) {
        StationInfoFragment fragment = new StationInfoFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("infoStations", (ArrayList<? extends Parcelable>) infoStations);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_station_info, container, false);

        info = getArguments().getParcelableArrayList("infoStations");
        return view;
    }

}
