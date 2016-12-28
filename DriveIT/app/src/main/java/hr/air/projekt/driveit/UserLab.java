package hr.air.projekt.driveit;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Helper.CurrentActivity;

/**
 * Created by Stjepan on 12.12.2016..
 */

public class UserLab {
    private static final String CHILD_USER = "users";

    private Map<String, Object> users;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference addUserReference;

    public UserLab() {
    }

    public Map<String, Object> getAllUsers(DataSnapshot snapshot) {
        users = (Map<String, Object>) snapshot.getValue();
        return users;
    }

    public ArrayList<User> getUserList(Map<String, Object> userMap) {
        ArrayList<User> userList = new ArrayList<User>();
        for (Map.Entry<String, Object> entry : userMap.entrySet()) {
            //Get user map
            Map singleUser = (Map) entry.getValue();
            User u = new User((String) singleUser.get("uid"), (String) singleUser.get("firstName"), (String) singleUser.get("lastName"),
                    (String) singleUser.get("email"));
            userList.add(u);
        }
        return userList;
    }

    public User getUserbyId(String UserId, Map<String, Object> users) {
        ArrayList<User> userList = new ArrayList<User>(getUserList(users));
        User user = new User();
        for (User u : userList) {
            if(UserId.equals(u.getUID())){
                user = u;
                break;
            }
        }
        return user;
    }

    public void addUser(final User user) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        addUserReference = firebaseDatabase.getReference().child(CHILD_USER).child(user.getUID());
        addUserReference.setValue(user);
    }

    public void updateUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference().child(CHILD_USER).child(user.getUID());
        db.setValue(user);
    }

    public void setRoles(String uid,boolean m, boolean a, boolean u){
        DatabaseReference dbm = FirebaseDatabase.getInstance().getReference().child("roles").child("admin").child(uid);
        dbm.setValue(m);
        DatabaseReference dba = FirebaseDatabase.getInstance().getReference().child("roles").child("mechanic").child(uid);
        dba.setValue(a);
        DatabaseReference dbu = FirebaseDatabase.getInstance().getReference().child("roles").child("user").child(uid);
        dbu.setValue(u);
    }


    public void deleteUser(User u) {
        System.out.println(u.getFirstName());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dba = database.getReference().child(CHILD_USER).child(u.getUID());
        dba.removeValue();
    }
}
