package com.dev.safehajj.HomeComponent;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.dev.safehajj.MapComponent.MapFragment;
import com.dev.safehajj.R;

import java.lang.reflect.Field;

public class HomeActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener{


    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setFragment("mapComponent");
                    return true;
                case R.id.navigation_pilgrims:

                    return true;
                case R.id.navigation_account:

                    return true;
                case R.id.navigation_settings:

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

        fragmentManager=getSupportFragmentManager();
        mapFragment=MapFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.rl_home_container,mapFragment,"mapComponent").commit();


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

            default:
                fragmentManager.beginTransaction().replace(R.id.rl_home_container,mapFragment,component).commit();
                break;

        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
