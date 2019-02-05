package xyz.gabrielrohez.apiecobici;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class StationInfoFragment extends Fragment {

    private View view;

    public static StationInfoFragment newInstance() {
        Bundle args = new Bundle();
        StationInfoFragment fragment = new StationInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_station_info, container, false);
        return view;
    }

}
