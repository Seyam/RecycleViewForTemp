package com.example.recycleviewfortemp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.recycleviewfortemp.API.RetrofitInstance;
import com.example.recycleviewfortemp.API.UserClient;
import com.example.recycleviewfortemp.Adapter.EmployeeAdapter;
import com.example.recycleviewfortemp.Model.Employee;
import com.example.recycleviewfortemp.Model.EmployeeList;
import com.example.recycleviewfortemp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Create handle for the RetrofitInstance interface*/
        UserClient service = RetrofitInstance.getRetrofitInstance().create(UserClient.class);
        /*Call the service method with parameter in the interface to get the employee data*/
        Call<EmployeeList> call = service.getEmployeeData();

        /*Log the URL called*/
        Log.e("URL Called", call.request().url().toString());

        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                generateEmployeeList(response.body().getEmployeeArrayList());
                Toast.makeText(MainActivity.this, "Outlet ID: "+response.body().getOutletId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Something Went Wrong!", Toast.LENGTH_LONG).show();

            }
        });
    }


    /*Method to generate List of employees using RecyclerView with custom adapter*/
    public void generateEmployeeList(ArrayList<Employee> empDataList){

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);


        //create a new constructor of EmployeeAdapter class with required params
        adapter = new EmployeeAdapter(empDataList);
        //create a new constructor of LinearLayoutManager class with required params
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }



}
