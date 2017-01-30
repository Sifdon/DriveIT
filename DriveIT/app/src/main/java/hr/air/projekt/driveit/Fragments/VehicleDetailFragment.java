package hr.air.projekt.driveit.Fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import hr.air.projekt.datamodule.Vehicle;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.Validation;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.VehicleLab;

/**
 * Created by mico on 29.1.2017..
 */

public class VehicleDetailFragment extends Fragment implements View.OnClickListener {

    private static final String VEHICLE_DETAILS = "VehicleDetails";

    private EditText editTextManufacturerName;
    private EditText editTextmodelName;
    private EditText editTextProductYear;
    private EditText editTextKW;
    private EditText editTextChassisNumber;
    private EditText editTextregistrationNumber;
    private EditText editTextRegistrationDate;
    private EditText editTextRegistrationExpired;
    private EditText editTextAverageFuelConsumpt;
    private SeekBar seekBarfuelStatus;
    private CheckBox checkBoxisFree;
    private Button buttonVehicleCancel;
    private Button buttonVehicleSave;
    private DateTime registrationExpiredDate;
    private DateTime registrationDate;
    private DateTimeFormatter dateTimeFormatter;
    private EditText editTextkmNumber;
    private Vehicle vehicle;

    VehicleLab vehicleLab = new VehicleLab();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.vehicle_detail_fragment, container, false);
        Bundle bundle = getArguments();
        vehicle = (Vehicle) bundle.getSerializable(VEHICLE_DETAILS);
        bindViews(view);

        dateTimeFormatter = DateTimeFormat.forPattern("DD.MM.yyyy.");
        registrationDate = dateTimeFormatter.parseDateTime(vehicle.getRegistrationDate());
        registrationExpiredDate = dateTimeFormatter.parseDateTime(vehicle.getRegistrationExpired());

        return view;
    }

    @Override
    public void onClick(View view) {

        if (view == editTextRegistrationDate) {
            showDatePickerDate();

        } else if (view == editTextRegistrationExpired) {
            showDatePickerExpired();
        } else if (view == buttonVehicleCancel) {
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        } else if (view == buttonVehicleSave) {

            if (checkValidation()) {

                updateVehicleData();
                vehicleLab.updateVehicle(vehicle);
                Toast.makeText(CurrentActivity.getActivity(), R.string.vehicle_updated, Toast.LENGTH_SHORT).show();
                CurrentActivity.getActivity().getFragmentManager().popBackStack();
            }
            else {
                Toast.makeText(CurrentActivity.getActivity(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void updateVehicleData() {

            vehicle.setManufacturer(editTextManufacturerName.getText().toString());
            vehicle.setModel(editTextmodelName.getText().toString());
            vehicle.setProductYear((Long.parseLong(editTextProductYear.getText().toString())));
            vehicle.setRegistrationDate(editTextRegistrationDate.getText().toString());
            vehicle.setRegistrationExpired(editTextRegistrationExpired.getText().toString());
            vehicle.setKw((Long.parseLong(editTextKW.getText().toString())));
            vehicle.setChassisNumber(editTextChassisNumber.getText().toString());
            vehicle.setRegistrationNumber(editTextregistrationNumber.getText().toString());
            vehicle.setAverageFuelConsumption((Double.parseDouble(editTextAverageFuelConsumpt.getText().toString())));
            vehicle.setFree(checkBoxisFree.isChecked());
            vehicle.setFuelStatus(Long.valueOf(seekBarfuelStatus.getProgress()));
            vehicle.setKmNumber(Long.parseLong(editTextkmNumber.getText().toString()));



    }

    private void bindViews(View view) {

        editTextManufacturerName = (EditText) view.findViewById(R.id.txt_vehicle_detail_manufacturer);
        editTextmodelName = (EditText) view.findViewById(R.id.txt_detail_vehicle_model);
        editTextProductYear = (EditText) view.findViewById(R.id.txt_vehicle_detail_production_year);
        editTextKW = (EditText) view.findViewById(R.id.txt_vehicle_detail_kw);
        editTextChassisNumber = (EditText) view.findViewById(R.id.txt_vehicle_detail_chassis_number);
        editTextregistrationNumber = (EditText) view.findViewById(R.id.txt_vehicle_detail_registration_number);
        editTextRegistrationDate = (EditText) view.findViewById(R.id.txt_vehicle_detail_registration_date);
        editTextRegistrationExpired = (EditText) view.findViewById(R.id.txt_vehicle_detail_registration_expired);
        editTextAverageFuelConsumpt = (EditText) view.findViewById(R.id.txt_vehicle_detail_average_fuel_consumption);
        checkBoxisFree = (CheckBox) view.findViewById(R.id.chb_vehicle_detail_isFree);
        buttonVehicleSave = (Button) view.findViewById(R.id.vehicle_detail_buttonSave);
        buttonVehicleCancel = (Button) view.findViewById(R.id.vehicle_detail_buttonCancel);
        seekBarfuelStatus = (SeekBar) view.findViewById(R.id.vehicle_detail_seekFuelstatus);
        editTextkmNumber = (EditText) view.findViewById(R.id.txt_vehicle_detail_kmNumber);

        editTextManufacturerName.setText(vehicle.getManufacturer());
        editTextmodelName.setText(vehicle.getModel());
        editTextProductYear.setText(vehicle.getProductYear().toString());
        editTextKW.setText(vehicle.getKw().toString());
        editTextChassisNumber.setText(vehicle.getChassisNumber());
        editTextregistrationNumber.setText(vehicle.getRegistrationNumber());
        editTextRegistrationDate.setText(vehicle.getRegistrationDate());
        editTextRegistrationExpired.setText(vehicle.getRegistrationExpired());
        editTextAverageFuelConsumpt.setText(vehicle.getAverageFuelConsumption().toString());
        checkBoxisFree.setChecked(vehicle.isFree());
        seekBarfuelStatus.setProgress(Integer.valueOf(vehicle.getFuelStatus().toString()));
        editTextkmNumber.setText(vehicle.getKmNumber().toString());

        //listeneri
        buttonVehicleCancel.setOnClickListener(this);
        buttonVehicleSave.setOnClickListener(this);
        editTextRegistrationExpired.setOnClickListener(this);
        editTextRegistrationDate.setOnClickListener(this);
    }

    private void showDatePickerDate() {
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd.MM.yyyy."; // your format
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                editTextRegistrationDate.setText(sdf.format(myCalendar.getTime()));
                registrationDate = dateTimeFormatter.parseDateTime(sdf.format(myCalendar.getTime()));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(CurrentActivity.getActivity(), date, registrationDate.getYear(), registrationDate.getMonthOfYear(), registrationDate.getDayOfMonth());
        datePickerDialog.setCancelable(true);
        datePickerDialog.show();
    }

    private void showDatePickerExpired() {
        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd.MM.yyyy."; // your format
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                editTextRegistrationExpired.setText(sdf.format(myCalendar.getTime()));
                registrationExpiredDate = dateTimeFormatter.parseDateTime(sdf.format(myCalendar.getTime()));

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(CurrentActivity.getActivity(), date, registrationExpiredDate.getYear(), registrationExpiredDate.getMonthOfYear(), registrationExpiredDate.getDayOfMonth());
        datePickerDialog.setCancelable(true);
        datePickerDialog.show();
    }

    /*provjera da li su sva polja popunjena*/
    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(editTextManufacturerName)) {
            return false;
        }
        if (!Validation.hasText(editTextmodelName)) {
            return false;
        }
        if (!Validation.hasText(editTextProductYear)) {
            return false;
        }
        if (!Validation.hasText(editTextRegistrationDate)) {
            return false;
        }
        if (!Validation.hasText(editTextRegistrationExpired)) {
            return false;
        }
        if (!Validation.hasText(editTextKW)) {
            return false;
        }
        if (!Validation.hasText(editTextChassisNumber)) {
            return false;
        }
        if (!Validation.hasText(editTextregistrationNumber)) {
            return false;
        }
        if (!Validation.hasText(editTextAverageFuelConsumpt)) {
            return false;
        }
        if (!Validation.hasText(editTextkmNumber)) {
            return false;
        }


        return ret;
    }
}
