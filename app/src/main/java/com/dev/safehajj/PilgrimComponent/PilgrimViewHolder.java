package com.dev.safehajj.PilgrimComponent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.safehajj.R;

import org.w3c.dom.Text;

public class PilgrimViewHolder extends RecyclerView.ViewHolder {

    ImageView pilgrimImg;
    TextView tvPilgrimName;
    TextView tvPilgrimSerial;
    public PilgrimViewHolder(View itemView) {
        super(itemView);
        tvPilgrimName=(TextView)itemView.findViewById(R.id.tvPilgrimName);
        pilgrimImg=(ImageView)itemView.findViewById(R.id.imageView);
        tvPilgrimSerial=(TextView)itemView.findViewById(R.id.tvPilgrimSerial);
    }


}
