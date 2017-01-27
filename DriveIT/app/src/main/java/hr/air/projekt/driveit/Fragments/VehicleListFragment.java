package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.Vehicle;
import hr.air.projekt.driveit.Adapters.VehicleAdapter;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.NavigationItem;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;
import hr.air.projekt.driveit.VehicleLab;

/**
 * Created by mislav on 22.12.16..
 */

public class VehicleListFragment extends Fragment implements NavigationItem {
    private int position;

    private static final String CHILD_VEHICLE = "vehicles";
    private RecyclerView vehicleRecyclerview;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference vehicleListReference;
    private VehicleAdapter adapter;
    private VehicleLab vehicleLab = new VehicleLab();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicle_list_fragment,container,false);
        vehicleListReference = FirebaseDatabase.getInstance().getReference().child(CHILD_VEHICLE);
        vehicleRecyclerview = (RecyclerView) view.findViewById(R.id.vehicle_recycler_view);
        vehicleRecyclerview.setLayoutManager( new LinearLayoutManager(getActivity()));

        vehicleListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Vehicle> vehicleArrayList= new ArrayList<Vehicle>();
                vehicleArrayList = vehicleLab.getVehicleList((Map<String, Object>) dataSnapshot.getValue());

                adapter = new VehicleAdapter(vehicleArrayList, getActivity());
                vehicleRecyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public String getItemName() {
        return "Vehicles";
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;

    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}
