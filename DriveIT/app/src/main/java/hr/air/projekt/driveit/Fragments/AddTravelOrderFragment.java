package hr.air.projekt.driveit.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.datamodule.Vehicle;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.CurrentFirebaseAuth;
import hr.air.projekt.driveit.Helper.CurrentUser;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.TravelOrderLab;
import hr.air.projekt.driveit.VehicleLab;


public class AddTravelOrderFragment extends android.app.Fragment implements View.OnClickListener{

    private static final String CHILD_TRAVEL_ORDER = "travelOrders";
    private static final String CHILD_VEHICLE ="vehicles";
    @BindView(R.id.add_travel_order_user)
    AutoCompleteTextView addTravelOrderUser;
    @BindView(R.id.add_travel_order_IsOpen)
    Switch addTravelOrderIsOpen;
    @BindView(R.id.add_travel_order_vehiclePicker)
    Spinner addTravelOrderVehiclePicker;
    @BindView(R.id.add_travel_order_startDate)
    AutoCompleteTextView addTravelOrderStartDate;
    @BindView(R.id.add_travel_order_endDate)
    AutoCompleteTextView addTravelOrderEndDate;
    @BindView(R.id.add_travel_order_startKm)
    AutoCompleteTextView addTravelOrderStartKm;
    @BindView(R.id.add_travel_order_endKm)
    AutoCompleteTextView addTravelOrderEndKm;
    @BindView(R.id.add_travel_order_crossedKm)
    AutoCompleteTextView addTravelOrderCrossedKm;
    @BindView(R.id.add_travel_order_buttonCancel)
    Button addTravelOrderButtonCancel;
    @BindView(R.id.add_travel_order_buttonSave)
    Button addTravelOrderButtonSave;

    private TravelOrder travelOrder;
    private TravelOrderLab travelOrderLab;
    private VehicleLab vehicleLab;
    private ArrayList<Vehicle> vehicleList;
    private List<String> chassisNumbers = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_travel_order_fragment, container, false);
        ButterKnife.bind(this, view);
        bindViews();
        addTravelOrderButtonSave.setOnClickListener(this);
        addTravelOrderButtonCancel.setOnClickListener(this);
        addTravelOrderUser.setText(CurrentUser.getUser().getFirstName() + " " + CurrentUser.getUser().getLastName());
        travelOrder = new TravelOrder();
        travelOrderLab = new TravelOrderLab();
        vehicleLab = new VehicleLab();

        addTravelOrderVehiclePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                travelOrder.setVehicleId(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MM.yyyy.");
        DateTime openDate = DateTime.now();
        addTravelOrderStartDate.setText(openDate.toString(fmt));

        return view;
    }


    private void bindViews() {

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_VEHICLE);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vehicleList = vehicleLab.getVehicleList((Map<String, Object>) dataSnapshot.getValue());
                chassisNumbers = vehicleLab.getAllChassisNumbers(vehicleList);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CurrentActivity.getActivity(), android.R.layout.simple_spinner_item, chassisNumbers);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addTravelOrderVehiclePicker.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateTravelOrder() {
        travelOrder.setUserId(CurrentUser.getUser().getUid());
        travelOrder.setStartDate(addTravelOrderStartDate.getText().toString());
        travelOrder.setStartKm(Long.parseLong(addTravelOrderStartKm.getText().toString()));
        travelOrder.setTravelOrderId(UUID.randomUUID().toString());
        travelOrder.setEndKm(travelOrder.getStartKm());
        travelOrder.setCrossedKm((travelOrder.getEndKm() - travelOrder.getStartKm()));
        travelOrder.setOpen(true);


    }

    @Override
    public void onClick(View v) {
        if (v == addTravelOrderButtonSave) {
            updateTravelOrder();
            travelOrderLab.updateTravelOrder(travelOrder);
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        } else if (v == addTravelOrderButtonCancel) {
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
    }

}
