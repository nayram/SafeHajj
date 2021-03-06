package com.dev.safehajj.HomeComponent;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.safehajj.AccountComponent.AccountFragment;
import com.dev.safehajj.MapComponent.MapFragment;
import com.dev.safehajj.PilgrimComponent.PilgrimFragment;
import com.dev.safehajj.R;
import com.dev.safehajj.Utils.GeneralFunctions;

import java.lang.reflect.Field;

public class HomeActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener{


    private FragmentManager fragmentManager;
    private MapFragment mapFragment;
    private PilgrimFragment pilgrimFragment;
    private AccountFragment accountFragment;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    String TAG=getClass().getName();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment("mapComponent");
                    return true;
                case R.id.navigation_pilgrims:
                    setFragment("pilgrimComponent");
                    return true;
                case R.id.navigation_account:
                    setFragment("accountComponent");

                    return true;
                case R.id.navigation_logout:
                        logout();
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        progressBar=(ProgressBar)findViewById(R.id.home_progress);

        setSupportActionBar(toolbar);

        setTitle("Home");

        fragmentManager=getSupportFragmentManager();

        mapFragment=MapFragment.newInstance();

        pilgrimFragment=PilgrimFragment.newInstance();
        accountFragment=AccountFragment.newInstance();

        fragmentManager.beginTransaction().add(R.id.rl_home_container,mapFragment,"mapComponent").commit();
        Log.d(TAG,"User "+ String.valueOf(GeneralFunctions.getUser()));


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);

                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }

    }



    void setFragment(String component){
        switch (component){
            case "pilgrimComponent":
                fragmentManager.beginTransaction().replace(R.id.rl_home_container,pilgrimFragment,component).commit();
                setTitle("Pilgrims");
                break;
            case "accountComponent":
                fragmentManager.beginTransaction().replace(R.id.rl_home_container,accountFragment,component).commit();
                setTitle("Account Profile");
                break;

            default:
                fragmentManager.beginTransaction().replace(R.id.rl_home_container,mapFragment,component).commit();
                break;

        }

    }

    void logout(){
        new AlertDialog.Builder(this)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GeneralFunctions.loogout();
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
