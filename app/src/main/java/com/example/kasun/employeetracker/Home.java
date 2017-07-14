package com.example.kasun.employeetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void loadLocationReports(View view) {
        startActivity(new Intent(this, LocationReport.class));
    }

    public void loadEmployeeProfiles(View view) {
        startActivity(new Intent(this, EmployeeProfile.class));
    }

    public void loadLeaveRequest(View view) {
        startActivity(new Intent(this, LeaveRequest.class));
    }

    public void loadOTRequest(View view) {
        startActivity(new Intent(this, OTRequest.class));
    }

    public void loadScheduleMeeting(View view) {
        startActivity(new Intent(this, ScheduleMeeting.class));
    }

    public void loadAttendanceReports(View view) {
        startActivity(new Intent(this, AttendanceReport.class));
    }
}
