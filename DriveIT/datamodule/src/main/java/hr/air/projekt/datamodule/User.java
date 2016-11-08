package hr.air.projekt.datamodule;

/**
 * Created by Stjepan on 8.11.2016..
 */

public class User {
    private String UID;
    private String firstName;
    private String lastName;


    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
