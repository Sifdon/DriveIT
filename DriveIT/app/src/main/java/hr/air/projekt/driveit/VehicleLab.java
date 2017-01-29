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
    private static final String CHILD_VEHICLES = "vehicles";
    private Vehicle vehicle;
    private Map<String, Object> vehicles;
    private DatabaseReference updateVehicleRef, deleteVehicleRef;
    private FirebaseDatabase firebaseDatabase;

    public VehicleLab() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public Map<String, Object> getAllVehicles(final DataSnapshot snapshot) {
        vehicles = (Map<String, Object>) snapshot.getValue();
        return vehicles;
    }

    public ArrayList<Vehicle> getVehicleList(Map<String, Object> vehicleMap) {

        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
        for (Map.Entry<String, Object> entry : vehicleMap.entrySet()) {
            Map singleVehicle = (Map) entry.getValue();
            //double long conversion problem
            Double afc = null;
            Long l;
            if (singleVehicle.get("averageFuelConsumption").getClass() == Double.class) {
                afc = (Double) singleVehicle.get("averageFuelConsumption");
            }
            if (singleVehicle.get("averageFuelConsumption").getClass() == Long.class) {
                l = (Long) singleVehicle.get("averageFuelConsumption");
                afc = l.doubleValue();
            }

            Vehicle v = new Vehicle(
                    (String) singleVehicle.get("manufacturer"),
                    (String) singleVehicle.get("model"),
                    (Long) singleVehicle.get("productYear"),
                    (String) singleVehicle.get("registrationDate"),
                    (String) singleVehicle.get("registrationExpired"),
                    (Long) singleVehicle.get("kw"),
                    (String) singleVehicle.get("chassisNumber"),
                    (String) singleVehicle.get("registrationNumber"),
                    afc,
                    (Boolean) singleVehicle.get("free"),
                    (Long) singleVehicle.get("fuelStatus"),
                    (Long) singleVehicle.get("kmNumber"));

            vehicleList.add(v);

        }
        return vehicleList;
    }

    public ArrayList<String> getAllChassisNumbers(ArrayList<Vehicle> vehicles) {
        ArrayList<String> chassisNumbers = new ArrayList<String>();
        for (Vehicle v : vehicles) {
            chassisNumbers.add(v.getChassisNumber());
        }
        return chassisNumbers;
    }

    public void addVehicle(Vehicle vehicle) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_VEHICLES).child(vehicle.getChassisNumber());
        db.setValue(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_VEHICLES).child(vehicle.getChassisNumber());
        db.setValue(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_VEHICLES).child(vehicle.getChassisNumber());
        db.removeValue();
    }
}
