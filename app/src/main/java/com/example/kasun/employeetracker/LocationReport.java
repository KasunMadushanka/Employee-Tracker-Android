package com.example.kasun.employeetracker;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LocationReport extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_report);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       new LocationTracker().execute("1");

    }

    public void setMarker(){

        try {
            double latitude = Double.parseDouble(data.getString("latitude"));
            double longitude=Double.parseDouble(data.getString("longitude"));

            LatLng sydney = new LatLng(latitude,longitude);

            mMap.addMarker(new MarkerOptions().position(sydney).title(data.getString("name")));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public JSONObject performRequest(){

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("emp_id","1")
                .build();

        Request request = new Request.Builder().url("http://192.168.1.3/Employee-Tracker/get_locations.php").post(body).build();

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

    private class LocationTracker extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            data=performRequest();
           return null;
        }

        @Override
        protected void onPostExecute(String result) {
               setMarker();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}
