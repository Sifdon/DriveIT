package hr.air.projekt.driveit;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.Service;
import hr.air.projekt.datamodule.User;

/**
 * Created by Stjepan on 26.1.2017..
 */

public class ServiceLab {

    private final static String CHILD_SERVICE = "services";

    public ArrayList<Service> getServiceList(Map<String, Object> serviceMap) {
        ArrayList<Service> serviceList = new ArrayList<Service>();
        for (Map.Entry<String, Object> entry : serviceMap.entrySet()) {
            Map singleService = (Map) entry.getValue();
            Service s = new Service((String)singleService.get("serviceId"),
                    (String)singleService.get("date"),
                    (String)singleService.get("description"),
                    (String)singleService.get("dateOfNextService"),
                    (Boolean)singleService.get("type"),
                    (Long)singleService.get("priceOfParts"),
                    (Long)singleService.get("priceOfWork"),
                    (String)singleService.get("mechanic"),
                    (String)singleService.get("vehicleId"));
            serviceList.add(s);
        }
        return serviceList;
    }

    public void updateService(Service s){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_SERVICE).child(s.getServiceId());
        db.setValue(s);
    }

    public void addService(Service s){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_SERVICE).child(s.getServiceId());
        db.setValue(s);
    }


}
