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

            //double long conversion problem
            Double priceOfParts = null;
            Double priceOfWork = null;
            Long l;
            if (singleService.get("priceOfParts").getClass() == Double.class) {
                priceOfParts = (Double) singleService.get("priceOfParts");
            }
            if(singleService.get("priceOfParts").getClass()== Long.class){
                l = (Long)  singleService.get("priceOfParts");
                priceOfParts = l.doubleValue();
            }

            if (singleService.get("priceOfWork").getClass() == Double.class) {
                priceOfWork = (Double) singleService.get("priceOfWork");
            }
            if(singleService.get("priceOfWork").getClass()== Long.class){
                l = (Long)  singleService.get("priceOfWork");
                priceOfWork = l.doubleValue();
            }



            Service s = new Service((String)singleService.get("serviceId"),
                    (String)singleService.get("date"),
                    (String)singleService.get("description"),
                    (String)singleService.get("dateOfNextService"),
                    (Boolean)singleService.get("type"),
                    priceOfParts,
                    priceOfWork,
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
