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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.Helper.CurrentFirebaseAuth;
import hr.air.projekt.driveit.Helper.CurrentUser;
import hr.air.projekt.driveit.R;


public class AddTravelOrderFragment extends Fragment implements View.OnClickListener{

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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_travel_order_fragment, container, false);
        ButterKnife.bind(this, view);

        addTravelOrderButtonSave.setOnClickListener(this);
        addTravelOrderButtonCancel.setOnClickListener(this);
        addTravelOrderUser.setText(CurrentUser.getUser().getFirstName() + " "+ CurrentUser.getUser().getLastName());

        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss, DD.MM.yyyy.");
        DateTime openDate = DateTime.now();
        addTravelOrderStartDate.setText(openDate.toString(fmt));

        return view;
    }


    private void updateTravelOrder(){
        travelOrder.setUserId(CurrentUser.getUser().getUid());



    }

    @Override
    public void onClick(View v) {
        if(v==addTravelOrderButtonSave){

        }
        else if(v==addTravelOrderButtonCancel){

        }
    }

}
