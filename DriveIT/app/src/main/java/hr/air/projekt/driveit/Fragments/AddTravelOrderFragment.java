package hr.air.projekt.driveit.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air.projekt.driveit.R;


public class AddTravelOrderFragment extends Fragment {

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

    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_travel_order_fragment, container, false);
        ButterKnife.bind(this, view);


        return view;
    }


}
