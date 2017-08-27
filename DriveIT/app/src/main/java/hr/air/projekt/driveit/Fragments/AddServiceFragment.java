package hr.air.projekt.driveit.Fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air.projekt.datamodule.Service;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.CurrentFirebaseAuth;
import hr.air.projekt.driveit.Helper.CurrentUser;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.ServiceLab;
import hr.air.projekt.driveit.VehicleLab;

import static hr.air.projekt.driveit.R.layout.support_simple_spinner_dropdown_item;

/**
 * Created by Stjepan on 28.1.2017..
 */

public class AddServiceFragment extends Fragment implements View.OnClickListener{

    private static final String CHILD_VEHICLE = "vehicles";


    @BindView(R.id.add_service_dateOfService)
    EditText editTextDateOfService;
    @BindView(R.id.add_service_dateOfNextService)
    EditText editTextDateOfNextService;
    @BindView(R.id.add_service_description)
    EditText editTextDescription;
    @BindView(R.id.add_service_mechanic)
    EditText editTextMechanic;
    @BindView(R.id.add_service_priceOfParts)
    EditText editTextPriceOfParts;
    @BindView(R.id.add_service_priceOfWork)
    EditText editTextPriceOfWork;
    @BindView(R.id.add_service_vehiclePicker)
    Spinner spinnerVehicle;
    @BindView(R.id.add_service_type)
    CheckBox checkBoxRegularService;
    @BindView(R.id.add_service_buttonSave)
    Button buttonSave;
    @BindView(R.id.add_service_buttonCancel)
    Button buttonCancel;

    private VehicleLab vehicleLab = new VehicleLab();
    private ServiceLab serviceLab = new ServiceLab();

    private List<String> chassisNumbers = new ArrayList<String>();
    private DateTime dateOfNextService;
    private DateTimeFormatter fmt;
    private Service service = new Service();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_service_fragment, container, false);
        bindViews();
        ButterKnife.bind(this,view);

        fmt = DateTimeFormat.forPattern("dd.MM.yyyy.");
        dateOfNextService = DateTime.now(DateTimeZone.UTC);


        DateTimeFormatter fmtt = DateTimeFormat.forPattern("dd.MM.yyyy.");
        editTextDateOfService.setText(DateTime.now().toString(fmtt));

        editTextDateOfNextService.setOnClickListener(this);

        buttonCancel.setOnClickListener(this);
        buttonSave.setOnClickListener(this);

        editTextMechanic.setText(CurrentUser.getUser().getFirstName() + " " + CurrentUser.getUser().getLastName());
        editTextMechanic.setEnabled(false);
        editTextDateOfNextService.setText(R.string.choose_date);

        spinnerVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                service.setVehicleId(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    private void bindViews() {

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_VEHICLE);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chassisNumbers = vehicleLab.getAllChassisNumbers(vehicleLab.getVehicleList((Map<String, Object>)dataSnapshot.getValue()));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(CurrentActivity.getActivity(),android.R.layout.simple_spinner_item,chassisNumbers);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerVehicle.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onClick(View v) {
        if(v==editTextDateOfNextService){
            showDatePicker();
        }
        else if(v == buttonCancel){
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
        else if (v==buttonSave){
            updateServiceData();
            serviceLab.addService(service);
            Toast.makeText(CurrentActivity.getActivity(), R.string.service_added,
                    Toast.LENGTH_SHORT).show();
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
    }

    private void showDatePicker(){
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

    private void updateServiceData (){
        service.setServiceId(UUID.randomUUID().toString());
        service.setDate(editTextDateOfService.getText().toString());
        service.setDescription(editTextDescription.getText().toString());
        service.setType(checkBoxRegularService.isChecked());
        service.setPriceOfParts(Double.parseDouble(editTextPriceOfParts.getText().toString()));
        service.setPriceOfWork(Double.parseDouble(editTextPriceOfWork.getText().toString()));
        service.setMechanic(CurrentUser.getUser().getUid());
    }


}
