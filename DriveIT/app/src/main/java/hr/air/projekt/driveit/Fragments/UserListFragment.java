package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Adapters.UserAdapter;
import hr.air.projekt.driveit.Helper.NavigationItem;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;

/**
 * Created by Stjepan on 5.12.2016..
 */

public class UserListFragment extends Fragment implements NavigationItem {
    private static final String CHILD_USER = "users";
    private RecyclerView userRecyclerView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userListReference;
    private UserAdapter adapter;
    private UserLab userLab = new UserLab();

    //Navigation manager
    private int position;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list_fragment, container, false);
        userListReference = FirebaseDatabase.getInstance().getReference().child(CHILD_USER);
        userRecyclerView = (RecyclerView) view.findViewById(R.id.user_recycler_view);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        userListReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> userLists = new ArrayList<User>();
                userLists = userLab.getUserList((Map<String, Object>) dataSnapshot.getValue());

                adapter = new UserAdapter(getActivity(), userLists);
                userRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }


    @Override
    public String getItemName() {
        return "Users";
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}
