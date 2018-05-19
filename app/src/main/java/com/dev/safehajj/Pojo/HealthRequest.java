package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;
import java.util.Date;

public class HealthRequest {
    String Token,Language,AppId;
    int DeviceId;
    Date StartTime,EndTime;

    double TimeOffset;

    public double getTimeOffset() {
        return TimeOffset;
    }

    public void setTimeOffset(double timeOffset) {
        TimeOffset = timeOffset;
    }



    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public int getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(int deviceId) {
        DeviceId = deviceId;
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
