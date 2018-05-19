package com.dev.safehajj.Utils;

import com.dev.safehajj.Pojo.Device;
import com.dev.safehajj.Pojo.DeviceListResponse;
import com.dev.safehajj.Pojo.DeviceRequest;
import com.dev.safehajj.Pojo.HealthRequest;
import com.dev.safehajj.Pojo.HealthResponse;
import com.dev.safehajj.Pojo.LoginResponse;
import com.dev.safehajj.Pojo.TrackingRequest;
import com.dev.safehajj.Pojo.TrackingResponse;
import com.dev.safehajj.Pojo.UserRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SafeHajjNetworkInterface {
    public static final String domain="http://api.amber360.com/api/";

    @POST("User/Login")
    Call<LoginResponse> login(@Body UserRequest request);

    @POST("Device/ListDevice")
    Call<DeviceListResponse> getDevices(@Body DeviceRequest request);

    @POST("Location/Tracking")
    Call<TrackingResponse> getDeviceTracking(@Body TrackingRequest trackingRequest);

    @POST("Device/CheckDevice")
    Call<Device> checkDevice(@Body DeviceRequest deviceRequest);

    @POST("Health/GetHealth")
    Call<HealthResponse> getHealthData(@Body HealthRequest healthRequest);

}
