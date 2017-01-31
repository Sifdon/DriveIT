package hr.air.projekt.driveit.Adapters;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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
import butterknife.ButterKnife;
import hr.air.projekt.datamodule.Vehicle;
import hr.air.projekt.driveit.Fragments.VehicleDetailFragment;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.VehicleLab;

/**
 * Created by mislav on 23.12.16..
 */

public class VehicleHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

    private static final String VEHICLE_DETAILS = "VehicleDetails";


    @BindView(R.id.list_manufacturer)
    EditText editTextManufacturer;
    @BindView(R.id.list_model)
    EditText editTextmodel;
    @BindView(R.id.list_registration_number)
    EditText editTextregistrationNumber;
    @BindView(R.id.list_driver)
    EditText editTextdriver;
    @BindView(R.id.list_status)
    EditText editTextfree;
    @BindView(R.id.list_button_deleteVehicle)
    Button buttonDelete;

    private Vehicle vehicledata;

    private List<Vehicle> allVehicle;
    private VehicleLab vehicleLab = new VehicleLab();
    private VehicleAdapter adapter;

    public VehicleHolder(View itemView, List<Vehicle> vehicles, VehicleAdapter vehicleAdapter) {
        super(itemView);
        itemView.setOnLongClickListener(this);
        ButterKnife.bind(this, itemView);

        editTextManufacturer.setEnabled(false);
        editTextfree.setEnabled(false);
        // editTextdriver.setEnabled(false);
        editTextmodel.setEnabled(false);
        editTextregistrationNumber.setEnabled(false);
        buttonDelete.setOnClickListener(this);

        this.allVehicle = vehicles;
        this.adapter = vehicleAdapter;
    }


    public void bindVehicle(Vehicle vehicle) {
        this.vehicledata = vehicle;
        editTextManufacturer.setText(vehicledata.getManufacturer());
        editTextmodel.setText(vehicledata.getModel());
        editTextregistrationNumber.setText(vehicledata.getRegistrationNumber());
        if (vehicledata.isFree() == true) {
            editTextfree.setText("Free");
            editTextfree.setTextColor(Color.rgb(0, 119, 51));
            editTextfree.setTypeface(null, Typeface.BOLD);
        } else {
            editTextfree.setText("Taken");
            editTextfree.setTextColor(Color.RED);
            editTextfree.setTypeface(null, Typeface.BOLD);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(VEHICLE_DETAILS, vehicledata);

        Fragment nextFragment = new VehicleDetailFragment();
        nextFragment.setArguments(bundle);

        CurrentActivity.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, nextFragment, null)
                .addToBackStack(null)
                .commit();

        return false;
    }

    @Override
    public void onClick(View view) {

        if (view == buttonDelete) {
            final AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
            alertDialog.setTitle(itemView.getContext().getString(R.string.removal_question_vehicle));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, itemView.getContext().getString(R.string.delete_vehicle),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            vehicleLab.deleteVehicle(vehicledata);
                            allVehicle.remove(getAdapterPosition());
                            adapter.notifyItemRemoved(getAdapterPosition());
                            adapter.notifyDataSetChanged();
                            adapter.notifyItemRangeChanged(getAdapterPosition(), allVehicle.size());
                            alertDialog.dismiss();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, itemView.getContext().getString(R.string.cancel_delete_vehicle),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });

            alertDialog.show();
        }
    }
}
