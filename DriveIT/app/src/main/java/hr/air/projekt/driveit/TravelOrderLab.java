package hr.air.projekt.driveit;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.Service;
import hr.air.projekt.datamodule.TravelOrder;

/**
 * Created by Stjepan on 29.1.2017..
 */

public class TravelOrderLab{

    public static final String CHILD_TRAVEL_ORDER="travelOrders";

    public ArrayList<TravelOrder> getTravelOrderList (Map<String, Object> travelOrderMap) {
        ArrayList<TravelOrder> travelOrderList = new ArrayList<TravelOrder>();
        for (Map.Entry<String, Object> entry : travelOrderMap.entrySet()) {
            Map singleService = (Map) entry.getValue();
            TravelOrder t = new TravelOrder((String)singleService.get("travelOrderId"),
                    (String)singleService.get("userId"),
                    (String)singleService.get("vehicleId"),
                    (String)singleService.get("startDate"),
                    (String) singleService.get("endDate"),
                    (boolean) singleService.get("open"),
                    (Long)singleService.get("startKm"),
                    (long)singleService.get("endKm"),
                    (long)singleService.get("crossedKm"));
            travelOrderList.add(t);
        }
        return travelOrderList;
    }


    public void updateTravelOrder(TravelOrder t){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_TRAVEL_ORDER).child(t.getTravelOrderId());
        db.setValue(t);
    }


}
