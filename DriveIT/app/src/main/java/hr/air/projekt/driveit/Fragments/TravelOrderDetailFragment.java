package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.*;
import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.GpsTracking;
import hr.air.projekt.driveit.Helper.CalculateKilometers;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 29.1.2017..
 */

public class TravelOrderDetailFragment extends Fragment implements View.OnClickListener{
    private static final String TRAVEL_ORDER_DETAILS = "travelOrderDetails";
    private static final String USER_NAME = "userName";

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
    Switch aSwitchIsOpen;
    @BindView(R.id.travel_order_detail_buttonSave)
    Button buttonSave;
    @BindView(R.id.travel_order_detail_buttonCancel)
    Button buttonCancel;

    private TravelOrder travelOrderData;
    private GpsTracking gpsTracking = new GpsTracking();
    private String userName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.travel_order_detail_fragment, container, false);
        ButterKnife.bind(this, view);
        buttonCancel.setOnClickListener(this);
        buttonSave.setOnClickListener(this);


        Bundle bundle = getArguments();
        travelOrderData = (TravelOrder) bundle.getSerializable(TRAVEL_ORDER_DETAILS);
        userName = bundle.getString(userName);
        gpsTracking.setCrossedDistance(travelOrderData);

        editTextUser.setText(userName);
        editTextVehicle.setText(travelOrderData.getVehicleId());
        editTextStartDate.setText(travelOrderData.getStartDate());
        editTextEndDate.setText(travelOrderData.getEndDate());
        editTextStartMileage.setText(Long.toString(travelOrderData.getStartKm()));
        editTextEndMileage.setText(Long.toString(travelOrderData.getEndKm()));
        editTextCrossedMileage.setText(Long.toString(travelOrderData.getCrossedKm()));
        aSwitchIsOpen.setChecked(travelOrderData.isOpen());



        return view;
    }

    @Override
    public void onClick(View v) {

    }


}
