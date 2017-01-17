package hr.air.projekt.driveit.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 5.12.2016..
 */

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {
    LayoutInflater layoutInflater;
    List<User> userList = new ArrayList<User>();


    public UserAdapter( ArrayList<User> users) {
        layoutInflater = LayoutInflater.from(CurrentActivity.getActivity());
        this.userList = users;
    }


    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_single_user, parent,false);
        return new UserHolder(view,userList,this);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User user = userList.get(position);
        holder.bindUsers(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
