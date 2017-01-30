package hr.air.projekt.driveit.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.CurrentFirebaseAuth;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;

/**
 * Created by Stjepan on 23.12.2016..
 */

public class AddUserFragment extends Fragment implements View.OnClickListener {


    private Button buttonAddUser;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextRepeatPassword;
    private CheckBox checkBoxEmployee;
    private CheckBox checkBoxMechanic;
    private CheckBox checkBoxAdmin;
    private UserLab userLab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_user_fragment, container, false);
        editTextFirstName = (EditText) view.findViewById(R.id.add_user_firstName);
        editTextLastName = (EditText) view.findViewById(R.id.add_user_lastName);
        editTextEmail = (EditText) view.findViewById(R.id.add_user_email);
        editTextPassword = (EditText) view.findViewById(R.id.add_user_password);
        editTextRepeatPassword = (EditText) view.findViewById(R.id.add_user_repeatPassword);
        buttonAddUser = (Button) view.findViewById(R.id.add_user_button_addUser);
        buttonAddUser.setOnClickListener(this);
        checkBoxAdmin = (CheckBox) view.findViewById(R.id.add_user_checkBoxRoleAdministrator);
        checkBoxEmployee = (CheckBox) view.findViewById(R.id.add_user_checkBoxRoleEmployee);
        checkBoxMechanic = (CheckBox) view.findViewById(R.id.add_user_checkBoxRoleMechanic);
        userLab = new UserLab();
        return view;
    }


    @Override
    public void onClick(View view) {
        if (view == buttonAddUser) {
            if (checkPasswordMatch() == false) {
                Toast.makeText(CurrentActivity.getActivity(), R.string.passwords_dont_match, Toast.LENGTH_LONG).show();
            }
            if (isValidEmail() == false)
                Toast.makeText(CurrentActivity.getActivity(), R.string.wrong_email_format, Toast.LENGTH_LONG).show();
            else {
                final User u = new User();
                final String p = editTextPassword.getText().toString();
                u.setFirstName(editTextFirstName.getText().toString());
                u.setLastName(editTextLastName.getText().toString());
                u.setEmail(editTextEmail.getText().toString());
                CurrentFirebaseAuth.getFirebaseAuth().createUserWithEmailAndPassword(u.getEmail(), p).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = task.getResult().getUser();
                                    u.setUid(user.getUid());
                                    Toast.makeText(CurrentActivity.getActivity(), R.string.user_added,
                                            Toast.LENGTH_SHORT).show();
                                    userLab.addUser(u);
                                    userLab.setRoles(user.getUid(),checkBoxMechanic.isChecked(),checkBoxAdmin.isChecked(),checkBoxEmployee.isChecked());
                                }
                            }
                        });
                CurrentActivity.getActivity().getFragmentManager().popBackStack();
            }
        }
    }
    
    private boolean checkPasswordMatch() {
        String p1 = editTextPassword.getText().toString();
        String p2 = editTextRepeatPassword.getText().toString();
        if (p1.equals(p2)) {
            return true;
        } else
            return false;
    }

    private boolean isValidEmail() {
        String email = editTextEmail.getText().toString();
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
