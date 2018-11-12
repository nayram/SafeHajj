package com.dev.safehajj.PilgrimComponent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.safehajj.MapComponent.MapFragment;
import com.dev.safehajj.Pojo.Device;
import com.dev.safehajj.Pojo.DeviceListResponse;
import com.dev.safehajj.Pojo.DeviceRequest;
import com.dev.safehajj.R;
import com.dev.safehajj.Utils.App;
import com.dev.safehajj.Utils.ConnectionDetector;
import com.dev.safehajj.Utils.GeneralFunctions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PilgrimFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PilgrimFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2,TAG=getClass().getName();

    RecyclerView rcView;
    private MapFragment.OnFragmentInteractionListener mListener;
    PilgrimAdapter adapter;
    ConnectionDetector connectionDetector;



    public PilgrimFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PilgrimFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PilgrimFragment newInstance() {
        PilgrimFragment fragment = new PilgrimFragment();
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
        View rootview= inflater.inflate(R.layout.fragment_pilgrim, container, false);
        rcView=(RecyclerView)rootview.findViewById(R.id.recViewDevice);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(linearLayoutManager);
        connectionDetector=new ConnectionDetector(getContext());

        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (connectionDetector.isConnectingToInternet())
        loadDevices();
        else Toast.makeText(getActivity() , "No internet connection", Toast.LENGTH_SHORT).show();
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
        if (context instanceof MapFragment.OnFragmentInteractionListener) {
            mListener = (MapFragment.OnFragmentInteractionListener) context;
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

    void loadDevices(){
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

                if (mListener!=null)
                    mListener.hideProgress();

                DeviceListResponse deviceListResponse=response.body();

                if (deviceListResponse!=null){
                    if (deviceListResponse.getItems().size()>0){
                        Log.d(TAG,"PILGRIMS "+deviceListResponse.toString());
                        adapter=new PilgrimAdapter(deviceListResponse,getContext());
                        rcView.setAdapter(adapter);
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

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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

}
