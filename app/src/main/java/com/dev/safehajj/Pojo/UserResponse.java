package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;

public class UserResponse {
    private int UserId;
    private String Username;
    String LoginName;
    String TimeZone;
    String Avatar;
    String Email;
    int DeviceCount;
    String CodeUrl;
    int UserType;
    int SubUsersCount;

    public int getUserId() {
        return UserId;
    }

    public String getUsername() {
        return Username;
    }

    public String getLoginName() {
        return LoginName;
    }

    public String getTimeZone() {
        return TimeZone;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getEmail() {
        return Email;
    }

    public int getDeviceCount() {
        return DeviceCount;
    }

    public String getCodeUrl() {
        return CodeUrl;
    }

    public int getUserType() {
        return UserType;
    }

    public int getSubUsersCount() {
        return SubUsersCount;
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
