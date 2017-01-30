package hr.air.projekt.driveit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.air.projekt.datamodule.Vehicle;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;

/**
 * Created by mislav on 23.12.16..
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleHolder> {

    private LayoutInflater layoutInflater;
    private List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    public VehicleAdapter(ArrayList<Vehicle> vehicles) {
        layoutInflater = layoutInflater.from(CurrentActivity.getActivity());
        this.vehicleList = vehicles;
    }

    @Override
    public VehicleHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.list_single_vehicle, parent, false);

        return new VehicleHolder(view, this.vehicleList, this);
    }

    @Override
    public void onBindViewHolder(VehicleHolder holder, int position) {

        Vehicle vehicle = vehicleList.get(position);
        holder.bindVehicle(vehicle);
    }

    @Override
    public int getItemCount() {

        return vehicleList.size();
    }
}
