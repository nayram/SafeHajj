package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;

public class ThirdPartyResponse {

    int UserID;
    String ThirdImg;
    String ThirdName;
    int ThirdType;
    String ThirdID;

    public int getUserID() {
        return UserID;
    }

    public String getThirdImg() {
        return ThirdImg;
    }

    public String getThirdName() {
        return ThirdName;
    }

    public int getThirdType() {
        return ThirdType;
    }

    public String getThirdID() {
        return ThirdID;
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
