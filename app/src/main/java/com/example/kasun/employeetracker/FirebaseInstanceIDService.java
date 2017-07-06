package com.example.kasun.employeetracker;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Kasun on 7/6/2017.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        registerToken(token);
    }

    private void registerToken(String token) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("token",token).build();

        Request request = new Request.Builder().url("http://192.168.1.3/Employee-Tracker/messaging/register.php").post(body).build();

        try {
            Response httpResponse = client.newCall(request).execute();
            System.out.println(httpResponse.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
