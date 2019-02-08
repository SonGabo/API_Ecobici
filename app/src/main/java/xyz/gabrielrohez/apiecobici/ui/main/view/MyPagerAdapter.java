package xyz.gabrielrohez.apiecobici.ui.main.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xyz.gabrielrohez.apiecobici.ui.availabilitystation.AvailabilityStationsFragment;
import xyz.gabrielrohez.apiecobici.ui.stationinfo.StationInfoFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return StationInfoFragment.newInstance();
            case 1:
                return AvailabilityStationsFragment.newInstance();
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
