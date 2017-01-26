package hr.air.projekt.driveit;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.Service;
import hr.air.projekt.datamodule.User;

/**
 * Created by Stjepan on 26.1.2017..
 */

public class ServiceLab {

    public ArrayList<Service> gerServiceList(Map<String, Object> serviceMap) {
        ArrayList<Service> serviceList = new ArrayList<Service>();
        for (Map.Entry<String, Object> entry : serviceMap.entrySet()) {
            Map singleService = (Map) entry.getValue();
            Long lParts = (Long) singleService.get("priceOfParts");
            Float fParts = new Float ((float)lParts.longValue());
            Long lWork = (Long) singleService.get("priceOfWork");
            Float fWork = new Float ((float)lWork.longValue());

            Service s = new Service((String)singleService.get("serviceId"),
                    (String)singleService.get("date"),
                    (String)singleService.get("description"),
                    (String)singleService.get("dateOfNextService"),
                    (Boolean)singleService.get("type"),
                    fParts,
                    fWork,
                    (String)singleService.get("mechanic"));
            serviceList.add(s);
        }
        return serviceList;
    }

}
