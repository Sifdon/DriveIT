package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;

import hr.air.projekt.driveit.Helper.NavigationItem;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class MalfunctionListFragment extends Fragment implements NavigationItem,View.OnClickListener{
    private static final String NAME = "Malfunction";
    private int position;

    private RecyclerView userRecyclerView;
    private FloatingActionButton floatingActionButtonAddMalfunction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list_fragment, container, false);
        userRecyclerView = (RecyclerView) view.findViewById(R.id.malfunction_recycler_view);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        floatingActionButtonAddMalfunction = (FloatingActionButton) view.findViewById(R.id.add_user);
        floatingActionButtonAddMalfunction.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public String getItemName() {
        return NAME;
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
