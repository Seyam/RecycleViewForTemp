package com.example.recycleviewfortemp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EmployeeList {
    @SerializedName("outletId")
    private String outletId;

    @SerializedName("allTempSensorList")
    private ArrayList<Employee> employeeList;

    public String getOutletId() {
        return outletId;
    }

    public ArrayList<Employee> getEmployeeArrayList() {

        return employeeList;
    }

}
