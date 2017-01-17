package hr.air.projekt.driveit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class MalfunctionHolder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private TextView textViewDescription;
    private CheckBox checkBoxSolved;
    private List<Malfunction> allMalfunctions;
    private MalfunctionAdapter adapter;
    private Malfunction malfunctionData;

    public MalfunctionHolder(View itemView,List<Malfunction> malfunctions, MalfunctionAdapter adapter) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.recycler_view_malfunctionName);
        textViewDescription =(TextView)itemView.findViewById(R.id.recycler_view_malfunctionDescription);
        checkBoxSolved = (CheckBox) itemView.findViewById(R.id.recycler_view_malfunctionSolved);
        this.allMalfunctions = malfunctions;
        this.adapter = adapter;
    }

    public void bindMalfunction(Malfunction malfunction) {
        this.malfunctionData = malfunction;
        textViewName.setText(malfunction.getName());
        textViewDescription.setText(malfunction.getDescription());
        checkBoxSolved.setChecked(malfunction.isSolved());
    }
}
