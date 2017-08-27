package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import butterknife.BindView;
import butterknife.*;
import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.TravelOrderLab;
import hr.air.projekt.driveit.VehicleLab;

/**
 * Created by Stjepan on 29.1.2017..
 */

public class TravelOrderDetailFragment extends Fragment implements View.OnClickListener, TextWatcher{
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
    CheckBox aSwitchIsOpen;
    @BindView(R.id.travel_order_detail_buttonSave)
    Button buttonSave;
    @BindView(R.id.travel_order_detail_buttonCancel)
    Button buttonCancel;
    private VehicleLab vehicleLab;
    private TravelOrder travelOrderData;
    TravelOrderLab travelOrderLab;
    private String userName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.travel_order_detail_fragment, container, false);
        ButterKnife.bind(this, view);
        buttonCancel.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        travelOrderLab = new TravelOrderLab();

        Bundle bundle = getArguments();
        travelOrderData = (TravelOrder) bundle.getSerializable(TRAVEL_ORDER_DETAILS);
        userName = bundle.getString(USER_NAME);

        vehicleLab = new VehicleLab();

        editTextUser.setText(userName);
        editTextVehicle.setText(travelOrderData.getVehicleId());
        editTextStartDate.setText(travelOrderData.getStartDate());
        editTextEndDate.setText(travelOrderData.getEndDate());
        editTextStartMileage.setText(Long.toString(travelOrderData.getStartKm()));
        editTextEndMileage.setText(Long.toString(travelOrderData.getEndKm()));
        editTextEndMileage.setClickable(true);
        editTextEndMileage.addTextChangedListener(this);
        editTextCrossedMileage.setText(Long.toString(travelOrderData.getCrossedKm()));
        aSwitchIsOpen.setChecked(travelOrderData.isOpen());
        DateTimeFormatter fmt = DateTimeFormat.forPattern(" dd.MM.yyyy.");
        DateTime openDate = DateTime.now();
        String date = openDate.toString(fmt);
        editTextEndDate.setText(openDate.toString(fmt));


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
}
