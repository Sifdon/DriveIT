package hr.air.projekt.driveit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.air.projekt.datamodule.Malfunction;
import hr.air.projekt.datamodule.Service;
import hr.air.projekt.driveit.Fragments.ServiceListFragment;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 26.1.2017..
 */




    public class ServiceAdapter extends RecyclerView.Adapter<ServiceHolder> {
        private LayoutInflater layoutInflater;
        private List<Service> serviceList = new ArrayList<Service>();

        public ServiceAdapter(ArrayList<Service> services) {
            layoutInflater = LayoutInflater.from(CurrentActivity.getActivity());
            serviceList = services;
        }

        @Override
        public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.list_single_service, parent,false);
            return new ServiceHolder(view,serviceList,this);
        }

    @Override
    public void onBindViewHolder(ServiceHolder holder, int position) {
        Service service = serviceList.get(position);
        holder.bindService(service);
    }



        @Override
        public int getItemCount() {
            return serviceList.size();
        }
    }

