package hr.air.projekt.driveit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class MalfunctionAdapter extends RecyclerView.Adapter<MalfunctionHolder> {
    private LayoutInflater layoutInflater;
    private List<Malfunction> malfunctionList = new ArrayList<Malfunction>();

    public MalfunctionAdapter(ArrayList<Malfunction> malfunctions) {
        layoutInflater = LayoutInflater.from(CurrentActivity.getActivity());
        malfunctionList = malfunctions;
    }

    @Override
    public MalfunctionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_single_malfunction, parent,false);
        return new MalfunctionHolder(view,malfunctionList,this);
    }

    @Override
    public void onBindViewHolder(MalfunctionHolder holder, int position) {
        Malfunction malfunction = malfunctionList.get(position);
        holder.bindMalfunction(malfunction);
    }

    @Override
    public int getItemCount() {
        return malfunctionList.size();
    }
}
