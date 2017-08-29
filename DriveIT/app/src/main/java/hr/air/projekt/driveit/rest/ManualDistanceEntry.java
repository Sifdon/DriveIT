package hr.air.projekt.driveit.rest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.widget.EditText;

import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.UpdateDistance;
import hr.foi.air.common.OnDataLoaded;

/**
 * Created by Boki on 29.8.2017..
 */

public class ManualDistanceEntry implements UpdateDistance {
    String m_Text;
    OnDataLoaded onDataLoadedListener;

    public ManualDistanceEntry() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void getCrossedDistance(OnDataLoaded onDataLoaded) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CurrentActivity.getActivity());
        builder.setTitle("Enter end mileage");


        final EditText input = new EditText(CurrentActivity.getActivity());

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                onDataLoadedListener.onDataLoaded(Long.parseLong(m_Text));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void setOnDataLoaded(OnDataLoaded onDataLoadedListener ) {
        this.onDataLoadedListener=onDataLoadedListener;

    }
}
