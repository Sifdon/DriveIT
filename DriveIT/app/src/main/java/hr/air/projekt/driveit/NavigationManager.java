package hr.air.projekt.driveit;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import java.util.ArrayList;

import hr.air.projekt.datamodule.User;
import hr.air.projekt.driveit.Helper.NavigationItem;

/**
 * Created by Stjepan on 19.12.2016..
 */

public class NavigationManager {
    private static NavigationManager instance;
    public ArrayList<NavigationItem> navigationItems;
    private NavigationView mNavigationView;
    private Activity mHandlerActivity;
    private int mItemGroupId;
    private DrawerLayout mDrawerLayout;
    private ArrayList<User> userList;




    private NavigationManager(){
        navigationItems = new ArrayList<NavigationItem>();
        userList = new ArrayList<User>();

    }

    public static NavigationManager getInstance(){
        if(instance == null)
            instance = new NavigationManager();
        return instance;
    }

    public void setDependencies(Activity handlerActivity, DrawerLayout drawerLayout, NavigationView navigationView, int itemGroupId){
        this.mHandlerActivity = handlerActivity;
        this.mNavigationView = navigationView;
        this.mDrawerLayout = drawerLayout;
        this.mItemGroupId = itemGroupId;
    }

    public void selectNavigationItem(MenuItem menuItem){
        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawer(GravityCompat.START, true);

            // uses the menu item to find the NavigationItem (interface implementator)
            NavigationItem clickedItem = navigationItems.get(menuItem.getItemId());

            displayFragment(clickedItem);
        }
    }

    private void displayFragment(NavigationItem clickedItem) {
        FragmentManager fragmentManager = mHandlerActivity.getFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, clickedItem.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void clearNavigationItems()
    {
        mNavigationView.getMenu().removeGroup(mItemGroupId);
        navigationItems.clear();
    }

    public void addItem(NavigationItem newItem){
        newItem.setPosition(navigationItems.size());
        navigationItems.add(newItem);
        mNavigationView.getMenu().add(mItemGroupId, newItem.getPosition(), newItem.getPosition() + 1, newItem.getItemName())
                .setCheckable(true);
    }

    public void showDefaultFragment(NavigationItem preferredItem) {

        displayFragment(preferredItem);
        mNavigationView.getMenu().getItem(preferredItem.getPosition()).setChecked(true);
    }

    public void showDefaultFragment()
    {
        if (navigationItems.size() > 0)
        {
            showDefaultFragment(navigationItems.get(0));
        }
    }

}
