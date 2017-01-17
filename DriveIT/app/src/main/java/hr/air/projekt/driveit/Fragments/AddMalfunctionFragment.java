package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.util.UUID;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.CurrentUser;
import hr.air.projekt.driveit.MalfunctionLab;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;

/**
 * Created by Stjepan on 15.1.2017..
 */

public class AddMalfunctionFragment extends Fragment implements View.OnClickListener {

    private EditText editTextMalfunctionName;
    private EditText editTextMalfunctionDescription;
    private CheckBox checkBoxMalfunctionType;
    private CheckBox checkBoxMalfunctionSolved;
    private TextView textViewMalfunctionVehicle;
    private TextView textViewMalfunctionReported;
    private TextView textViewMalfunctionTime;
    private Button buttonMalfunctionSave;
    private Button buttonMalfunctionCancel;
    MalfunctionLab malfunctionLab = new MalfunctionLab();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_malfunction_fragment, container, false);
        editTextMalfunctionName = (EditText) view.findViewById(R.id.add_malfunction_name);
        editTextMalfunctionDescription = (EditText) view.findViewById(R.id.add_malfunction_description);
        checkBoxMalfunctionType = (CheckBox) view.findViewById(R.id.add_malfunction_type);
        checkBoxMalfunctionSolved = (CheckBox) view.findViewById(R.id.add_malfunction_solved);
        textViewMalfunctionVehicle = (TextView) view.findViewById(R.id.add_malfunction_vehicle);
        textViewMalfunctionReported = (TextView) view.findViewById(R.id.add_malfunction_user);
        textViewMalfunctionTime = (TextView) view.findViewById(R.id.add_malfunction_date);
        buttonMalfunctionSave = (Button) view.findViewById(R.id.add_malfunction_buttonSave);
        buttonMalfunctionCancel = (Button) view.findViewById(R.id.add_malfunction_buttonCancel);
        buttonMalfunctionSave.setOnClickListener(this);
        buttonMalfunctionCancel.setOnClickListener(this);


        textViewMalfunctionReported.setText(CurrentUser.getUser().getFirstName() + " " + CurrentUser.getUser().getLastName());
        textViewMalfunctionReported.setClickable(false);


        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss, DD.MM.yyyy.");
        DateTime d = DateTime.now();
        textViewMalfunctionTime.setText(d.toString(fmt));
        textViewMalfunctionTime.setClickable(false);
        textViewMalfunctionVehicle.setText("prikaz nakon naloga");
        textViewMalfunctionVehicle.setClickable(false);

        return view;

    }

    @Override
    public void onClick(View v) {
        if (v == buttonMalfunctionSave) {
            UUID id = UUID.randomUUID();
            Malfunction malfunction = new Malfunction(editTextMalfunctionName.getText().toString(),
                    id.toString(),
                    editTextMalfunctionDescription.getText().toString(),
                    textViewMalfunctionVehicle.getText().toString(),
                    textViewMalfunctionReported.getText().toString(),
                    textViewMalfunctionTime.getText().toString(),
                    checkBoxMalfunctionType.isChecked(),
                    checkBoxMalfunctionSolved.isChecked());
            malfunctionLab.addMalfunction(malfunction);
            Toast.makeText(CurrentActivity.getActivity(), R.string.malfunction_added,
                    Toast.LENGTH_SHORT).show();
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
        if (v == buttonMalfunctionCancel) {
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
    }
}
