package hr.air.projekt.datamodule;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Stjepan on 8.11.2016..
 */

public class User {
    private String UID;
    private String firstName;
    private String lastName;
    private String Email;

    public User(FragmentActivity activity, ArrayList<User> userList) {
    }

    public User() {
    }

    public User(String UID, String firstName, String lastName, String email) {
        this.UID = UID;
        this.firstName = firstName;
        this.lastName = lastName;
        Email = email;
    }

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
