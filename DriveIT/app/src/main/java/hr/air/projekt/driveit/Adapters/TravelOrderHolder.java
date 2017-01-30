package hr.air.projekt.driveit.Adapters;

import android.app.Fragment;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.Fragments.ServiceDetailFragment;
import hr.air.projekt.driveit.Fragments.TravelOrderDetailFragment;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 29.1.2017..
 */

public class TravelOrderHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

    private static final String TRAVEL_ORDER_DETAILS = "travelOrderDetails";
    private static final String USER_NAME = "userName";

    private TextView textViewVehicle;
    private TextView textViewStartDate;
    private TextView textViewEndDate;
    private TextView textViewUser;
    private Switch aSwitchIsOpen;

    private TravelOrder travelOrderData;
    private String userName;

    public TravelOrderHolder(View itemView, List<TravelOrder> travelOrders, TravelOrderAdapter adapter) {
        super(itemView);
        itemView.setOnLongClickListener(this);
        textViewVehicle = (TextView) itemView.findViewById(R.id.recycler_view_travelOrderVehicle);
        textViewStartDate = (TextView) itemView.findViewById(R.id.recycler_view_travelOrderStartDate);
        textViewEndDate = (TextView) itemView.findViewById(R.id.recycler_view_travelOrderEndDate);
        textViewUser = (TextView) itemView.findViewById(R.id.recycler_view_travelOrderUser);
        aSwitchIsOpen = (Switch) itemView.findViewById(R.id.recycler_view_travelOrderIsOpen);
    }

    public void bindTravelOrder(TravelOrder travelOrder){
        this.travelOrderData = travelOrder;
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("users").child(travelOrderData.getUserId());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> user = (Map<String,Object> )dataSnapshot.getValue();
                userName = user.get("firstName")+ " " + user.get("lastName");
                textViewUser.setText(userName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        textViewVehicle.setText(travelOrderData.getVehicleId());
        textViewStartDate.setText(travelOrderData.getStartDate());
        textViewEndDate.setText(travelOrderData.getEndDate());
        aSwitchIsOpen.setChecked(travelOrderData.isOpen());
        if (travelOrder.isOpen()) {
            aSwitchIsOpen.getTrackDrawable().setColorFilter(ContextCompat.getColor(CurrentActivity.getActivity(), R.color.switch_track_checked_true_color), PorterDuff.Mode.SRC_OVER);
        } else {
            aSwitchIsOpen.getTrackDrawable().setColorFilter(ContextCompat.getColor(CurrentActivity.getActivity(), R.color.switch_track_checked_false_color), PorterDuff.Mode.SRC_OVER);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TRAVEL_ORDER_DETAILS, travelOrderData);
        bundle.putSerializable(USER_NAME, userName);
        Fragment nextFrag = new TravelOrderDetailFragment();
        nextFrag.setArguments(bundle);
        CurrentActivity.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, nextFrag, null)
                .addToBackStack(null)
                .commit();
        return false;
    }
}
