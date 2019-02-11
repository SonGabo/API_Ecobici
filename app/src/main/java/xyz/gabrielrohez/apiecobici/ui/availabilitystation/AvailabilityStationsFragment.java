package xyz.gabrielrohez.apiecobici.ui.availabilitystation;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class AvailabilityStationsFragment extends Fragment {

    private View view;
    private List<AvailabilityStationsResponse.Stationsstatus> list;

    public static AvailabilityStationsFragment newInstance(List<AvailabilityStationsResponse.Stationsstatus> stationStatus) {
        AvailabilityStationsFragment fragment = new AvailabilityStationsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("stationStatus", (ArrayList<? extends Parcelable>) stationStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_availability_stations, container, false);
        list = getArguments().getParcelableArrayList("stationStatus");

        return view;
    }

}
