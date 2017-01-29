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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.driveit.Adapters.MalfunctionAdapter;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.NavigationItem;
import hr.air.projekt.driveit.MalfunctionLab;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class MalfunctionListFragment extends Fragment implements NavigationItem,View.OnClickListener{


    private static final String NAME="Malfunctions";
    private  final String CHILD_MALFUNCTION = "malfunction";
    private int position;
    private RecyclerView malfunctionRecyclerView;
    private FloatingActionButton floatingActionButtonAddMalfunction;
    private MalfunctionLab malfunctionLab = new MalfunctionLab();
    private MalfunctionAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.malfunction_list_fragment, container, false);

        malfunctionRecyclerView = (RecyclerView) view.findViewById(R.id.malfunction_recycler_view);
        malfunctionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        floatingActionButtonAddMalfunction = (FloatingActionButton) view.findViewById(R.id.add_malfunction);
        floatingActionButtonAddMalfunction.setOnClickListener(this);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_MALFUNCTION);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Malfunction> malfunctionList;
                malfunctionList = malfunctionLab.getMalfunctionsList((Map<String, Object>) dataSnapshot.getValue());
                adapter = new MalfunctionAdapter(malfunctionList);
                malfunctionRecyclerView.setAdapter(adapter);
                malfunctionRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).color(Color.TRANSPARENT).marginResId(R.dimen.activity_horizontal_margin).build());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment nextFrag= new AddMalfunctionFragment();
        CurrentActivity.getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, nextFrag,null)
                .addToBackStack(null)
                .commit();
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
