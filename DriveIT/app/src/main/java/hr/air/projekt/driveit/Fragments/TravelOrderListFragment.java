package hr.air.projekt.driveit.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.TravelOrder;
import hr.air.projekt.driveit.Adapters.ServiceAdapter;
import hr.air.projekt.driveit.Adapters.TravelOrderAdapter;
import hr.air.projekt.driveit.Helper.NavigationItem;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.ServiceLab;
import hr.air.projekt.driveit.TravelOrderLab;

/**
 * Created by Stjepan on 29.1.2017..
 */

public class TravelOrderListFragment extends Fragment implements NavigationItem,View.OnClickListener{
    private static final String NAME = "Travel orders";
    private int position;
    private static final String CHILD_TRAVEL_ORDER = "travelOrders";

    private RecyclerView travelOrderRecyclerView;
    private FloatingActionButton floatingActionButtonAddTravelOrder;

    private TravelOrderLab travelOrderLab = new TravelOrderLab();
    private TravelOrderAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.travel_order_list_fragment,container,false);

        travelOrderRecyclerView = (RecyclerView) view.findViewById(R.id.travel_order_recycler_view);
        travelOrderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        floatingActionButtonAddTravelOrder = (FloatingActionButton) view.findViewById(R.id.add_travel_order);
        floatingActionButtonAddTravelOrder.setOnClickListener(this);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_TRAVEL_ORDER);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<TravelOrder> travelOrderList;
                travelOrderList = travelOrderLab.getTravelOrderList((Map<String,Object>) dataSnapshot.getValue());
                adapter = new TravelOrderAdapter(travelOrderList);
                travelOrderRecyclerView.setAdapter(adapter);
                travelOrderRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
