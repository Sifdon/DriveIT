package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Adapters.UserAdapter;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.NavigationItem;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;

/**
 * Created by Stjepan on 5.12.2016..
 */

public class UserListFragment extends Fragment implements NavigationItem,View.OnClickListener {
    private static final String CHILD_USER = "users";
    private RecyclerView userRecyclerView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userListReference;
    private UserAdapter adapter;
    private UserLab userLab = new UserLab();
    private FloatingActionButton floatingActionButtonAddUser;
    //Navigation manager
    private int position;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.user_list_fragment, container, false);
        userListReference = FirebaseDatabase.getInstance().getReference().child(CHILD_USER);
        userRecyclerView = (RecyclerView) view.findViewById(R.id.user_recycler_view);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        floatingActionButtonAddUser = (FloatingActionButton) view.findViewById(R.id.add_user);
        floatingActionButtonAddUser.setOnClickListener(this);
        userListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> userArrayList;
                userArrayList = userLab.getUserList((Map<String, Object>) dataSnapshot.getValue());
                adapter = new UserAdapter(userArrayList);
                userRecyclerView.setAdapter(adapter);
                //userRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).color(Color.TRANSPARENT).marginResId(R.dimen.activity_horizontal_margin).build());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
            Fragment nextFrag= new AddUserFragment();
            CurrentActivity.getActivity().getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, nextFrag,null)
                    .addToBackStack(null)
                    .commit();
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
