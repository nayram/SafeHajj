package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;

public class Sleep {
    String Imei,LastUpdated,StartTime,EndTime;
    int TotalSec,DeepSleepSec,Roll,Wake;

    public String getImei() {
        return Imei;
    }

    public void setImei(String imei) {
        Imei = imei;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        LastUpdated = lastUpdated;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getTotalSec() {
        return TotalSec;
    }

    public void setTotalSec(int totalSec) {
        TotalSec = totalSec;
    }

    public int getDeepSleepSec() {
        return DeepSleepSec;
    }

    public void setDeepSleepSec(int deepSleepSec) {
        DeepSleepSec = deepSleepSec;
    }

    public int getRoll() {
        return Roll;
    }

    public void setRoll(int roll) {
        Roll = roll;
    }

    public int getWake() {
        return Wake;
    }

    public void setWake(int wake) {
        Wake = wake;
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
