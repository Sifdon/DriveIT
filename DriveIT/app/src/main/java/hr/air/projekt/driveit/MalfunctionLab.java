package hr.air.projekt.driveit;

import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Helper.CurrentActivity;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class MalfunctionLab {
    private static final String CHILD_MALFUNCTION = "malfunction";

    public ArrayList<Malfunction> getMalfunctionsList(Map<String, Object> malfunctionMap) {
        ArrayList<Malfunction> malfunctionList = new ArrayList<Malfunction>();
        for (Map.Entry<String, Object> entry : malfunctionMap.entrySet()) {
            Map singleMalfunction = (Map) entry.getValue();
            Malfunction m = new Malfunction((String) singleMalfunction.get("name"), (String) singleMalfunction.get("malfunctionId"), (String) singleMalfunction.get("description"),
                    (String) singleMalfunction.get("veichle"), (String) singleMalfunction.get("reported"), (String) singleMalfunction.get("time"), (Boolean) singleMalfunction.get("type"), (Boolean) singleMalfunction.get("solved"));
            malfunctionList.add(m);
        }
        return malfunctionList;
    }

    public ArrayList<Malfunction> getAllMalfunctions() {
        final ArrayList<Malfunction> malfunctionList = new ArrayList<Malfunction>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_MALFUNCTION);
        final ValueEventListener valueEventListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> malfunctions = (Map<String, Object>) dataSnapshot.getValue();
                for (Map.Entry<String, Object> entry : malfunctions.entrySet()) {
                    //Get user map
                    Map singleMalfunction = (Map) entry.getValue();
                    Malfunction m = new Malfunction((String) singleMalfunction.get("name"), (String) singleMalfunction.get("malfunctionId"), (String) singleMalfunction.get("description"),
                            (String) singleMalfunction.get("veichle"), (String) singleMalfunction.get("reported"), (String) singleMalfunction.get("time"),
                            (Boolean)singleMalfunction.get("type"), (Boolean) singleMalfunction.get("solved"));
                    malfunctionList.add(m);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return malfunctionList;
    }

    public void addMalfunction(Malfunction malfunction){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_MALFUNCTION).child(malfunction.getMalfunctionId());
        db.setValue(malfunction);
    }

    public void updateMalfunction(Malfunction malfunction) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_MALFUNCTION).child(malfunction.getMalfunctionId());
        db.setValue(malfunction);
    }
}
