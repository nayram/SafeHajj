package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class HealthResponse {

    ArrayList<BloodPressure> BloodPressureItems;
    ArrayList<BloodSugar> BloodSugarItems;
    ArrayList<Heart> Items;
    ArrayList<Heart> HeartItems;
    ArrayList<Sleep> SleepItems;
    HealthRatio HealthRatio;
    Sleep SleepRatio;
    int State,StepCount;

    public ArrayList<Sleep> getSleepItems() {
        return SleepItems;
    }

    public void setSleepItems(ArrayList<Sleep> sleepItems) {
        SleepItems = sleepItems;
    }

    public com.dev.safehajj.Pojo.HealthRatio getHealthRatio() {
        return HealthRatio;
    }

    public void setHealthRatio(com.dev.safehajj.Pojo.HealthRatio healthRatio) {
        HealthRatio = healthRatio;
    }

    public Sleep getSleepRatio() {
        return SleepRatio;
    }

    public void setSleepRatio(Sleep sleepRatio) {
        SleepRatio = sleepRatio;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getStepCount() {
        return StepCount;
    }

    public void setStepCount(int stepCount) {
        StepCount = stepCount;
    }

    public ArrayList<BloodPressure> getBloodPressureItems() {
        return BloodPressureItems;
    }

    public ArrayList<BloodSugar> getBloodSugarItems() {
        return BloodSugarItems;
    }

    public ArrayList<Heart> getItems() {
        return Items;
    }

    public ArrayList<Heart> getHeartItems() {
        return HeartItems;
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
