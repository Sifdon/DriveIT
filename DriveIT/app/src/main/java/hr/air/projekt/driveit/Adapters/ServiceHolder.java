package hr.air.projekt.driveit.Adapters;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;
import java.util.Map;

import hr.air.projekt.datamodule.Service;

import hr.air.projekt.driveit.Fragments.AddMalfunctionFragment;
import hr.air.projekt.driveit.Fragments.MalfunctionDetailFragment;
import hr.air.projekt.driveit.Fragments.ServiceDetailFragment;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;

/**
 * Created by Stjepan on 26.1.2017..
 */

public class ServiceHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    private static final String SERVICE_DETAILS = "ServiceDetails";
    private static final String MECHANIC_NAME = "MechanicName";
    private TextView textViewMechanic;
    private TextView textViewDate;
    private TextView textViewDescription;
    private Service serviceData;
    private UserLab userLab = new UserLab();
    private String mechanicName;

    public ServiceHolder(View itemView, List<Service> services, ServiceAdapter adapter) {
        super(itemView);
        itemView.setOnLongClickListener(this);
        textViewMechanic = (TextView) itemView.findViewById(R.id.recycler_view_serviceMechanic);
        textViewDate = (TextView) itemView.findViewById(R.id.recycler_view_serviceDate);
        textViewDescription = (TextView) itemView.findViewById(R.id.recycler_view_serviceDescription);
    }

    public void bindService(Service service) {
        this.serviceData = service;
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("users").child(serviceData.getMechanic());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> user = (Map<String, Object>) dataSnapshot.getValue();
                mechanicName = user.get("firstName") + " " + user.get("lastName");
                textViewMechanic.setText(user.get("firstName") + " " + user.get("lastName"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        textViewDate.setText(service.getDate());
        textViewDescription.setText(service.getDescription());
    }

    @Override
    public boolean onLongClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SERVICE_DETAILS, serviceData);
        bundle.putSerializable(MECHANIC_NAME, mechanicName);
        Fragment nextFrag = new ServiceDetailFragment();
        nextFrag.setArguments(bundle);
        CurrentActivity.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, nextFrag, null)
                .addToBackStack(null)
                .commit();
        return false;
    }
}
