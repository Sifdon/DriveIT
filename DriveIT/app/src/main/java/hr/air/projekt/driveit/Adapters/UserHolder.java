package hr.air.projekt.driveit.Adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.R;

/**
 * Created by Stjepan on 5.12.2016..
 */

public class UserHolder extends RecyclerView.ViewHolder {
    private static final String EXTRA_USER_ID = "com.stipe.android.jebemtifirebase.user_id";
    private TextView textViewFirstName;
    private TextView textViewLastName;
    private TextView textViewEmail;
    private Context applicationContext;
    private User userData;
    private List<User> allusers;


    public UserHolder(View itemView, Context context, List<User> users) {
        super(itemView);

        textViewFirstName = (TextView) itemView.findViewById(R.id.list_firstName);
        textViewLastName = (TextView) itemView.findViewById(R.id.list_lastName);
        textViewEmail = (TextView) itemView.findViewById(R.id.list_email);
        this.applicationContext = context;
        this.allusers = users;
    }

    public void bindUsers(User user){
        userData = user;
        textViewFirstName.setText(userData.getFirstName());
        textViewLastName.setText(userData.getLastName());
        textViewEmail.setText(userData.getEmail() );
    }
}
