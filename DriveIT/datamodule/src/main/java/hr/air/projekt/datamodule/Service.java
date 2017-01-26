package hr.air.projekt.datamodule;

/**
 * Created by Stjepan on 26.1.2017..
 */

public class Service {
    private String serviceId;
    private String date;
    private String description;
    private String dateOfNextService;
    private Boolean type;
    private Float priceOfParts;
    private Float priceOfWork;
    private String mechanic;

    public Service() {
    }

    public String getServiceId() {
        return serviceId;
    }


    public Service(String serviceId, String date, String description, String dateOfNextService, Boolean type, Float priceOfParts, Float priceOfWork, String mechanic) {
        this.serviceId = serviceId;
        this.date = date;
        this.description = description;
        this.dateOfNextService = dateOfNextService;
        this.type = type;
        this.priceOfParts = priceOfParts;
        this.priceOfWork = priceOfWork;
        this.mechanic = mechanic;
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

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Float getPriceOfParts() {
        return priceOfParts;
    }

    public void setPriceOfParts(Float priceOfParts) {
        this.priceOfParts = priceOfParts;
    }

    public Float getPriceOfWork() {
        return priceOfWork;
    }

    public void setPriceOfWork(Float priceOfWork) {
        this.priceOfWork = priceOfWork;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }
}
