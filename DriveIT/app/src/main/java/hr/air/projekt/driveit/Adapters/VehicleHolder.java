package hr.air.projekt.driveit.Adapters;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import hr.air.projekt.datamodule.Vehicle;
import hr.air.projekt.driveit.Fragments.VehicleDetailFragment;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.VehicleLab;

/**
 * Created by mislav on 23.12.16..
 */

public class VehicleHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    /*

    private static final String VEHICLE_MANUFACTURER = "manufacturer";
    private static final String VEHICLE_MODEL = "model";
    private static final String VEHICLE_PRODUCT_YEAR = "productYear";
    private static final String VEHICLE_REGISTRATION_DATE = "registrationDate";
    private static final String VEHICLE_REGISTRATION_EXPIRED = "registrationExpired";
    private static final String VEHICLE_KW = "kw";
    private static final String VEHICLE_CHASSIS_NUMBER = "chassisNumber";
    private static final String VEHICLE_REGISTRATION_NUMBER = "registrationNumber";
    private static final String VEHICLE_AVERAGE_FUEL_CONSUM = "averageFuelConsum";
    private static final String VEHICLE_FREE = "free";
    private static final String VEHICLE_FUEL_STATUS = "fuelStatus";
    private static final String VEHICLE_KM_NUMBER = "kmNumber";
    */

    private static final String VEHICLE_DETAILS = "VehicleDetails";

    private EditText editTextManufacturer;
    private EditText editTextmodel;
    private EditText editTextregistrationNumber;
    private EditText editTextdriver;
    private EditText editTextfree;
    private Button btnUpdate;
    private Context appContext;
    private Vehicle vehicledata;
    private EditText editTextKmNumber; //broj kilometara
    private List<Vehicle> allVehicle;
    private VehicleLab vehicleLab = new VehicleLab();
    private VehicleAdapter adapter;

    public VehicleHolder(View itemView, Context appContext, List<Vehicle> vehicles, VehicleAdapter vehicleAdapter) {
        super(itemView);
        itemView.setOnLongClickListener(this);
        editTextManufacturer = (EditText)itemView.findViewById(R.id.list_manufacturer);
        editTextmodel = (EditText)itemView.findViewById(R.id.list_model);
        editTextregistrationNumber = (EditText)itemView.findViewById(R.id.list_registration_number);
        //editTextdriver = (EditText) itemView.findViewById(R.id.list_driver);
        editTextfree = (EditText) itemView.findViewById(R.id.list_status);
        btnUpdate= (Button) itemView.findViewById(R.id.list_button_updateVehicle);
        editTextManufacturer.setEnabled(false);
        editTextfree.setEnabled(false);
       // editTextdriver.setEnabled(false);
        editTextmodel.setEnabled(false);
        editTextregistrationNumber.setEnabled(false);
        this.appContext = CurrentActivity.getActivity();
        this.allVehicle =vehicles;
        this.adapter = vehicleAdapter;


    }


    public void bindVehicle(Vehicle vehicle){
        this.vehicledata = vehicle;
        editTextManufacturer.setText(vehicledata.getManufacturer());
        editTextmodel.setText(vehicledata.getModel());
        editTextregistrationNumber.setText(vehicledata.getRegistrationNumber());
        if(vehicledata.isFree() == true){
            editTextfree.setText("Free");
            editTextfree.setTextColor(Color.rgb(0,119,51));
            editTextfree.setTypeface(null,Typeface.BOLD);
        }else {
            editTextfree.setText("Taken");
            editTextfree.setTextColor(Color.RED);
            editTextfree.setTypeface(null,Typeface.BOLD);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(VEHICLE_DETAILS, vehicledata);

        Fragment nextFragment = new VehicleDetailFragment();
        nextFragment.setArguments(bundle);

        CurrentActivity.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,nextFragment,null)
                .addToBackStack(null)
                .commit();

        return false;
    }
}
