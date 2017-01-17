package hr.air.projekt.datamodule;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class Malfunction {
    private String name;
    private String malfunctionId;
    private String description;
    private String vehicle;
    private String reported;
    private String time;
    private boolean type;
    private boolean solved;

    public Malfunction() {
    }

    public Malfunction(String name, String malfunctionId, String description,
                       String vehicle, String reported,
                       String time, boolean type, boolean solved) {
        this.name = name;
        this.malfunctionId = malfunctionId;
        this.description = description;
        this.vehicle = vehicle;
        this.reported = reported;
        this.time = time;
        this.type = type;
        this.solved = solved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMalfunctionId() {
        return malfunctionId;
    }

    public void setMalfunctionId(String malfunctionId) {
        this.malfunctionId = malfunctionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getReported() {
        return reported;
    }

    public void setReported(String reported) {
        this.reported = reported;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
