package hr.air.projekt.driveit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hr.air.projekt.datamodule.Service;
import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;

/**
 * Created by Stjepan on 26.1.2017..
 */

public class ServiceHolder extends RecyclerView.ViewHolder {
    private TextView textViewMechanic;
    private TextView textViewDate;
    private TextView textViewDescription;
    private Service serviceData;
    private UserLab userLab = new UserLab();


    public ServiceHolder(View itemView, List<Service> services, ServiceAdapter adapter) {
        super(itemView);
        textViewMechanic = (TextView) itemView.findViewById(R.id.recycler_view_serviceMechanic);
        textViewDate = (TextView) itemView.findViewById(R.id.recycler_view_serviceDate);
        textViewDescription = (TextView) itemView.findViewById(R.id.recycler_view_serviceDescription);
    }

    public void bindService(Service service) {
        this.serviceData = service;
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("users").child(serviceData.getMechanic());
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> user = (Map<String, Object>) dataSnapshot.getValue();
                textViewMechanic.setText(user.get("firstName") + " " + user.get("lastName"));
                /*ArrayList<User> userList = userLab.getUserList((Map<String, Object>) dataSnapshot.getValue());
                User u = userLab.getUserById(serviceData.getMechanic(),userList);
                textViewMechanic.setText(u.getFirstName() +" " + u.getLastName());*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        textViewDate.setText(service.getDate());
        textViewDescription.setText(service.getDescription());
    }
}
