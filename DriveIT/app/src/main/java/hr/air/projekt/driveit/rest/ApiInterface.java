package hr.air.projekt.driveit.rest;


import java.util.Map;

import hr.air.projekt.datamodule.Vehicle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static hr.air.projekt.driveit.R.id.info;


public interface ApiInterface {
    @GET("/")
    Call<VehicleResponse> GetVehicle();

}
