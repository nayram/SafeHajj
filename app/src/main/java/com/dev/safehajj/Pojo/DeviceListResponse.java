package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DeviceListResponse {
    int Page,Total,State;
    ArrayList<Device> Items;

    public int getPage() {
        return Page;
    }

    public int getTotal() {
        return Total;
    }

    public int getState() {
        return State;
    }

    public ArrayList<Device> getItems() {
        return Items;
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
