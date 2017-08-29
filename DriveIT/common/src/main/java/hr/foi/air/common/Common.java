package hr.foi.air.common;

/**
 * Created by Boki on 29.8.2017..
 */

public abstract class Common {
    protected OnDataLoaded mDataLoaded;

    public void loadData(OnDataLoaded onDataLoaded)
    {
        this.mDataLoaded=onDataLoaded;
    }

}
