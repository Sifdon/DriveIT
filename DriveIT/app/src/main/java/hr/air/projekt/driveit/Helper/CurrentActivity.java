package hr.air.projekt.driveit.Helper;

import android.app.Activity;

/**
 * Created by Stjepan on 19.12.2016..
 */

public class CurrentActivity {
    private static Activity mActivity;

    public static Activity getActivity()
    {
        return mActivity;
    }

    public static void setActivity(Activity activity)
    {
        mActivity = activity;
    }
}
