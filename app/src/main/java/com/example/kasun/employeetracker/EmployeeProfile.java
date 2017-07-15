package com.example.kasun.employeetracker;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EmployeeProfile extends AppCompatActivity{

    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12,textView13,textView14,textView15,textView16,
            textView17,textView18,textView19,textView20,textView21,textView22,textView23,textView24,textView25,textView26,textView27,textView28,textView29,textView30,textView31;

    private SearchView searchView;
    private ImageView imageView;
    private JSONObject data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        searchView=(SearchView)findViewById(R.id.searchView);

        imageView=(ImageView)findViewById(R.id.imageView1);

        textView1 = (TextView) findViewById(R.id.textView1);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView12 = (TextView) findViewById(R.id.textView12);
        textView13 = (TextView) findViewById(R.id.textView13);
        textView14 = (TextView) findViewById(R.id.textView14);
        textView15 = (TextView) findViewById(R.id.textView15);
        textView16 = (TextView) findViewById(R.id.textView16);

        textView17 = (TextView) findViewById(R.id.textView17);
        textView18 = (TextView) findViewById(R.id.textView18);
        textView19 = (TextView) findViewById(R.id.textView19);
        textView20 = (TextView) findViewById(R.id.textView20);
        textView21 = (TextView) findViewById(R.id.textView21);
        textView22 = (TextView) findViewById(R.id.textView22);
        textView23 = (TextView) findViewById(R.id.textView23);
        textView24 = (TextView) findViewById(R.id.textView24);
        textView25 = (TextView) findViewById(R.id.textView25);
        textView26 = (TextView) findViewById(R.id.textView26);
        textView27 = (TextView) findViewById(R.id.textView27);
        textView28 = (TextView) findViewById(R.id.textView28);
        textView29 = (TextView) findViewById(R.id.textView29);
        textView30 = (TextView) findViewById(R.id.textView30);
        textView31 = (TextView) findViewById(R.id.textView31);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                new SearchRequest().execute(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });

    }

    public JSONObject search(String emp_id) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("emp_id", emp_id)
                .build();

        Request request = new Request.Builder().url("http://192.168.1.3/Employee-Tracker/get_employee.php").post(body).build();

        try {
            Response httpResponse = client.newCall(request).execute();
            String response = httpResponse.body().string().toString();
            JSONObject data = new JSONObject(response);

            return data;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setValues(){

        try {
            textView1.setText(data.getString("name"));

            textView17.setText(data.getString("emp_id"));
            textView18.setText(data.getString("designation"));
            textView19.setText(data.getString("department"));
            textView20.setText(data.getString("cluster"));
            textView21.setText(data.getString("age"));
            textView22.setText(data.getString("gender"));
            textView23.setText(data.getString("marital_status"));
            textView24.setText(data.getString("nic_no"));
            textView25.setText(data.getString("contact_no"));
            textView26.setText(data.getString("address"));
            textView27.setText(data.getString("edu_background"));
            textView28.setText(data.getString("post_emp"));
            textView29.setText(data.getString("awards"));
            textView30.setText(data.getString("promotions"));
            textView31.setText(data.getString("no_of_leaves"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class SearchRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
           data=search(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            setValues();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }



}
