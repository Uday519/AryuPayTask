package com.uday.aryupaytask.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.widget.Toast;

import com.uday.aryupaytask.interfaces.Api;
import com.uday.aryupaytask.models.Employee;
import com.uday.aryupaytask.models.EmployeeObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeViewModel extends ViewModel {

    public MutableLiveData<List<Employee>> employeelist ;
    public MutableLiveData<Boolean> showProgressBar = new MutableLiveData<>();
    private  Retrofit retrofit;


    public LiveData<List<Employee>> getEmployeelist() {
        if(employeelist == null){
            showProgressBar.setValue(true);
            employeelist = new MutableLiveData<>();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();
            Api api = retrofit.create(Api.class);
            Call<EmployeeObject> getapi = api.getEmployees();
            getapi.enqueue(new Callback<EmployeeObject>() {
                @Override
                public void onResponse(Call<EmployeeObject> call, Response<EmployeeObject> response) {
                    employeelist.setValue(response.body().getEmployeeList());
                    showProgressBar.setValue(false);
                }
                @Override
                public void onFailure(Call<EmployeeObject> call, Throwable t) {
                    String st= t.getMessage();
                    Log.d("Error loading data", st);
                }
            });
        }
        return employeelist;
    }

    public MutableLiveData<Boolean> getShowProgressBar() {
        return showProgressBar;
    }
}
