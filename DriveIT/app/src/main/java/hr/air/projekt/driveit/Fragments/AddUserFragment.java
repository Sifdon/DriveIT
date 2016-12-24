package hr.air.projekt.driveit.Fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v4.util.CircularArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.CurrentFirebaseAuth;
import hr.air.projekt.driveit.R;
import hr.air.projekt.driveit.UserLab;

import static android.widget.Toast.*;

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
                            u.setUID(user.getUid());
                            Toast.makeText(CurrentActivity.getActivity(), "User created",
                                    Toast.LENGTH_SHORT).show();
                            userLab.addUser(u, p);
                        }
                    }

                });
            CurrentActivity.getActivity().getFragmentManager().popBackStack();
            }
            System.out.println("sc" + isValidEmail());
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
