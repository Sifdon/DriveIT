package hr.air.projekt.driveit.Helper;

import android.app.Fragment;

/**
 * Created by Stjepan on 19.12.2016..
 */

public interface NavigationItem {
    public String getItemName();
    public int getPosition();
    public void setPosition(int position);
    public Fragment getFragment();
}
