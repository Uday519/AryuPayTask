package com.uday.aryupaytask.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeObject {

    @SerializedName("Success")
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
