package com.dev.safehajj.Pojo;

import java.lang.reflect.Field;

public class LoginResponse {

    int LoginType;
    String AccessToken;
    UserResponse Item;
    ThirdPartyResponse ThirdParty;
    int State;


    public int getLoginType() {
        return LoginType;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public UserResponse getItem() {
        return Item;
    }

    public ThirdPartyResponse getThirdParty() {
        return ThirdParty;
    }

    public int getState() {
        return State;
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
