package hr.air.projekt.driveit.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.StringDef;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Fragments.SingleUserFragment;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 5.12.2016..
 */

public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String EXTRA_USER = "com.stipe.android.jebemtifirebase.user";
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewEmail;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Context applicationContext;
    private User userData;
    private List<User> allusers;


    public UserHolder(View itemView, Context context, List<User> users) {
        super(itemView);
        itemView.setOnClickListener(this);
        textViewFirstName = (TextView) itemView.findViewById(R.id.list_firstName);
        textViewLastName = (TextView) itemView.findViewById(R.id.list_lastName);
        textViewEmail = (TextView) itemView.findViewById(R.id.list_email);
        buttonDelete = (Button) itemView.findViewById(R.id.list_button_deleteUser);
        buttonUpdate = (Button) itemView.findViewById(R.id.list_button_updateUser);
        this.applicationContext = context;
        this.allusers = users;
    }

    public void bindUsers(User user) {
        userData = user;
        textViewFirstName.setText(userData.getFirstName());
        textViewLastName.setText(userData.getLastName());
        textViewEmail.setText(userData.getEmail());
    }

    private void replaceFragment(Fragment newFragment) {
        FragmentManager fragmentManager = CurrentActivity.getActivity().getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container, newFragment);
        ft.commit();
    }


    @Override
    public void onClick(View view) {
        if(view == itemView||view == buttonUpdate) {
            SingleUserFragment fragment = new SingleUserFragment();
            Bundle b = new Bundle();
            b.putSerializable(EXTRA_USER, (Serializable) userData);
            fragment.setArguments(b);
            replaceFragment(fragment);
        }
        if(view ==)
    }
}
