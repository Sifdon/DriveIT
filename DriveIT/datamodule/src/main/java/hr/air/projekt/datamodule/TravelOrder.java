package hr.air.projekt.datamodule;

import java.io.Serializable;

/**
 * Created by Stjepan on 29.1.2017..
 */

public class TravelOrder implements Serializable {
    private String travelOrderId;
    private String userId;
    private String vehicleId;
    private String startDate;
    private String endDate;
    private boolean isOpen;
    private long startKm;
    private long endKm;
    private long crossedKm;


    public TravelOrder() {
    }

    public TravelOrder(String travelOrderId,
                       String userId,
                       String vehicleId,
                       String startDate,
                       String endDate,
                       boolean isOpen,
                       long startKm,
                       long endKm,
                       long crossedKm) {
        this.travelOrderId = travelOrderId;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isOpen = isOpen;
        this.startKm = startKm;
        this.endKm = endKm;
        this.crossedKm = crossedKm;
    }

    public String getTravelOrderId() {
        return travelOrderId;
    }

    public void setTravelOrderId(String travelOrderId) {
        this.travelOrderId = travelOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public long getStartKm() {
        return startKm;
    }

    public void setStartKm(long startKm) {
        this.startKm = startKm;
    }

    public long getEndKm() {
        return endKm;
    }

    public void setEndKm(long endKm) {
        this.endKm = endKm;
    }

    public long getCrossedKm() {
        return endKm - startKm;
    }
    public void setCrossedKm(long crossedKm){
        this.crossedKm = crossedKm;
    }
}
