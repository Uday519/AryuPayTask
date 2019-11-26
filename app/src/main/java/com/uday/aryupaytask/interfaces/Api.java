package com.uday.aryupaytask.interfaces;

import com.uday.aryupaytask.models.EmployeeObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://hradmin.aryupay.io/tracking/";

    @GET("viewreport")
    Call<EmployeeObject> getEmployees();
}
