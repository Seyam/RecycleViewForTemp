package com.example.recycleviewfortemp.API;


import com.example.recycleviewfortemp.Model.EmployeeList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserClient {
    @GET("retrofit-demo.php")
    Call<EmployeeList> getEmployeeDataWithQueryParams(@Query("company_no") int companyNo);

    @GET("data")
    Call<EmployeeList> getEmployeeData();
}
