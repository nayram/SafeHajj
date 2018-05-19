package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;

public class HealthRatio {
    int Total,Normal,Low,High;
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

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getNormal() {
        return Normal;
    }

    public void setNormal(int normal) {
        Normal = normal;
    }

    public int getLow() {
        return Low;
    }

    public void setLow(int low) {
        Low = low;
    }

    public int getHigh() {
        return High;
    }

    public void setHigh(int high) {
        High = high;
    }
}
