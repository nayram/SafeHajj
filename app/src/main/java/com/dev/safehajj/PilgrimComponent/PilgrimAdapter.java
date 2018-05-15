package com.dev.safehajj.PilgrimComponent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.safehajj.Pojo.Device;
import com.dev.safehajj.Pojo.DeviceListResponse;
import com.dev.safehajj.R;

public class PilgrimAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    DeviceListResponse deviceListResponse;
    Context context;
    String TAG=getClass().getName();

    public PilgrimAdapter(DeviceListResponse deviceListResponse, Context context) {
        this.deviceListResponse = deviceListResponse;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pilgrim_layout,parent,false);
        PilgrimViewHolder viewHolder=new PilgrimViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PilgrimViewHolder){
            Device device= deviceListResponse.getItems().get(position);
            ((PilgrimViewHolder) holder).tvPilgrimSerial.setText(device.getSerialNumber());
            ((PilgrimViewHolder) holder).tvPilgrimName.setText(device.getName());
        }

    }

    @Override
    public int getItemCount() {
        return deviceListResponse.getItems().size();
    }
}
