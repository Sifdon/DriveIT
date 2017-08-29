package hr.air.projekt.driveit.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Map;

import butterknife.BindView;
import butterknife.*;
import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.GpsTracking;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.UpdateDistance;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.TravelOrderLab;
import hr.air.projekt.driveit.VehicleLab;
import hr.air.projekt.driveit.rest.ManualDistanceEntry;
import hr.foi.air.common.OnDataLoaded;

/**
 * Created by Stjepan on 29.1.2017..
 */

public class TravelOrderDetailFragment extends Fragment implements View.OnClickListener, TextWatcher,OnDataLoaded{
    private static final String TRAVEL_ORDER_DETAILS = "travelOrderDetails";
    private static final String USER_NAME = "userName";

    final TravelOrderDetailFragment thisFragment=this;
    private Long kmNumber;

    @BindView(R.id.travel_order_detail_user)
    EditText editTextUser;
    @BindView(R.id.travel_order_detail_vehicle)
    EditText editTextVehicle;
    @BindView(R.id.travel_order_detail_startDate)
    EditText editTextStartDate;
    @BindView(R.id.travel_order_detail_endDate)
    EditText editTextEndDate;
    @BindView(R.id.travel_order_detail_startKm)
    EditText editTextStartMileage;
    @BindView(R.id.travel_order_detail_endKm)
    EditText editTextEndMileage;
    @BindView(R.id.travel_order_detail_crossedKm)
    EditText editTextCrossedMileage;
    @BindView(R.id.travel_order_detail_IsOpen)
    CheckBox aSwitchIsOpen;
    @BindView(R.id.travel_order_detail_buttonSave)
    Button buttonSave;
    @BindView(R.id.travel_order_detail_buttonCancel)
    Button buttonCancel;
    @BindView(R.id.use_service)
    Button buttonUseService;

    UpdateDistance updateDistance;

    private VehicleLab vehicleLab;
    private TravelOrder travelOrderData;
    TravelOrderLab travelOrderLab;
    private String userName;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.travel_order_detail_fragment, container, false);
        ButterKnife.bind(this, view);
        buttonCancel.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        travelOrderLab = new TravelOrderLab();
        buttonUseService.setOnClickListener(this);
        Bundle bundle = getArguments();
        travelOrderData = (TravelOrder) bundle.getSerializable(TRAVEL_ORDER_DETAILS);
        userName = bundle.getString(USER_NAME);

        vehicleLab = new VehicleLab();
        editTextEndMileage.setClickable(false);
        editTextEndMileage.setEnabled(false);

        editTextEndMileage.setClickable(true);
        editTextEndMileage.addTextChangedListener(this);
        aSwitchIsOpen.setChecked(travelOrderData.isOpen());

        setView();
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==buttonCancel){
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
        if(v==buttonSave){
            updateTravelOrderData();
            travelOrderLab.updateTravelOrder(travelOrderData);
            Toast.makeText(CurrentActivity.getActivity(), R.string.travel_order_updated,
                    Toast.LENGTH_SHORT).show();
            if(travelOrderData.isOpen()==false){
                vehicleLab.updateMileage(travelOrderData.getEndKm(), travelOrderData.getVehicleId());
            }
            vehicleLab.updateMileage(travelOrderData.getEndKm(),travelOrderData.getVehicleId());
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
        else if(v==buttonUseService){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    CurrentActivity.getActivity());

            alertDialogBuilder.setTitle("Pick mileage source");



            alertDialogBuilder
                    .setMessage("Get milegae from service?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            updateDistance = new GpsTracking();
                            updateDistance.setOnDataLoaded(thisFragment);
                            updateDistance.getCrossedDistance(thisFragment);
                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            updateDistance = new ManualDistanceEntry();
                            updateDistance.setOnDataLoaded(thisFragment);
                            updateDistance.getCrossedDistance(thisFragment);
                            dialog.cancel();

                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


    }

    private void setView(){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("travelOrders").child(travelOrderData.getTravelOrderId());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> travelOrder = (Map<String,Object> )dataSnapshot.getValue();
                editTextUser.setText(userName);
                editTextVehicle.setText(travelOrder.get("vehicleId").toString());
                editTextStartDate.setText(travelOrder.get("startDate").toString());
                editTextCrossedMileage.setText(travelOrder.get("crossedKm").toString());
                editTextStartMileage.setText(travelOrder.get("startKm").toString());
                editTextEndMileage.setText(travelOrder.get("endKm").toString());

                if(travelOrderData.isOpen()==true){
                    DateTimeFormatter fmt = DateTimeFormat.forPattern(" dd.MM.yyyy.");
                    DateTime openDate = DateTime.now();
                    editTextEndDate.setText(openDate.toString(fmt));
                }
                else{
                    editTextEndDate.setText(travelOrder.get("endDate").toString());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateTravelOrderData() {
        travelOrderData.setEndKm(Long.parseLong(editTextEndMileage.getText().toString()));
        travelOrderData.setCrossedKm(Long.parseLong(editTextCrossedMileage.getText().toString()));
        travelOrderData.setOpen(aSwitchIsOpen.isChecked());
        travelOrderData.setEndDate(editTextEndDate.getText().toString());
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s==editTextEndMileage) {
            int m = Integer.parseInt(editTextStartMileage.getText().toString());
            int k = Integer.parseInt(editTextEndMileage.getText().toString());
            int c = m + k;
            editTextCrossedMileage.setText(c);
        }
    }


    @Override
    public void onDataLoaded(Long kmNumber) {
        this.kmNumber=kmNumber;
        editTextEndMileage.setText(kmNumber.toString());
    }


}
