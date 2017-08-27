package hr.air.projekt.driveit.rest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleResponse {

    @SerializedName("manufacturer")
    @Expose
    private String manufacturer;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("productYear")
    @Expose
    private Integer productYear;
    @SerializedName("registrationDate")
    @Expose
    private String registrationDate;
    @SerializedName("registrationExpired")
    @Expose
    private String registrationExpired;
    @SerializedName("kw")
    @Expose
    private Integer kw;
    @SerializedName("chassisNumber")
    @Expose
    private String chassisNumber;
    @SerializedName("registrationNumber")
    @Expose
    private String registrationNumber;
    @SerializedName("averageFuelConsumption")
    @Expose
    private Double averageFuelConsumption;
    @SerializedName("free")
    @Expose
    private Boolean free;
    @SerializedName("fuelStatus")
    @Expose
    private Integer fuelStatus;
    @SerializedName("kmNumber")
    @Expose
    private Integer kmNumber;

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

    public Integer getProductYear() {
        return productYear;
    }

    public void setProductYear(Integer productYear) {
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

    public Integer getKw() {
        return kw;
    }

    public void setKw(Integer kw) {
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

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Integer getFuelStatus() {
        return fuelStatus;
    }

    public void setFuelStatus(Integer fuelStatus) {
        this.fuelStatus = fuelStatus;
    }

    public Integer getKmNumber() {
        return kmNumber;
    }

    public void setKmNumber(Integer kmNumber) {
        this.kmNumber = kmNumber;
    }

}