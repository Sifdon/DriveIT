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
            System.out.println("man " + singleVehicle.get("manufacturer").getClass());
            System.out.println("mod " + singleVehicle.get("model").getClass() );
            System.out.println("py" + singleVehicle.get("productYear").getClass());
            System.out.println("rd" + singleVehicle.get("registrationDate") .getClass());
            System.out.println("re" + singleVehicle.get("registrationExpired").getClass());
            System.out.println("kw" + singleVehicle.get("kw").getClass());
            System.out.println("cn" + singleVehicle.get("chassisNumber").getClass());
            System.out.println("rn" + singleVehicle.get("registrationNumber").getClass());
            System.out.println("afc " + singleVehicle.get("averageFuelConsum").getClass());
            System.out.println("free" + singleVehicle.get("free").getClass());
            System.out.println("fs" + singleVehicle.get("fuelStatus").getClass());
            System.out.println("ajmoooo" + singleVehicle.get("averageFuelConsum").toString());

            Vehicle v = new Vehicle(
                    (String)singleVehicle.get("manufacturer"),
                    (String) singleVehicle.get("model"),
                    (Long) singleVehicle.get("productYear"),
                    (String) singleVehicle.get("registrationDate"),
                    (String) singleVehicle.get("registrationExpired"),
                    (Long) singleVehicle.get("kw"),
                    (String) singleVehicle.get("chassisNumber"),
                    (String) singleVehicle.get("registrationNumber"),
                    (Double) singleVehicle.get("averageFuelConsum"),
                    (Boolean) singleVehicle.get("free"),
                    (Long) singleVehicle.get("fuelStatus"));
            vehicleList.add(v);

        }
        return vehicleList;
    }

    public ArrayList<String> getAllChassisNumbers(ArrayList<Vehicle> vehicles){
        ArrayList<String> chassisNumbers = new ArrayList<String>();
        for(Vehicle v:vehicles){
            chassisNumbers.add(v.getChassisNumber());
        }
        return chassisNumbers;
    }

    public void addVehicle(Vehicle malfunction){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_VEHICLES).child(vehicle.getChassisNumber());
        db.setValue(malfunction);
    }




}
