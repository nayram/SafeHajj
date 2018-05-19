package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;

public class Heart {
    String IMEI,LastUpdate;
    int Steps,HeartBeat,Diastolic,Shrink,BloodSugar,Distance,Calorie;

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

    public String getIMEI() {
        return IMEI;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public int getSteps() {
        return Steps;
    }

    public int getHeartBeat() {
        return HeartBeat;
    }

    public int getDiastolic() {
        return Diastolic;
    }

    public int getShrink() {
        return Shrink;
    }

    public int getBloodSugar() {
        return BloodSugar;
    }

    public int getDistance() {
        return Distance;
    }

    public int getCalorie() {
        return Calorie;
    }
}
