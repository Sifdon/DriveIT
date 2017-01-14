package hr.air.projekt.datamodule;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class Malfunction {
    private String name;
    private int malfunctionId;
    private String description;
    private String veichle;
    private String reported;
    private Date time;
    private boolean type;
    private boolean solved;

    public Malfunction() {
    }

    public Malfunction(String name, int malfunctionId, String description,
                       String veichle, String reported,
                       Date time, boolean type, boolean solved) {
        this.name = name;
        this.malfunctionId = malfunctionId;
        this.description = description;
        this.veichle = veichle;
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

    public int getMalfunctionId() {
        return malfunctionId;
    }

    public void setMalfunctionId(int malfunctionId) {
        this.malfunctionId = malfunctionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVeichle() {
        return veichle;
    }

    public void setVeichle(String veichle) {
        this.veichle = veichle;
    }

    public String getReported() {
        return reported;
    }

    public void setReported(String reported) {
        this.reported = reported;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
