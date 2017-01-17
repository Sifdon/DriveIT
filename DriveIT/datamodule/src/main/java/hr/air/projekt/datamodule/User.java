package hr.air.projekt.datamodule;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

/**
 * Created by Stjepan on 8.11.2016..
 */

public class User {
    private String uid;
    private String firstName;
    private String lastName;
    private String email;

    public User(FragmentActivity activity, ArrayList<User> userList) {
    }

    public User() {
    }

    public User(String UID, String firstName, String lastName, String email) {
        this.uid = UID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
