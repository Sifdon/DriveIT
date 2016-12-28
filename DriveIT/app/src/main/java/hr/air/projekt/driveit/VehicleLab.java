package hr.air.projekt.driveit;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import hr.air.projekt.datamodule.Vehicle;

/**
 * Created by mislav on 22.12.16..
 */

public class VehicleLab {
    private static final String CHILD_VEHICLES ="vehicles";
    private Vehicle vehicle;
    private Map<String,Object> vehicles;
    private DatabaseReference updateVehicleRef, deleteVehicleRef;
    private FirebaseDatabase firebaseDatabase;

    public VehicleLab() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public Map<String, Object> getAllVehicles (final DataSnapshot snapshot){
        vehicles = (Map<String, Object>) snapshot.getValue();
        return vehicles;
    }
    public ArrayList<Vehicle> getVehicleList(Map<String,Object> vehicleMap){

        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
        for (Map.Entry<String,Object> entry:vehicleMap.entrySet()){
            Map singleVehicle = (Map)entry.getValue();
            Vehicle v = new Vehicle(
                    (String)singleVehicle.get("manufacturer"),
                    (String) singleVehicle.get("model"),
                    (String) singleVehicle.get("productYear"),
                    (String) singleVehicle.get("registrationDate"),
                    (String) singleVehicle.get("registrationExpired"),
                    (String) singleVehicle.get("kw"),
                    (String) singleVehicle.get("chassisNumber"),
                    (String) singleVehicle.get("registrationNumber"),
                    (Double) singleVehicle.get("averageFuelConsum"),
                    (Boolean) singleVehicle.get("free"),
                    (Long) singleVehicle.get("fuelStatus"));
            vehicleList.add(v);
        }
        return vehicleList;
    }




}