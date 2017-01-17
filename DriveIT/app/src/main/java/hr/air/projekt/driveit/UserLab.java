package hr.air.projekt.driveit;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.User;

/**
 * Created by Stjepan on 12.12.2016..
 */

public class UserLab {
    private static final String CHILD_USER = "users";

    private ArrayList<User> allUsers = new ArrayList<User>();

    public UserLab() {
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

    public User getUserById(String userId, ArrayList<User> users) {
        User user = new User();
        for(User u:users){
            if(u.getUid().equals(userId)){
                user = u;
            }
        }
        return user;
    }

    public void addUser(final User user) {
        DatabaseReference addUser = FirebaseDatabase.getInstance().getReference().child(CHILD_USER).child(user.getUid());
        addUser.setValue(user);
    }

    public void updateUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference().child(CHILD_USER).child(user.getUid());
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
        DatabaseReference dba = database.getReference().child(CHILD_USER).child(u.getUid());
        dba.removeValue();
    }
}
