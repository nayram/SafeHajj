package com.dev.safehajj.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.dev.safehajj.HomeComponent.HomeActivity;
import com.dev.safehajj.Pojo.LoginResponse;
import com.dev.safehajj.Pojo.UserRequest;
import com.dev.safehajj.Pojo.UserResponse;
import com.dev.safehajj.R;
import com.dev.safehajj.Utils.App;
import com.dev.safehajj.Utils.GeneralFunctions;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button loginBtn;
    EditText etEmail,etPassword;
    ProgressBar login_progress;
    String TAG=getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn=(Button)findViewById(R.id.bt_go);
        etEmail=(EditText)findViewById(R.id.et_email);
        etPassword=(EditText)findViewById(R.id.et_password);
        login_progress=(ProgressBar)findViewById(R.id.login_progress);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=etEmail.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                if (email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                }else {
                  login();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (GeneralFunctions.getLoginFlag()){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }
    }

    private void login() {

        UserRequest request=new UserRequest(etEmail.getText().toString(),
                    etPassword.getText().toString(),"6","en-us",0);
        loginBtn.setEnabled(false);

        login_progress.setVisibility(View.VISIBLE);

        App.hajjNetworkInterface.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                login_progress.setVisibility(View.GONE);
                loginBtn.setEnabled(true);
                Log.d(TAG,response.body().toString());
                if (response.body().getAccessToken()!=null){
                    if (!response.body().getAccessToken().isEmpty()){
                        GeneralFunctions.setLoginFlag(true);
                        LoginResponse user=response.body();
                        if (user!=null){
                            GeneralFunctions.saveUserToken(user.getAccessToken());

                            GeneralFunctions.setUser(new Gson().toJson(user));
                            GeneralFunctions.setLoginFlag(true);
                            startActivity(new Intent(MainActivity.this,HomeActivity.class));
                        }
                    }else{
                        showToast("Login Failed. Try again!");
                    }
                }else {
                    showToast("Login Failed. Try again!");
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                login_progress.setVisibility(View.GONE);
                loginBtn.setEnabled(true);
                t.printStackTrace();
                showToast("Login Failed. Try again!");
            }
        });
    }


    void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
