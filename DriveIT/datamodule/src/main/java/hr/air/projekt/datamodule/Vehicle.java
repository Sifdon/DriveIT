package hr.air.projekt.datamodule;

/**
 * Created by mico on 13.12.2016..
 */

public class Vehicle {
    private String manufacturer;
    private String model;
    private Long productYear;
    private String registrationDate;
    private String registrationExpired;
    private Long kw;
    private String chassisNumber;
    private String registrationNumber;
    private Double averageFuelConsumption;
    private boolean free;
    private Long fuelStatus;

    public Vehicle() {
    }

    public Vehicle(String manufacturer, String model, Long productYear, String registrationDate, String registrationExpired, Long kw, String chassisNumber, String registrationNumber,
                   Double averageFuelConsumption, boolean free, Long fuelStatus) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.productYear = productYear;
        this.registrationDate = registrationDate;
        this.registrationExpired = registrationExpired;
        this.kw = kw;
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.averageFuelConsumption = averageFuelConsumption;
        this.free = free;
        this.fuelStatus = fuelStatus;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getProductYear() {
        return productYear;
    }

    public void setProductYear(Long productYear) {
        this.productYear = productYear;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationExpired() {
        return registrationExpired;
    }

    public void setRegistrationExpired(String registrationExpired) {
        this.registrationExpired = registrationExpired;
    }

    public Long getKw() {
        return kw;
    }

    public void setKw(Long kw) {
        this.kw = kw;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Double getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    public void setAverageFuelConsumption(Double averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Long getFuelStatus() {
        return fuelStatus;
    }

    public void setFuelStatus(Long fuelStatus) {
        this.fuelStatus = fuelStatus;
    }
}
