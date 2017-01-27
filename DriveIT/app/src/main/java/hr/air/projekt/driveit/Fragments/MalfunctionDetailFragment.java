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

import java.util.UUID;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.MalfunctionLab;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 18.1.2017..
 */

public class MalfunctionDetailFragment extends Fragment implements View.OnClickListener{

    private static final String MALFUNCTION_EXTRA_NAME = "malfunctionName";
    private static final String MALFUNCTION_EXTRA_ID = "malfunctionId";
    private static final String MALFUNCTION_EXTRA_DESCRIPTION = "malfunctionDescription";
    private static final String MALFUNCTION_EXTRA_TYPE = "malfunctionType";
    private static final String MALFUNCTION_EXTRA_SOLVED = "malfunctionSolved";
    private static final String MALFUNCTION_EXTRA_VEHICLE = "malfunctionVehicle";
    private static final String MALFUNCTION_EXTRA_REPORTED = "malfunctionReported";
    private static final String MALFUNCTION_EXTRA_TIME = "malfunctionTime";

    private EditText editTextMalfunctionName;
    private EditText editTextMalfunctionDescription;
    private CheckBox checkBoxMalfunctionType;
    private CheckBox checkBoxMalfunctionSolved;
    private TextView textViewMalfunctionVehicle;
    private TextView textViewMalfunctionReported;
    private TextView textViewMalfunctionTime;
    private Button buttonMalfunctionSave;
    private Button buttonMalfunctionCancel;
    private MalfunctionLab malfunctionLab = new MalfunctionLab();
    private Malfunction m = new Malfunction();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.malfunction_detail_fragment,container,false);

        editTextMalfunctionName = (EditText) view.findViewById(R.id.malfunction_detail_name);
        editTextMalfunctionDescription = (EditText) view.findViewById(R.id.malfunction_detail_description);
        checkBoxMalfunctionType = (CheckBox) view.findViewById(R.id.malfunction_detail_type);
        checkBoxMalfunctionSolved = (CheckBox) view.findViewById(R.id.malfunction_detail_solved);
        textViewMalfunctionVehicle = (TextView) view.findViewById(R.id.malfunction_detail_vehicle);
        textViewMalfunctionReported = (TextView) view.findViewById(R.id.malfunction_detail_user);
        textViewMalfunctionTime = (TextView) view.findViewById(R.id.malfunction_detail_date);
        buttonMalfunctionSave = (Button) view.findViewById(R.id.malfunction_detail_buttonSave);
        buttonMalfunctionCancel = (Button) view.findViewById(R.id.malfunction_detail_buttonCancel);
        buttonMalfunctionSave.setOnClickListener(this);
        buttonMalfunctionCancel.setOnClickListener(this);


        Bundle b = getArguments();
        m.setMalfunctionId(b.getString(MALFUNCTION_EXTRA_ID));
        m.setName(b.getString(MALFUNCTION_EXTRA_NAME));
        m.setVehicle(b.getString(MALFUNCTION_EXTRA_VEHICLE));
        m.setDescription(b.getString(MALFUNCTION_EXTRA_DESCRIPTION));
        m.setReported(b.getString(MALFUNCTION_EXTRA_REPORTED));
        m.setTime(b.getString(MALFUNCTION_EXTRA_TIME));
        m.setType(b.getBoolean(MALFUNCTION_EXTRA_TYPE));
        m.setSolved(b.getBoolean(MALFUNCTION_EXTRA_SOLVED));

        editTextMalfunctionName.setText(m.getName());
        editTextMalfunctionDescription.setText(m.getDescription());
        textViewMalfunctionReported.setText(m.getReported());
        textViewMalfunctionTime.setText(m.getTime());
        textViewMalfunctionVehicle.setText(m.getVehicle());
        checkBoxMalfunctionSolved.setChecked(m.isSolved());
        checkBoxMalfunctionType.setChecked(m.isType());

        return view;
    }


    @Override
    public void onClick(View v) {
        if (v == buttonMalfunctionSave) {
            Malfunction malfunction = new Malfunction(editTextMalfunctionName.getText().toString(),
                    m.getMalfunctionId(),
                    editTextMalfunctionDescription.getText().toString(),
                    textViewMalfunctionVehicle.getText().toString(),
                    textViewMalfunctionReported.getText().toString(),
                    textViewMalfunctionTime.getText().toString(),
                    checkBoxMalfunctionType.isChecked(),
                    checkBoxMalfunctionSolved.isChecked());
            malfunctionLab.updateMalfunction(malfunction);
                Toast.makeText(CurrentActivity.getActivity(), R.string.malfunction_updated,
                        Toast.LENGTH_SHORT).show();
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
        if (v == buttonMalfunctionCancel) {
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
        }
    }
}
