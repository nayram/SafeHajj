package com.dev.safehajj.DeviceComponent;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.safehajj.Pojo.BloodPressure;
import com.dev.safehajj.Pojo.BloodSugar;
import com.dev.safehajj.Pojo.Device;
import com.dev.safehajj.Pojo.HealthRequest;
import com.dev.safehajj.Pojo.HealthResponse;
import com.dev.safehajj.Pojo.Heart;
import com.dev.safehajj.Pojo.TrackingRequest;
import com.dev.safehajj.Pojo.TrackingResponse;
import com.dev.safehajj.R;
import com.dev.safehajj.Utils.App;
import com.dev.safehajj.Utils.ConnectionDetector;
import com.dev.safehajj.Utils.GeneralFunctions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDeviceDetails extends AppCompatActivity {

    public static double latitude,longitude;
    MapView map;
    public static Device device;
    Toolbar guest_tool_bar;

    TextView tvDeviceName,titleTracking,titleHealth,tvBattery,tvDistance,
                tvDiastolic,tvShrink,tvHeartBeat,tvBloodSugar,tvHealthDistance,tvCalorie,
            tvErBloodPressure,tvErBloodSugar,tvErHeartBeat,tvErDistance,tvErCalorie;

    ImageView imgTracking,imgHealth;
    boolean isTrackingExpanded=false,isHealthExpanded=false;
    private float DEFAULT_ZOOM=7;
    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 90f;
    private static final boolean HONEYCOMB_AND_ABOVE = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;

    LinearLayout llTracking,llHealth;

    ProgressBar pbTracking,pbHealth;

    String TAG=getClass().getName();

    SimpleDateFormat format;
    ConnectionDetector connectionDetector;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_device_details);
        connectionDetector=new ConnectionDetector(this);

        tvDeviceName = (TextView) findViewById(R.id.tvDeviceName);
        titleHealth = (TextView) findViewById(R.id.titleHealth);
        titleTracking = (TextView) findViewById(R.id.titleTracking);
        tvDistance=(TextView)findViewById(R.id.tvDistance);
        tvBattery=(TextView)findViewById(R.id.tvBattery);

        tvDiastolic=(TextView)findViewById(R.id.tvDiastolic);
        tvShrink=(TextView) findViewById(R.id.tvShrink);
        tvHeartBeat=(TextView)findViewById(R.id.tvHeartBeat);
        tvHealthDistance=(TextView)findViewById(R.id.tvHealthDistance);
        tvBloodSugar=(TextView)findViewById(R.id.tvBloodSugar);
        tvCalorie=(TextView) findViewById(R.id.tvCalorie);

        tvErBloodPressure=(TextView) findViewById(R.id.tvErBloodPressure);
        tvErBloodSugar=(TextView) findViewById(R.id.tvErBloodSugar);
        tvErCalorie=(TextView) findViewById(R.id.tvErCalorie);
        tvErDistance=(TextView) findViewById(R.id.tvErDistance);
        tvErHeartBeat=(TextView) findViewById(R.id.tvErHeartBeat);

        imgTracking = (ImageView) findViewById(R.id.imgTracking);
        imgHealth = (ImageView) findViewById(R.id.imgHealth);

        llHealth=(LinearLayout) findViewById(R.id.llHealthView);
        llTracking=(LinearLayout) findViewById(R.id.llTrackingView);

        pbHealth=(ProgressBar) findViewById(R.id.pbHealth);
        pbTracking=(ProgressBar) findViewById(R.id.pbTracking);

        tvDeviceName.setText(device.getName());
        map = (MapView) findViewById(R.id.device_detail_map_view);

        mapViewListItemViewOnCreate(savedInstanceState);


        if (map != null){
            mapViewResume();
            map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(device.getLatitude(), device.getLongitude()));
                markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.currentlocation_icon));

                googleMap.addMarker(markerOption);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(device.getLatitude(), device.getLongitude()),DEFAULT_ZOOM));

            }
            });
        }

        imgTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTrackingExpanded=!isTrackingExpanded;
                toggleTrackingView(isTrackingExpanded);
            }
        });

        imgHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isHealthExpanded=!isHealthExpanded;
                toggleHealthView(isHealthExpanded);
            }
        });

        titleTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTrackingExpanded=!isTrackingExpanded;
                toggleTrackingView(isTrackingExpanded);
            }
        });

        titleHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isHealthExpanded=!isHealthExpanded;
                toggleHealthView(isHealthExpanded);
            }
        });

    }

    private void toggleHealthView(boolean isHealthExpanded) {


                RotateAnimation rotateAnimation;
                if (isHealthExpanded) { // rotate clockwise
                    rotateAnimation = new RotateAnimation(INITIAL_POSITION,
                            ROTATED_POSITION,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                } else { // rotate counterclockwise
                    rotateAnimation = new RotateAnimation( ROTATED_POSITION,
                            INITIAL_POSITION,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }

                rotateAnimation.setDuration(100);
                rotateAnimation.setFillAfter(true);

                if (imgHealth !=null)
                    imgHealth.startAnimation(rotateAnimation);

                if (isHealthExpanded){
                    if (connectionDetector.isConnectingToInternet())
                    loadHealthData();
                    else Toast.makeText(this, "No internet connection!", Toast.LENGTH_SHORT).show();
                }else{
                    llHealth.setVisibility(View.GONE);
                }


    }

    private void toggleTrackingView(boolean isTrackingExpanded) {


                RotateAnimation rotateAnimation;
                if (isTrackingExpanded) { // rotate clockwise
                    rotateAnimation = new RotateAnimation(INITIAL_POSITION,
                            ROTATED_POSITION,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                } else { // rotate counterclockwise
                    rotateAnimation = new RotateAnimation( ROTATED_POSITION,
                            INITIAL_POSITION,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }

                rotateAnimation.setDuration(100);
                rotateAnimation.setFillAfter(true);

                if (imgTracking !=null)
                    imgTracking.startAnimation(rotateAnimation);

                if (isTrackingExpanded){
                    loadTrackingData();
                }else{
                    pbTracking.setVisibility(View.GONE);
                    llTracking.setVisibility(View.GONE);
                }


    }

    void loadTrackingData(){
        final TrackingRequest request=new TrackingRequest();
        request.setAppId("6");
        request.setDeviceId(device.getId());
        request.setLanguage("en-us");
        request.setMapType("google");
        request.setToken(GeneralFunctions.getUserToken());

        pbTracking.setVisibility(View.VISIBLE);
        Log.d(TAG,String.valueOf(device.getId()));
        Log.d(TAG,GeneralFunctions.getUserToken());

        App.hajjNetworkInterface.getDeviceTracking(request).enqueue(new Callback<TrackingResponse>() {
            @Override
            public void onResponse(Call<TrackingResponse> call, Response<TrackingResponse> response) {

                try {

                    Log.d(TAG,String.valueOf(response));
                    if (response.body() !=null){
                        Device dev=response.body().getItem();

                        if (dev!=null){
                            tvBattery.setText(String.valueOf(dev.getBattery())+"%");
                            tvDistance.setText(String.valueOf(dev.getDistance()));
                            llTracking.setVisibility(View.VISIBLE);
                        }else{
                            isTrackingExpanded=!isTrackingExpanded;
                            toggleTrackingView(isTrackingExpanded);
                            llTracking.setVisibility(View.GONE);
                        }
                    }else{
                        isTrackingExpanded=!isTrackingExpanded;
                        toggleTrackingView(isTrackingExpanded);
                        llTracking.setVisibility(View.GONE);
                        Toast.makeText(ActivityDeviceDetails.this, "Falied to load tracking data", Toast.LENGTH_SHORT).show();
                    }

                    pbTracking.setVisibility(View.GONE);
                }catch (Exception ex){
                    ex.printStackTrace();
                    isTrackingExpanded=!isTrackingExpanded;
                    toggleTrackingView(isTrackingExpanded);
                    llTracking.setVisibility(View.GONE);
                }



            }

            @Override
            public void onFailure(Call<TrackingResponse> call, Throwable t) {
                pbTracking.setVisibility(View.GONE);
                llTracking.setVisibility(View.GONE);
                isTrackingExpanded=!isTrackingExpanded;
                toggleTrackingView(isTrackingExpanded);
                t.printStackTrace();
                Toast.makeText(ActivityDeviceDetails.this, "Failed to load tracking data", Toast.LENGTH_SHORT).show();
            }
        });
    }


    void loadHealthData(){
        HealthRequest request=new HealthRequest();
        request.setAppId("6");
        request.setDeviceId(device.getId());


        try{
            format= new SimpleDateFormat("yyyy-MM-ddzH:m:sZ");



            request.setEndTime(format.parse("2018-05-17T11:04:00+08:00"));
            request.setStartTime(format.parse("2017-05-17T11:04:00+08:00"));
        }catch (Exception ex){
            ex.printStackTrace();
        }
        request.setLanguage("en-us");
        request.setTimeOffset(4.1);
        request.setToken(GeneralFunctions.getUserToken());

        pbHealth.setVisibility(View.VISIBLE);

        App.hajjNetworkInterface.getHealthData(request).enqueue(new Callback<HealthResponse>() {
            @Override
            public void onResponse(Call<HealthResponse> call, Response<HealthResponse> response) {
                Log.d(TAG,"HEALTH "+String.valueOf(response));
                if (response.body() !=null){

                    HealthResponse res=response.body();
                    Log.d(TAG,response.body().toString());
                    llHealth.setVisibility(View.VISIBLE);

                    if (res.getBloodPressureItems().size()>0){
                        BloodPressure bloodPressure=res.getBloodPressureItems().get(0);
                        tvDiastolic.setText(String.valueOf(bloodPressure.getDiastolic()));
                        tvShrink.setText(String.valueOf(bloodPressure.getShrink()));
                    }else{
                        tvErBloodPressure.setVisibility(View.VISIBLE);
                    }

                    if (res.getBloodSugarItems().size()>0){
                        BloodSugar bloodSugar= res.getBloodSugarItems().get(0);
                        tvBloodSugar.setText(String.valueOf(bloodSugar.getBloodSugar()));
                    }else{
                        tvErBloodSugar.setVisibility(View.VISIBLE);
                    }

                    if (res.getHeartItems().size()>0){
                        Heart heart=res.getHeartItems().get(0);
                        tvHeartBeat.setText(String.valueOf(heart.getHeartBeat()));
                        tvHealthDistance.setText(String.valueOf(heart.getDistance()));
                        tvCalorie.setText(String.valueOf(heart.getCalorie()));
                    }else{
                        tvErDistance.setVisibility(View.VISIBLE);
                        tvErCalorie.setVisibility(View.VISIBLE);
                        tvErHeartBeat.setVisibility(View.VISIBLE);
                    }

                    pbHealth.setVisibility(View.GONE);

                }else{
                    isHealthExpanded=!isHealthExpanded;
                    toggleHealthView(isHealthExpanded);
                    llHealth.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<HealthResponse> call, Throwable t) {
                t.printStackTrace();
                pbHealth.setVisibility(View.GONE);
                isHealthExpanded=!isHealthExpanded;
                toggleHealthView(isHealthExpanded);
                llHealth.setVisibility(View.GONE);
                Toast.makeText(ActivityDeviceDetails.this, "Failed to load health data", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void mapViewListItemViewOnCreate(Bundle savedInstanceState) {
        if (map !=null){
            map.onCreate(savedInstanceState);
        }
    }

    private void mapViewResume(){
        if (map!=null){
            map.onResume();
        }
    }
}
