package hr.air.projekt.driveit;

import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.Adapters.TravelOrderHolder;
import hr.air.projekt.driveit.Helper.UpdateDistance;
import hr.air.projekt.driveit.rest.ApiClient;
import hr.air.projekt.driveit.rest.ApiInterface;
import hr.air.projekt.driveit.rest.VehicleResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by scolak on 22.08.17..
 */

public class GpsTracking implements UpdateDistance {

    TravelOrderHolder travelOrderHolder;

    public GpsTracking(TravelOrderHolder travelOrderHolder) {
        this.travelOrderHolder = travelOrderHolder;
    }

    @Override
    public void setCrossedDistance(final TravelOrder travelOrder) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<VehicleResponse> call = apiService.GetVehicle();
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                VehicleResponse v = response.body();
                travelOrder.setEndKm(v.getKmNumber());
                //travelOrderHolder.bindTravelOrder(travelOrder);
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {

            }
        });
    }
}



