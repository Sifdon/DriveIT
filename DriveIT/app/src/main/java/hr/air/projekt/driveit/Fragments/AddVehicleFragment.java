package hr.air.projekt.driveit.Fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air.projekt.datamodule.Vehicle;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.Validation;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.VehicleLab;

/**
 * Created by mico on 28.1.2017..
 */

public class AddVehicleFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.txt_manufacturer)
    EditText editTextManufacturerName;
    @BindView(R.id.txt_model_veichle)
    EditText editTextmodelName;
    @BindView(R.id.txt_production_year)
    EditText editTextProductYear;
    @BindView(R.id.txt_kw)
    EditText editTextKW;
    @BindView(R.id.txt_chassis_number)
    EditText editTextChassisNumber;
    @BindView(R.id.txt_registration_number)
    EditText editTextregistrationNumber;
    @BindView(R.id.txt_registration_date)
    EditText editTextRegistrationDate;
    @BindView(R.id.txt_registration_expired)
    EditText editTextRegistrationExpired;
    @BindView(R.id.txt_average_fuel_consumption)
    EditText editTextAverageFuelConsumpt;
    @BindView(R.id.seekFuelstatus)
    SeekBar seekBarAverageFuel;

    @BindView(R.id.add_vehicle_buttonCancel)
    Button buttonVehicleCancel;
    @BindView(R.id.add_vehicle_buttonSave)
    Button buttonVehicleSave;
    @BindView(R.id.txt_kmNumber)
    EditText editTextkmNumber;

    private DateTime registrationExpiredDate;
    private DateTime registrationDate;
    private DateTimeFormatter dateTimeFormatter;
    VehicleLab vehicleLab = new VehicleLab();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_vehicle_fragment, container, false);
        ButterKnife.bind(this, view);

        buttonVehicleCancel.setOnClickListener(this);
        buttonVehicleSave.setOnClickListener(this);
        editTextRegistrationExpired.setOnClickListener(this);
        editTextRegistrationDate.setOnClickListener(this);

        dateTimeFormatter = DateTimeFormat.forPattern("DD.MM.yyyy.");
        DateTime d = DateTime.now();
        registrationDate = d;
        registrationExpiredDate = d;

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
                        true,
                        Long.valueOf(seekBarAverageFuel.getProgress()),
                        Long.parseLong(editTextkmNumber.getText().toString()));
                vehicleLab.addVehicle(vehicle);
                Toast.makeText(CurrentActivity.getActivity(), R.string.vehicle_added, Toast.LENGTH_SHORT).show();
                CurrentActivity.getActivity().getFragmentManager().popBackStack();

            } else {
                Toast.makeText(CurrentActivity.getActivity(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*
    otvara dateTimePicker za odabir datuma
    registracije(editTextRegistrationDate)
    */
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

    /*
    otvara dateTimePicker za odabir datuma
    isteka registracije(editTextRegistrationExpired)
    */
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
