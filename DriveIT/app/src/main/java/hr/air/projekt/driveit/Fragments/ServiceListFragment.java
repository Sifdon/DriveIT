package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
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
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.Service;
import hr.air.projekt.driveit.Adapters.ServiceAdapter;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.NavigationItem;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.ServiceLab;

/**
 * Created by Stjepan on 26.1.2017..
 */

public class ServiceListFragment extends Fragment implements NavigationItem, View.OnClickListener {
    private static final String NAME = "Services";
    private int position;
    private static final String CHILD_SERVICE = "services";


    private RecyclerView serviceRecyclerView;
    private FloatingActionButton floatingActionButtonAddService;

    private ServiceLab serviceLab = new ServiceLab();
    private ServiceAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_list_fragment, container, false);

        serviceRecyclerView = (RecyclerView) view.findViewById(R.id.service_recycler_view);
        serviceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        floatingActionButtonAddService = (FloatingActionButton) view.findViewById(R.id.add_service);
        floatingActionButtonAddService.setOnClickListener(this);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_SERVICE);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Service> serviceList;
                serviceList = serviceLab.getServiceList((Map<String, Object>) dataSnapshot.getValue());
                adapter = new ServiceAdapter(serviceList);
                serviceRecyclerView.setAdapter(adapter);
                serviceRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==floatingActionButtonAddService){
            Fragment nextFrag = new AddServiceFragment();
            CurrentActivity.getActivity().getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, nextFrag,null)
                    .addToBackStack(null)
                    .commit();
        }
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
