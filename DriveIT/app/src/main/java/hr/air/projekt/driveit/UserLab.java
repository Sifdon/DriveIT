package hr.air.projekt.driveit;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import hr.air.projekt.datamodule.User;

/**
 * Created by Stjepan on 12.12.2016..
 */

public class UserLab {
    private static final String CHILD_USER = "users";
    private User user;
    private Map<String, Object> users;
    private DatabaseReference updateUserRef,deleteUserRef;
    private FirebaseDatabase database;


    public UserLab() {
        database = FirebaseDatabase.getInstance();

    }

    public Map<String, Object> getAllUsers(final DataSnapshot snapshot){
        users = (Map<String, Object>) snapshot.getValue();
        return users;
    }

    public ArrayList<User> getUserList(Map<String, Object> userMap){
        ArrayList<User> userList = new ArrayList<User>();
        for (Map.Entry<String, Object> entry : userMap.entrySet()) {
            //Get user map
            Map singleUser = (Map) entry.getValue();
            User u = new User((String)singleUser.get("UID"),(String)singleUser.get("firstName"),(String)singleUser.get("lastName"),
                    (String)singleUser.get("email"));
            userList.add(u);
        }
        return userList;
    }

    public User getUserbyId(String UserId,DatabaseReference databaseReference){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users = (Map<String,Object>) dataSnapshot.getValue();
                for(Map.Entry<String, Object> entry : users.entrySet()){
                    Map singleUser = (Map)entry.getValue();
                    user = new User(singleUser.get("UID").toString(),singleUser.get("firstName").toString(),singleUser.get("lastName").toString(),singleUser.get("email").toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return user;
    }

    public void addUser(User user, DatabaseReference databaseReference){

    }

    public void updateUser(User user){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference db = database.getReference().child(CHILD_USER).child(user.getUID());
        db.setValue(user);

    }


    public void deleteUser(User u){System.out.println(u.getFirstName());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dba = database.getReference().child(u.getUID());
        dba.removeValue();
    }
}
