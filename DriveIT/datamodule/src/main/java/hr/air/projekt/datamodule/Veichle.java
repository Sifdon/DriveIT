package hr.air.projekt.datamodule;

/**
 * Created by mico on 13.12.2016..
 */

public class Veichle {
    private String manufacturer;
    private String model;
    private String productYear;
    private String registrationDate;
    private String registrationExpired;
    private String kw;
    private String chassisNumber;
    private String registrationNumber;
    private float averageFuelConsumption;
    private boolean free;
    private int fuelStatus;

    public Veichle() {
    }

    public Veichle(String manufacturer, String model, String productYear, String registrationDate, String registrationExpired, String kw,
                   String chassisNumber, String registrationNumber, float averageFuelConsumption,
                   boolean free, int fuelStatus) {
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

    public String getProductYear() {
        return productYear;
    }

    public void setProductYear(String productYear) {
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

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
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

    public float getAverageFuelConsumption() {
        return averageFuelConsumption;
    }

    public void setAverageFuelConsumption(float averageFuelConsumption) {
        this.averageFuelConsumption = averageFuelConsumption;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getFuelStatus() {
        return fuelStatus;
    }

    public void setFuelStatus(int fuelStatus) {
        this.fuelStatus = fuelStatus;
    }
}
