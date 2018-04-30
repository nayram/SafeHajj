package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;

public class Device {
    int Id,GroupId,Status,Acc,Course,IsShowAcc,TypeValue,IsCarDevice,Model,ShowSpeed,IsStop,ShowDataType,DataType,
            ShowBattery,Battery,DeviceId;
    boolean NeedPhone;
    String SerialNumber,Name,CarNo,Icon,Type,Logo,Sim,DeviceUtcDate,LastCommunication;
    double Latitude,Longitude,OLat,OLng,Speed,Distance;


    public int getId() {
        return Id;
    }

    public int getGroupId() {
        return GroupId;
    }

    public int getDeviceId() {
        return DeviceId;
    }

    public boolean isNeedPhone() {
        return NeedPhone;
    }

    public int getStatus() {
        return Status;

    }

    public int getShowSpeed() {
        return ShowSpeed;
    }

    public int getIsStop() {
        return IsStop;
    }

    public int getShowDataType() {
        return ShowDataType;
    }

    public int getDataType() {
        return DataType;
    }

    public int getShowBattery() {
        return ShowBattery;
    }

    public int getBattery() {
        return Battery;
    }

    public String getDeviceUtcDate() {
        return DeviceUtcDate;
    }

    public String getLastCommunication() {
        return LastCommunication;
    }

    public double getOLat() {
        return OLat;
    }

    public double getOLng() {
        return OLng;
    }

    public double getSpeed() {
        return Speed;
    }

    public double getDistance() {
        return Distance;
    }

    public int getAcc() {
        return Acc;
    }

    public int getCourse() {
        return Course;
    }

    public int getIsShowAcc() {
        return IsShowAcc;
    }

    public int getTypeValue() {
        return TypeValue;
    }

    public int getIsCarDevice() {
        return IsCarDevice;
    }

    public int getModel() {
        return Model;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public String getName() {
        return Name;
    }

    public String getCarNo() {
        return CarNo;
    }

    public String getIcon() {
        return Icon;
    }

    public String getType() {
        return Type;
    }

    public String getLogo() {
        return Logo;
    }

    public String getSim() {
        return Sim;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = "";

        result.append(((Object) this).getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = ((Object) this).getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append(" ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }

        result.append("}");

        return result.toString();
    }
}
