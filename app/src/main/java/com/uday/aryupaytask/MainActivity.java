package com.uday.aryupaytask;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.uday.aryupaytask.adapter.RecycleAdapter;
import com.uday.aryupaytask.interfaces.Api;
import com.uday.aryupaytask.models.Employee;
import com.uday.aryupaytask.models.EmployeeObject;
import com.uday.aryupaytask.viewmodels.EmployeeViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecycleAdapter.OnEmployeeListner {

    private EmployeeViewModel mViewModel;
    private RecyclerView rv;
    private RecycleAdapter recycleAdapter;
    private Dialog dialog;
    private ProgressBar progressBar;
    List<Employee> employee_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new Dialog(this);
        rv = findViewById(R.id.emp_recycleview);
        progressBar = findViewById(R.id.progress_bar);
        mViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        mViewModel.getEmployeelist().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                employee_list = employees;
                initRecyclerView(employees);
            }
        });
        mViewModel.getShowProgressBar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                    progressBar.setVisibility(View.VISIBLE);
                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    @Override
    public void onEmployeeClick(int index) {
        View v = rv.getLayoutManager().findViewByPosition(index);
        updateEmployeeDetails(v,index);
    }

    public void initRecyclerView(List<Employee> employees){
        rv.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(employees,this,this);
        rv.setAdapter(recycleAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());

    }

    public void updateEmployeeDetails(View v, final int index){
        Button close_dialog, submit;
        final EditText employee_id, employee_name,employee_category,employee_category_id,employee_address,employee_contact,employee_code, employee_description;
        TextView employee_id_v, employee_name_v,employee_category_v,employee_category_id_v,employee_address_v,employee_contact_v,employee_code_v, employee_description_v;
        ImageView employee_image;
        ImageView employee_image_v;
        employee_id_v = v.findViewById(R.id.employee_id);
        employee_name_v = v.findViewById(R.id.employee_name);
        employee_category_v = v.findViewById(R.id.employee_category);
        employee_category_id_v = v.findViewById(R.id.employee_category_id);
        employee_address_v = v.findViewById(R.id.employee_address);
        employee_contact_v = v.findViewById(R.id.employee_contact);
        employee_code_v = v.findViewById(R.id.employee_code);
        employee_description_v = v.findViewById(R.id.employee_id_description);
        employee_image_v = v.findViewById(R.id.employee_image);


        dialog.setContentView(R.layout.custom_popup);
        employee_id = dialog.findViewById(R.id.employee_id);
        employee_name = dialog.findViewById(R.id.employee_name);
        employee_category = dialog.findViewById(R.id.employee_category);
        employee_category_id = dialog.findViewById(R.id.employee_category_id);
        employee_address = dialog.findViewById(R.id.employee_address);
        employee_contact = dialog.findViewById(R.id.employee_contact);
        employee_code = dialog.findViewById(R.id.employee_code);
        employee_description = dialog.findViewById(R.id.employee_id_description);
        employee_image = dialog.findViewById(R.id.employee_image);


        employee_image.setImageDrawable(employee_image_v.getDrawable());
        employee_id.setText(employee_id_v.getText().toString());
        employee_name.setText(employee_name_v.getText().toString());
        employee_category.setText(employee_category_v.getText().toString());
        employee_category_id.setText(employee_category_id_v.getText().toString());
        employee_address.setText(employee_address_v.getText().toString());
        employee_contact.setText(employee_contact_v.getText().toString());
        employee_description.setText(employee_description_v.getText().toString());
        employee_code.setText(employee_code_v.getText().toString());

        close_dialog = dialog.findViewById(R.id.close_dialog);
        submit = dialog.findViewById(R.id.submit);
        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee temp = employee_list.get(index);
                temp.id = employee_id.getText().toString();
                temp.name = employee_name.getText().toString();
                temp.category = employee_category.getText().toString();
                temp.categoryid = employee_category_id.getText().toString();
                temp.address = employee_address.getText().toString();
                temp.contact = employee_contact.getText().toString();
                temp.description = employee_description.getText().toString();
                temp.empcode = employee_code.getText().toString();
                employee_list.remove(index);
                employee_list.add(index, temp);
                recycleAdapter.setEmployees(employee_list);
                recycleAdapter.notifyItemChanged(index);
                Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
        dialog.show();

    }
}
