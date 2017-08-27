package hr.air.projekt.driveit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.app.FragmentManager;
import android.preference.PreferenceManager;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;
import java.util.Map;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Fragments.MalfunctionListFragment;
import hr.air.projekt.driveit.Fragments.ServiceListFragment;
import hr.air.projekt.driveit.Fragments.TravelOrderListFragment;
import hr.air.projekt.driveit.Fragments.UserListFragment;
import hr.air.projekt.driveit.Fragments.VehicleListFragment;
import hr.air.projekt.driveit.Helper.CurrentActivity;
import hr.air.projekt.driveit.Helper.CurrentFirebaseAuth;
import hr.air.projekt.driveit.Helper.CurrentUser;
import hr.air.projekt.driveit.Helper.Util;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        FragmentManager.OnBackStackChangedListener,
        SharedPreferences.OnSharedPreferenceChangeListener{
    private final static String TAG = "DriveIT";
    private static final String CHILD_USER = "users";
    private static FirebaseAuth firebaseAuth;
    private DatabaseReference userReference;

    //Navigation manager
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    FragmentManager fragmentManager;

    private TextView textViewCurrentUserName;
    private TextView textViewCurrentUserEmail;

    private Util util = new Util();
    private SharedPreferences mSharedPreferences;

    private UserLab userLab = new UserLab();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurrentActivity.setActivity(this);
        firebaseAuth = FirebaseAuth.getInstance();
        CurrentFirebaseAuth.setFirebaseAuth(firebaseAuth);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        JodaTimeAndroid.init(this);
        setCurrentUser();
        textViewCurrentUserName = (TextView) findViewById(R.id.textViewCurrentUserName);
        textViewCurrentUserEmail = (TextView) findViewById(R.id.textViewCurrentUserEmail);


        util.setLanguage(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        /*
        DATETIME PODSJETNIK
        String date = "15:45:28, 21.01.2017.";
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss, DD.MM.yyyy.");
        DateTime d = fmt.parseDateTime(date);
        System.out.println("Formateddate "+d.toString(fmt));
        System.out.println("date "+d.toString());
        */

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();
        fragmentManager.addOnBackStackChangedListener(this);

        toolbar.setOnClickListener(navigationClick);

        setUpAvailableModules();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setCurrentUser() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(CHILD_USER);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                ArrayList<User> userList = new ArrayList<User>();
                userList = userLab.getUserList((Map<String, Object>) dataSnapshot.getValue());
                CurrentUser.setUser(userLab.getUserById(uid, userList));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_app_preference:
                Intent intent = new Intent(this, AppPreferenceActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        util.setLanguage(this);
        this.recreate();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            //Handle click on static options
            case R.id.logout:
                drawer.closeDrawer(GravityCompat.START);
                Toast.makeText(this, R.string.log_out, Toast.LENGTH_LONG).show();
                logOut();
                break;

            //Handle clicks on other (dynamicaly added drawer) items
            default:
                NavigationManager.getInstance().selectNavigationItem(item);
                break;
        }

        return true;
    }

    public void logOut() {
        finish();
        firebaseAuth.signOut();
        Intent i = new Intent(this, LogInActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackStackChanged() {
        drawerToggle.setDrawerIndicatorEnabled(fragmentManager.getBackStackEntryCount() == 0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(fragmentManager.getBackStackEntryCount() > 0);
        drawerToggle.syncState();
    }

    private void setUpAvailableModules() {
        NavigationManager nm = NavigationManager.getInstance();
        nm.setDependencies(this, drawer, navigationView, R.id.dynamic_group);
        nm.clearNavigationItems();
        nm.addItem(new UserListFragment());
        nm.addItem(new VehicleListFragment());
        nm.addItem(new MalfunctionListFragment());
        nm.addItem(new ServiceListFragment());
        nm.addItem(new TravelOrderListFragment());
        nm.showDefaultFragment();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() != 0) {
            // there is something on the stack, I'm in the fragment
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                fragmentManager.popBackStack();
            }
        } else {
            // I'm on the landing page, close the drawer or exit
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    View.OnClickListener navigationClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getFragmentManager().getBackStackEntryCount() == 0) {
                drawer.openDrawer(GravityCompat.START);
            } else {
                onBackPressed();
            }
        }
    };


}
