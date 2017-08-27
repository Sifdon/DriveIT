package hr.air.projekt.driveit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.GpsTracking;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 29.1.2017..
 */

public class TravelOrderAdapter extends RecyclerView.Adapter<TravelOrderHolder> {
    private LayoutInflater layoutInflater;
    private List<TravelOrder> travelOrderList = new ArrayList<TravelOrder>();

    public TravelOrderAdapter(ArrayList<TravelOrder> travelOrders) {
        layoutInflater = LayoutInflater.from(CurrentActivity.getActivity());
        travelOrderList = travelOrders;
    }

    @Override
    public TravelOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_single_travel_order, parent, false);
        return new TravelOrderHolder(view, travelOrderList, this);
    }

    @Override
    public void onBindViewHolder(TravelOrderHolder holder, int position) {
        TravelOrder travelOrder = travelOrderList.get(position);
        GpsTracking gpsTracking = new GpsTracking(holder);
        gpsTracking.setCrossedDistance(travelOrder);
        holder.bindTravelOrder(travelOrder);
    }

    @Override
    public int getItemCount() {
        return travelOrderList.size();
    }
}
