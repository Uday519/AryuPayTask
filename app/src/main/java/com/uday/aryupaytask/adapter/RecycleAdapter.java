package com.uday.aryupaytask.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.uday.aryupaytask.R;
import com.uday.aryupaytask.models.Employee;
import com.uday.aryupaytask.repo.DownloadImages;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecyclerViewHolder> {
    List<Employee> employees;
    Context context;
    LayoutInflater inflater;
    private OnEmployeeListner onEmployeeListner;

    public RecycleAdapter(List<Employee> employees, Context context, OnEmployeeListner onEmployeeListner) {
        this.employees = employees;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.onEmployeeListner = onEmployeeListner;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public RecycleAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.employee_row,viewGroup,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, onEmployeeListner);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.employee_id.setText(employees.get(i).id);
        recyclerViewHolder.employee_name.setText(employees.get(i).name);
        recyclerViewHolder.employee_category_id.setText(employees.get(i).categoryid);
        recyclerViewHolder.employee_category.setText(employees.get(i).category);
        recyclerViewHolder.employee_address.setText(employees.get(i).address);
        recyclerViewHolder.employee_code.setText(employees.get(i).empcode);
        recyclerViewHolder.employee_contact.setText(employees.get(i).contact);
        recyclerViewHolder.employee_id_description.setText(employees.get(i).description);
        Picasso.with(context).load(employees.get(i).image).fit().into(recyclerViewHolder.employee_image);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView employee_id;
        TextView employee_name;
        TextView employee_category_id;
        TextView employee_category;
        TextView employee_address;
        TextView employee_code;
        TextView employee_contact;
        TextView employee_id_description;
        ImageView employee_image;

        OnEmployeeListner onEmployeeListner;

        public RecyclerViewHolder(@NonNull View itemView, OnEmployeeListner onEmployeeListner) {
            super(itemView);
            this.employee_id = itemView.findViewById(R.id.employee_id);
            this.employee_name = itemView.findViewById(R.id.employee_name);
            this.employee_category_id = itemView.findViewById(R.id.employee_category_id);
            this.employee_category = itemView.findViewById(R.id.employee_category);
            this.employee_address = itemView.findViewById(R.id.employee_address);
            this.employee_code = itemView.findViewById(R.id.employee_code);
            this.employee_contact = itemView.findViewById(R.id.employee_contact);
            this.employee_id_description = itemView.findViewById(R.id.employee_id_description);
            this.employee_image = itemView.findViewById(R.id.employee_image);

            this.onEmployeeListner = onEmployeeListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onEmployeeListner.onEmployeeClick(getAdapterPosition());
        }
    }

    public interface OnEmployeeListner{
        void onEmployeeClick(int index);
    }
}
