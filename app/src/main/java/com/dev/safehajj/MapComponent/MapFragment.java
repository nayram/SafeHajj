package com.dev.safehajj.MapComponent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.safehajj.Pojo.Device;
import com.dev.safehajj.Pojo.DeviceListResponse;
import com.dev.safehajj.Pojo.DeviceRequest;
import com.dev.safehajj.R;
import com.dev.safehajj.Utils.App;
import com.dev.safehajj.Utils.GeneralFunctions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleMap mMap;
    private String TAG=getClass().getName();

    private OnFragmentInteractionListener mListener;
    private float DEFAULT_ZOOM=7;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();

        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng mecca = new LatLng(21.422510, 39.826168);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mecca,DEFAULT_ZOOM));
        loadDevices();
    }


    void loadDevices(){
        Log.d(TAG,"Map Load Devices");

        if (mListener!=null)
        mListener.showProgress();

        DeviceRequest deviceRequest=new DeviceRequest();
        deviceRequest.setAppId("6");
        deviceRequest.setToken(GeneralFunctions.getUserToken());
        deviceRequest.setId(6831);
        deviceRequest.setLanguage("en-us");
        deviceRequest.setPageCount(100);
        deviceRequest.setPageNo(1);
        deviceRequest.setType(0);
        deviceRequest.setMapType("google");
        App.hajjNetworkInterface.getDevices(deviceRequest).enqueue(new Callback<DeviceListResponse>() {
            @Override
            public void onResponse(Call<DeviceListResponse> call, Response<DeviceListResponse> response) {
                Log.d(TAG,"Map Response");
                if (mListener!=null)
                    mListener.hideProgress();

                DeviceListResponse deviceListResponse=response.body();
                Log.d(TAG,"MapFragment:\n"+response.body().toString());
                if (deviceListResponse!=null){
                    if (deviceListResponse.getItems().size()>0){
                        for (Device item:deviceListResponse.getItems()){
                                addItemOnMap(item);
                        }
                    }else{
                        showToast("No devices found");
                    }
                }else{
                    showToast("No devices found");
                }
            }

            @Override
            public void onFailure(Call<DeviceListResponse> call, Throwable t) {

            }
        });
    }

    void addItemOnMap(Device device){
        Log.d(TAG,"Map add device to map");
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bmp = Bitmap.createBitmap(80, 80, conf);
        Canvas canvas1 = new Canvas(bmp);

        Paint color = new Paint();
        color.setTextSize(35);
        color.setColor(Color.BLACK);

        canvas1.drawBitmap(drawableToBitmap(getResources().getDrawable(R.mipmap.ic_launcher)), 0,0, color);
        canvas1.drawText(device.getName(), 30, 40, color);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(device.getLatitude(),device.getLongitude()))
                .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                .anchor(0.5f, 1));

    }

    public static Bitmap drawableToBitmap (Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);


        return bitmap;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void showProgress();
        void hideProgress();
    }

    void showToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
