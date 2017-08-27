package hr.air.projekt.datamodule;

import java.io.Serializable;

/**
 * Created by mico on 13.12.2016..
 */

public class Vehicle implements Serializable{
    @com.google.gson.annotations.SerializedName("manufacturer")
    private String manufacturer;
    @com.google.gson.annotations.SerializedName("model")
    private String model;
    @com.google.gson.annotations.SerializedName("productYear")
    private Long productYear;
    @com.google.gson.annotations.SerializedName("registrationDate")
    private String registrationDate;
    @com.google.gson.annotations.SerializedName("registrationExpired")
    private String registrationExpired;
    @com.google.gson.annotations.SerializedName("kw")
    private Long kw;
    @com.google.gson.annotations.SerializedName("chassisNumber")
    private String chassisNumber;
    @com.google.gson.annotations.SerializedName("registrationNumber")
    private String registrationNumber;
    @com.google.gson.annotations.SerializedName("averageFuelConsumption")
    private Double averageFuelConsumption;
    @com.google.gson.annotations.SerializedName("free")
    private boolean free;
    @com.google.gson.annotations.SerializedName("fuelStatus")
    private Long fuelStatus;
    @com.google.gson.annotations.SerializedName("KmNumber")
    private Long KmNumber;

    public Vehicle() {
    }

    public Vehicle(String manufacturer, String model, Long productYear, String registrationDate, String registrationExpired, Long kw, String chassisNumber, String registrationNumber,
                   Double averageFuelConsumption, boolean free, Long fuelStatus, Long kmNumber) {
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
        this.KmNumber = kmNumber;
    }

    public Long getKmNumber() {
        return KmNumber;
    }

    public void setKmNumber(Long kmNumber) {
        KmNumber = kmNumber;
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
