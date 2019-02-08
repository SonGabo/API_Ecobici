package xyz.gabrielrohez.apiecobici.ui.availabilitystation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.gabrielrohez.apiecobici.R;
import xyz.gabrielrohez.apiecobici.data.network.model.AvailabilityStationsResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class AvailabilityStationsFragment extends Fragment {

    private View view;

    public static AvailabilityStationsFragment newInstance(List<AvailabilityStationsResponse.Stationsstatus> stationStatus) {
        Bundle args = new Bundle();
        AvailabilityStationsFragment fragment = new AvailabilityStationsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_availability_stations, container, false);

        return view;
    }

}
