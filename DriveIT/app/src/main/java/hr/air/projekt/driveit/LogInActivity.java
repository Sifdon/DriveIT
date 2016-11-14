package hr.air.projekt.driveit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private FirebaseAuth.AuthStateListener AuthListener;
    private boolean logedIn;
    private static final String TAG = "DriveIT";

    @BindView(R.id.txt_password)
    EditText editTextPassword;
    @BindView(R.id.txt_email)
    EditText editTextUsername;
    @BindView(R.id.button_log_in)
    Button buttonLogIn;
    @BindView(R.id.login_progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        firebaseAuth = firebaseAuth.getInstance();

        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };
    }


    @OnClick(R.id.button_log_in)
    public void onClick() {
        final String user = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        if (checkUsernamePassword(user, password)) {
            Toast.makeText(this, R.string.empty_field_warning, Toast.LENGTH_SHORT).show();
        } else {
            signIn();

        }
    }

    private void signIn() {
        String user = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent i = new Intent(LogInActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    Log.d(TAG, "signIn: is successful! " + task.isSuccessful());
                    logedIn = true;
                    progressBar.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "FAILURE", e);
                logedIn = false;

            }
        });

    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean checkUsernamePassword(String user, String password) {
        if (!isValidEmail(user)) {
            Toast.makeText(this, R.string.wrong_email_format, Toast.LENGTH_SHORT).show();
        }
        if (user.equals("") || password.equals("")){
            return true;
        }


        else
            return false;

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(AuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (AuthListener != null) {
            firebaseAuth.removeAuthStateListener(AuthListener);
        }
        firebaseAuth.getInstance().signOut();
    }
}
