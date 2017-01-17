package hr.air.projekt.driveit.Helper;

import com.google.firebase.auth.FirebaseUser;

import hr.air.projekt.datamodule.User;

/**
 * Created by Stjepan on 16.1.2017..
 */

public class CurrentUser {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        CurrentUser.user = user;
    }
}
