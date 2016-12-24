package hr.air.projekt.driveit.Helper;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Stjepan on 24.12.2016..
 */

public class CurrentFirebaseAuth {
    private static FirebaseAuth firebaseAuth;

    public static FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public static void setFirebaseAuth(FirebaseAuth fa) {
        firebaseAuth = fa;
    }
}
