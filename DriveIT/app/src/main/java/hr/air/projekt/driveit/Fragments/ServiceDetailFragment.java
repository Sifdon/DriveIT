package hr.air.projekt.driveit.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air.projekt.datamodule.Service;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.ServiceLab;

/**
 * Created by Stjepan on 26.1.2017..
 */

public class ServiceDetailFragment extends Fragment implements View.OnClickListener {
    private static final String SERVICE_DETAILS = "ServiceDetails";
    private static final String MECHANIC_NAME = "MechanicName";

    @BindView(R.id.service_detail_dateOfService)
    EditText editTextDateOfService;
    @BindView(R.id.service_detail_dateOfNextService)
    EditText editTextDateOfNextService;
    @BindView(R.id.service_detail_description)
    EditText editTextDescription;
    @BindView(R.id.service_detail_mechanic)
    EditText editTextMechanic;
    @BindView(R.id.service_detail_priceOfParts)
    EditText editTextPriceOfParts;
    @BindView(R.id.service_detail_priceOfWork)
    EditText editTextPriceOfWork;
    @BindView(R.id.service_detail_vehicle)
    EditText editTextVehicle;
    @BindView(R.id.service_detail_type)
    CheckBox checkBoxRegularService;
    @BindView(R.id.service_detail_buttonSave)
    Button buttonSave;
    @BindView(R.id.service_detail_buttonCancel)
    Button buttonCancel;

    private DateTime dateOfNextService;
    private DateTimeFormatter fmt;
    private Service service;
    private String mechanicName;

    private ServiceLab serviceLab = new ServiceLab();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_detail_fragment, container, false);
        Bundle bundle = getArguments();
        ButterKnife.bind(this, view);
        service = (Service) bundle.getSerializable(SERVICE_DETAILS);
        mechanicName = (String) bundle.getSerializable(MECHANIC_NAME);
        bindViews();

        fmt = DateTimeFormat.forPattern("DD.MM.yyyy.");
        dateOfNextService = fmt.parseDateTime(service.getDateOfNextService());


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == editTextDateOfNextService) {
            showDatePicker();
        } else if (v == buttonCancel) {
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        } else if (v == buttonSave) {
            updateServiceData();
            serviceLab.updateService(service);
            Toast.makeText(CurrentActivity.getActivity(), R.string.service_updated,
                    Toast.LENGTH_SHORT).show();
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }

    }

    private void updateServiceData() {
        service.setDescription(editTextDescription.getText().toString());
        service.setPriceOfParts(Double.parseDouble(editTextPriceOfParts.getText().toString()));
        service.setPriceOfWork(Double.parseDouble(editTextPriceOfWork.getText().toString()));
        service.setType(checkBoxRegularService.isChecked());
    }

    private void bindViews() {

        System.out.println("Vehicle: " + service.getVehicleId());
        editTextVehicle.setText(service.getVehicleId());
        editTextDateOfService.setText(service.getDate());
        editTextDateOfNextService.setText(service.getDateOfNextService());
        editTextDescription.setText(service.getDescription());
        editTextMechanic.setText(mechanicName);
        editTextPriceOfParts.setText(service.getPriceOfParts().toString());
        editTextPriceOfWork.setText(service.getPriceOfWork().toString());
        checkBoxRegularService.setChecked(service.getType());
        editTextDateOfNextService.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

    }

    private void showDatePicker() {
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
                editTextDateOfNextService.setText(sdf.format(myCalendar.getTime()));
                dateOfNextService = fmt.parseDateTime(sdf.format(myCalendar.getTime()));
                service.setDateOfNextService(dateOfNextService.toString(fmt));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(CurrentActivity.getActivity(), date, dateOfNextService.getYear(), dateOfNextService.getMonthOfYear(), dateOfNextService.getDayOfMonth());
        datePickerDialog.setCancelable(true);
        datePickerDialog.show();
    }
}

