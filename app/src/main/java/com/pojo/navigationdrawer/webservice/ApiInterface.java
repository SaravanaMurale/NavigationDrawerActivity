package com.pojo.navigationdrawer.webservice;

import com.pojo.navigationdrawer.model.BaseResponse;
import com.pojo.navigationdrawer.model.LoginRequest;
import com.pojo.navigationdrawer.model.LoginResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("api/login/")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

    @FormUrlEncoded
    @POST("api/add/")
    Call<BaseResponse> doPostCustomerDetails(@Field("customer_name") String keywords,
                                             @Field("mobile_number") String mobilenumber,
                                             @Field("email_id") String state,
                                             @Field("address") String country,
                                             @Field("city") String city,
                                             @Field("pincode") String pincode,
                                             @Field("pan_number") String pan_number,
                                             @Field("adhaar_number") String adhaar_number,
                                             @Field("mother_name") String mother_name,
                                             @Field("qualification") String qualification,
                                             @Field("company_name") String company_name,
                                             @Field("designation") String designation,
                                             @Field("requirement") String requirement,
                                             @Field("category") String category,
                                             @Field("range") String gender,
                                             @Field("status") String specialization,
                                             @Field("remarks") String cities,
                                             @Field("created_by") int speciality_name,
                                             @Field("document") String state_name,

                                             @Field("pan_document") String pan_document,
                                             @Field("salary_slip") String salary_slip,
                                             @Field("bank_statment") MultipartBody.Part bank_statment,
                                             @Field("reference_mobile_number_1") String reference_mob_1,
                                             @Field("reference_mobile_number_2") String reference_mob_2

                                             );

    /*@GET("api/record/")
    Call<CustomerBasetResponse> doGetCustomerBase(@Query("employee_id") int userId,@Query("date") String date);*/


}
