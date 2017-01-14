package hr.air.projekt.driveit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.datamodule.User;

/**
 * Created by Stjepan on 14.1.2017..
 */

public class MalfunctionAdapter extends RecyclerView.Adapter<MalfunctionHolder> {
    LayoutInflater layoutInflater;
    List<Malfunction> malfunctionList = new ArrayList<Malfunction>();
    Context applicationContext;

    public MalfunctionAdapter(Context context, ArrayList<Malfunction> malfunctions) {
        applicationContext = context;
        layoutInflater = LayoutInflater.from(context);
        this.malfunctionList = malfunctions;
    }

    @Override
    public MalfunctionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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
