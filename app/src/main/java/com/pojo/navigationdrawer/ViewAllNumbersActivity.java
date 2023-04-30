package com.pojo.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pojo.navigationdrawer.adapter.ViewAllNumberAdapter;
import com.pojo.navigationdrawer.databinding.ActivityLoginBinding;
import com.pojo.navigationdrawer.databinding.ActivityViewAllNumbersBinding;
import com.pojo.navigationdrawer.model.CalledStatusResponse;
import com.pojo.navigationdrawer.model.CustomerBasetResponse;
import com.pojo.navigationdrawer.utils.AppConstant;
import com.pojo.navigationdrawer.utils.ProgressDlg;
import com.pojo.navigationdrawer.utils.SessionHandler;
import com.pojo.navigationdrawer.utils.SqliteManager;
import com.pojo.navigationdrawer.webservice.ApiClient;
import com.pojo.navigationdrawer.webservice.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllNumbersActivity extends AppCompatActivity implements ViewAllNumberAdapter.OnNumberClickable {

    ActivityViewAllNumbersBinding binding;

    ViewAllNumberAdapter viewAllNumberAdapter;

    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_all_numbers);

        binding = ActivityViewAllNumbersBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sqliteManager=new SqliteManager(ViewAllNumbersActivity.this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ViewAllNumbersActivity.this, LinearLayoutManager.VERTICAL, false);
        binding.rvViewAllNumber.setLayoutManager(mLayoutManager);

        getCustomerBase();

    }

    private void getCustomerBase() {
        Dialog dialogs = ProgressDlg.showProgressBar(ViewAllNumbersActivity.this);

        System.out.println("GetCustomerBase");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String strDate = formatter.format(date);
        System.out.print("Currentdate " + strDate);

        ApiInterface apiService = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<CustomerBasetResponse> call = apiService.doGetCustomerBase(SessionHandler.getValueInt(ViewAllNumbersActivity.this, AppConstant.USER_ID), strDate);
        call.enqueue(new Callback<CustomerBasetResponse>() {
            @Override
            public void onResponse(Call<CustomerBasetResponse> call, Response<CustomerBasetResponse> response) {

                CustomerBasetResponse customerBasetResponse = response.body();
                if (customerBasetResponse.getType().equalsIgnoreCase("success")) {


                    saveBaseInDatabase(customerBasetResponse.getResponseDate());


                    String dateAndTime = customerBasetResponse.getResponseDate().get(0).getDateAndTime();
                    String[] words = dateAndTime.split(" ");//splits the string based on whitespace
                    System.out.println("SAvedDAte " + words[0]);
                    SessionHandler.setValueString(ViewAllNumbersActivity.this, AppConstant.RECORD_DATE, words[0]);



                } else {
                    Toast.makeText(ViewAllNumbersActivity.this,"No base found this day",Toast.LENGTH_SHORT).show();
                    getAllDataFromSqliteDb();

                }

                ProgressDlg.dismisProgressBar(ViewAllNumbersActivity.this, dialogs);
            }

            @Override
            public void onFailure(Call<CustomerBasetResponse> call, Throwable t) {

                System.out.println("TTTT "+t.getMessage().toString() );

                ProgressDlg.dismisProgressBar(ViewAllNumbersActivity.this, dialogs);
            }
        });

    }

    private void saveBaseInDatabase(List<CustomerBasetResponse.ResponseData> responseDate) {

        sqliteManager.addCustomerBaseUser(responseDate);

        getAllDataFromSqliteDb();


    }

    private void getAllDataFromSqliteDb(){
        List<CalledStatusResponse> calledStatusResponseList =sqliteManager.getAllUserData();

        viewAllNumberAdapter=new ViewAllNumberAdapter(ViewAllNumbersActivity.this,calledStatusResponseList,this);
        binding.rvViewAllNumber.setAdapter(viewAllNumberAdapter);

    }

    @Override
    public void onNumberClick(CalledStatusResponse calledStatusResponse) {


       /* Intent intent=new Intent(ViewAllNumbersActivity.this,HomeActivity.class);
        intent.putExtra("Name",calledStatusResponse.getName());
        intent.putExtra("Number",calledStatusResponse.getMobileNumber());
        intent.putExtra("CustomerId",calledStatusResponse.getId());
        startActivity(intent);*/


    }
}