package hr.air.projekt.driveit.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;

/**
 * Created by Stjepan on 5.12.2016..
 */

public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private static final String EXTRA_USER = "com.stipe.android.jebemtifirebase.user";
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Context applicationContext;
    private User userData;
    private List<User> allusers;
    private UserLab userLab = new UserLab();
    private UserAdapter adapter;


    public UserHolder(View itemView, Context context, List<User> users, UserAdapter adapter) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        editTextFirstName = (EditText) itemView.findViewById(R.id.list_firstName);
        editTextLastName = (EditText) itemView.findViewById(R.id.list_lastName);
        editTextEmail = (EditText) itemView.findViewById(R.id.list_email);
        buttonDelete = (Button) itemView.findViewById(R.id.list_button_deleteUser);
        buttonUpdate = (Button) itemView.findViewById(R.id.list_button_updateUser);
        buttonUpdate.setVisibility(View.GONE);
        editTextFirstName.setEnabled(false);
        editTextLastName.setEnabled(false);
        editTextEmail.setEnabled(false);
        buttonDelete.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        this.applicationContext = context;
        this.allusers = users;
        this.adapter = adapter;

    }

    public void bindUsers(User user) {
        userData = user;
        editTextFirstName.setText(userData.getFirstName());
        editTextLastName.setText(userData.getLastName());
        editTextEmail.setText(userData.getEmail());
    }


    @Override
    public void onClick(View view) {
        if (view == buttonUpdate) {
            User user = userData;
            user.setFirstName(editTextFirstName.getText().toString());
            user.setLastName(editTextLastName.getText().toString());
            user.setEmail(editTextEmail.getText().toString());
            userLab.updateUser(user);
        }
        if (view == buttonDelete) {
            final AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).create();
            final int parentPosition = getAdapterPosition();
            final User currentUser = userData;
            final List<User> currentUsers = allusers;
            alertDialog.setTitle(itemView.getContext().getString(R.string.removal_question_user));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, itemView.getContext().getString(R.string.delete),
                    new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    userLab.deleteUser(userData);
                    allusers.remove(getAdapterPosition());
                    adapter.notifyItemRemoved(getAdapterPosition());
                    adapter.notifyDataSetChanged();
                    adapter.notifyItemRangeChanged(getAdapterPosition(),allusers.size());
                    alertDialog.dismiss();
                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, itemView.getContext().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (view == itemView) {
            buttonUpdate.setVisibility(View.VISIBLE);
            editTextFirstName.setClickable(true);
            editTextLastName.setClickable(true);
            editTextEmail.setClickable(true);
            editTextFirstName.setEnabled(true);
            editTextLastName.setEnabled(true);
            editTextEmail.setEnabled(true);
        }
        return true;
    }
}
