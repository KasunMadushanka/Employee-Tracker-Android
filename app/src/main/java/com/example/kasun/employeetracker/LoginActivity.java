package com.example.kasun.employeetracker;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import java.net.URL;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


import javax.net.ssl.HttpsURLConnection;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AsyncTask<String, String, String> {



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String username=params[0];
        String password=params[1];

        String urlString ="http://192.168.1.3:80/login.php";

        HashMap<String,String> map=new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);

        performPostCall(urlString,map);

        return username;
    }

    public void  performPostCall(String requestURL,HashMap<String, String> postDataParams) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("token","d").build();

        Request request = new Request.Builder().url("http://192.168.1.3/Employee-Tracker/messaging/register.php").post(body).build();

        try {
            Response httpResponse = client.newCall(request).execute();
            System.out.println(httpResponse.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
                System.out.println(response);
            }
            else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }


    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            System.out.println(entry.getKey());
        }

        return result.toString();
    }


    @Override
    protected void onPostExecute(String result) {

        System.out.println("success");
    }

}
