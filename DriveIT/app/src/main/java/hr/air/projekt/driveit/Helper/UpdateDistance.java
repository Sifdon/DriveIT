package hr.air.projekt.driveit.Helper;

import hr.air.projekt.datamodule.TravelOrder;
import hr.foi.air.common.OnDataLoaded;

/**
 * Created by scolak on 22.08.17..
 */



public interface UpdateDistance {
    void getCrossedDistance(OnDataLoaded onDataLoaded);
    void  setOnDataLoaded (OnDataLoaded onDataLoaded);
}


