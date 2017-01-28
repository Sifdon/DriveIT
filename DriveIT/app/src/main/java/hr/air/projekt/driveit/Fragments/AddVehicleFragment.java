package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import hr.air.projekt.datamodule.Vehicle;
import hr.air.projekt.driveit.Adapters.VehicleAdapter;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.VehicleLab;

/**
 * Created by mico on 28.1.2017..
 */

public class AddVehicleFragment extends Fragment implements View.OnClickListener {

    private EditText editTextManufacturerName;
    private EditText editTextmodelName;
    private EditText editTextProductYear;
    private EditText editTextKW;
    private EditText editTextChassisNumber;
    private EditText editTextregistrationNumber;
    private EditText editTextRegistrationDate;
    private EditText editTextRegistrationExpired;
    private EditText editTextAverageFuelConsumpt;
    private EditText editTextFuelStatus;
    private SeekBar seekBarAverageFuel;
    private CheckBox checkBoxisFree;
    private Button buttonVehicleCancel;
    private Button buttonVehicleSave;
    private DateTime registrationExpiredDate;
    private DateTime registrationDate;
    private DateTimeFormatter dateTimeFormatter;
    VehicleLab  vehicleLab = new VehicleLab();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_vehicle_fragment, container, false);

        editTextManufacturerName = (EditText) view.findViewById(R.id.txt_manufacturer);
        editTextmodelName = (EditText) view.findViewById(R.id.txt_model_veichle);
        editTextProductYear = (EditText) view.findViewById(R.id.txt_production_year);
        editTextKW = (EditText) view.findViewById(R.id.txt_kw);
        editTextChassisNumber = (EditText) view.findViewById(R.id.txt_chassis_number);
        editTextregistrationNumber = (EditText) view.findViewById(R.id.txt_registration_number);
        editTextRegistrationDate = (EditText) view.findViewById(R.id.txt_registration_date);
        editTextRegistrationExpired = (EditText) view.findViewById(R.id.txt_registration_expired);
        editTextAverageFuelConsumpt = (EditText) view.findViewById(R.id.txt_average_fuel_consumption);
        checkBoxisFree = (CheckBox) view.findViewById(R.id.checkBox2);
        buttonVehicleSave = (Button) view.findViewById(R.id.add_vehicle_buttonSave);
        buttonVehicleCancel = (Button) view.findViewById(R.id.add_vehicle_buttonCancel);
        editTextFuelStatus = (EditText) view.findViewById(R.id.add_vehicle_fuel_status);

        buttonVehicleCancel.setOnClickListener(this);
        buttonVehicleSave.setOnClickListener(this);

        dateTimeFormatter = DateTimeFormat.forPattern("DD.MM.yyyy.");


        return view;
    }

    @Override
    public void onClick(View view) {

        if(view == buttonVehicleSave){

            Vehicle vehicle = new Vehicle(
                    editTextManufacturerName.getText().toString(),
                    editTextmodelName.getText().toString(),
                    (Long.parseLong(editTextProductYear.getText().toString())),
                    editTextRegistrationDate.getText().toString(),
                    editTextRegistrationExpired.getText().toString(),
                    (Long.parseLong(editTextKW.getText().toString())),
                    editTextChassisNumber.getText().toString(),
                    editTextregistrationNumber.getText().toString(),
                    (Double.parseDouble(editTextAverageFuelConsumpt.getText().toString())),
                    checkBoxisFree.isChecked(),
                    (Long.parseLong(editTextFuelStatus.getText().toString())));
            vehicleLab.addVehicle(vehicle);
            Toast.makeText(CurrentActivity.getActivity(),"Vehicle added",Toast.LENGTH_SHORT).show();
            CurrentActivity.getActivity().getFragmentManager().popBackStack();


        }




    }
}
