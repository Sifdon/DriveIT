package hr.air.projekt.driveit;

import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.Adapters.TravelOrderHolder;
import hr.air.projekt.driveit.Helper.UpdateDistance;
import hr.air.projekt.driveit.rest.ApiClient;
import hr.air.projekt.driveit.rest.ApiInterface;
import hr.air.projekt.driveit.rest.VehicleResponse;
import hr.foi.air.common.Common;
import hr.foi.air.common.OnDataLoaded;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by scolak on 22.08.17..
 */

public class GpsTracking extends Common implements UpdateDistance {


    OnDataLoaded onDataLoadedListener;
    public GpsTracking() {

    }


    @Override
    public void getCrossedDistance(OnDataLoaded onDataLoaded) {
        super.loadData(onDataLoaded);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<VehicleResponse> call = apiService.GetVehicle();
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                VehicleResponse v = response.body();
                onDataLoadedListener.onDataLoaded(Long.parseLong(v.getKmNumber().toString()));
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {

            }
        });


    }


    @Override
    public void setOnDataLoaded(OnDataLoaded onDataLoadedListener) {
        this.onDataLoadedListener=onDataLoadedListener;

    }
}



