package com.dev.safehajj.HomeComponent;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.dev.safehajj.MapComponent.MapFragment;
import com.dev.safehajj.R;

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
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragmentManager=getSupportFragmentManager();
        mapFragment=MapFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.rl_home_container,mapFragment,"mapComponent").commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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
