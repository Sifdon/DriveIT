package hr.air.projekt.driveit.Adapters;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.driveit.Fragments.MalfunctionDetailFragment;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class MalfunctionHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
    private static final String MALFUNCTION_EXTRA_NAME = "malfunctionName";
    private static final String MALFUNCTION_EXTRA_ID = "malfunctionId";
    private static final String MALFUNCTION_EXTRA_DESCRIPTION = "malfunctionDescription";
    private static final String MALFUNCTION_EXTRA_TYPE = "malfunctionType";
    private static final String MALFUNCTION_EXTRA_SOLVED = "malfunctionSolved";
    private static final String MALFUNCTION_EXTRA_VEHICLE = "malfunctionVehicle";
    private static final String MALFUNCTION_EXTRA_REPORTED = "malfunctionReported";
    private static final String MALFUNCTION_EXTRA_TIME = "malfunctionTime";

    private TextView textViewName;
    private TextView textViewDescription;
    private CheckBox checkBoxSolved;
    private List<Malfunction> allMalfunctions;
    private MalfunctionAdapter adapter;
    private Malfunction malfunctionData;

    public MalfunctionHolder(View itemView,List<Malfunction> malfunctions, MalfunctionAdapter adapter) {
        super(itemView);
        itemView.setOnLongClickListener(this);
        textViewName = (TextView) itemView.findViewById(R.id.recycler_view_malfunctionName);
        textViewDescription =(TextView)itemView.findViewById(R.id.recycler_view_malfunctionDescription);
        checkBoxSolved = (CheckBox) itemView.findViewById(R.id.recycler_view_malfunctionSolved);
        checkBoxSolved.setClickable(false);
        textViewName.setEnabled(false);
        textViewDescription.setEnabled(false);
        this.allMalfunctions = malfunctions;
        this.adapter = adapter;
    }

    public void bindMalfunction(Malfunction malfunction) {
        this.malfunctionData = malfunction;
        textViewName.setText(malfunction.getName());
        textViewDescription.setText(malfunction.getDescription());
        checkBoxSolved.setChecked(malfunction.isSolved());
    }


    @Override
    public boolean onLongClick(View v) {
        Fragment nextFrag= new MalfunctionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MALFUNCTION_EXTRA_ID,malfunctionData.getMalfunctionId());
        bundle.putString(MALFUNCTION_EXTRA_NAME, malfunctionData.getName());
        bundle.putString(MALFUNCTION_EXTRA_DESCRIPTION, malfunctionData.getDescription());
        bundle.putString(MALFUNCTION_EXTRA_VEHICLE, malfunctionData.getVehicle());
        bundle.putString(MALFUNCTION_EXTRA_TIME, malfunctionData.getTime());
        bundle.putBoolean(MALFUNCTION_EXTRA_TYPE, malfunctionData.isType());
        bundle.putBoolean(MALFUNCTION_EXTRA_SOLVED, malfunctionData.isSolved());
        bundle.putString(MALFUNCTION_EXTRA_REPORTED, malfunctionData.getReported());
        nextFrag.setArguments(bundle);
        CurrentActivity.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, nextFrag,null)
                .addToBackStack(null)
                .commit();


        return false;
    }
}
