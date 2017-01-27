package hr.air.projekt.datamodule;

import java.io.Serializable;

/**
 * Created by Stjepan on 26.1.2017..
 */

public class Service implements Serializable {
    private String serviceId;
    private String date;
    private String description;
    private String dateOfNextService;
    private String vehicleId;
    private Boolean type;
    private Long priceOfParts;
    private Long priceOfWork;
    private String mechanic;

    public Service() {
    }

    public String getServiceId() {
        return serviceId;
    }


    public Service(String serviceId, String date, String description, String dateOfNextService,
                   Boolean type, Long priceOfParts, Long priceOfWork, String mechanic, String vehicleId) {
        this.serviceId = serviceId;
        this.date = date;
        this.description = description;
        this.dateOfNextService = dateOfNextService;
        this.type = type;
        this.priceOfParts = priceOfParts;
        this.priceOfWork = priceOfWork;
        this.mechanic = mechanic;
        this.vehicleId = vehicleId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfNextService() {
        return dateOfNextService;
    }

    public void setDateOfNextService(String dateOfNextService) {
        this.dateOfNextService = dateOfNextService;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Long getPriceOfParts() {
        return priceOfParts;
    }

    public void setPriceOfParts(Long priceOfParts) {
        this.priceOfParts = priceOfParts;
    }

    public Long getPriceOfWork() {
        return priceOfWork;
    }

    public void setPriceOfWork(Long priceOfWork) {
        this.priceOfWork = priceOfWork;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }
}
