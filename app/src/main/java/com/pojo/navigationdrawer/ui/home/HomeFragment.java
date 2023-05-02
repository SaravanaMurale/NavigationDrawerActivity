package com.pojo.navigationdrawer.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pojo.navigationdrawer.ViewAllNumbersActivity;
import com.pojo.navigationdrawer.adapter.ViewAllNumberAdapter;
import com.pojo.navigationdrawer.databinding.FragmentHomeBinding;
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

public class HomeFragment extends Fragment implements ViewAllNumberAdapter.OnNumberClickable {

    private FragmentHomeBinding binding;

    ViewAllNumberAdapter viewAllNumberAdapter;

    SqliteManager sqliteManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        sqliteManager=new SqliteManager(getContext());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvViewAllNumber.setLayoutManager(mLayoutManager);

        getCustomerBase();



        return root;
    }

    private void getCustomerBase() {

        Dialog dialogs = ProgressDlg.showProgressBar(getContext());

        System.out.println("GetCustomerBase");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String strDate = formatter.format(date);
        System.out.print("Currentdate " + strDate);

        ApiInterface apiService = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<CustomerBasetResponse> call = apiService.doGetCustomerBase(SessionHandler.getValueInt(getContext(), AppConstant.USER_ID), strDate);
        call.enqueue(new Callback<CustomerBasetResponse>() {
            @Override
            public void onResponse(Call<CustomerBasetResponse> call, Response<CustomerBasetResponse> response) {

                CustomerBasetResponse customerBasetResponse = response.body();
                if (customerBasetResponse.getType().equalsIgnoreCase("success")) {


                    saveBaseInDatabase(customerBasetResponse.getResponseDate());


                    String dateAndTime = customerBasetResponse.getResponseDate().get(0).getDateAndTime();
                    String[] words = dateAndTime.split(" ");//splits the string based on whitespace
                    System.out.println("SAvedDAte " + words[0]);
                    SessionHandler.setValueString(getContext(), AppConstant.RECORD_DATE, words[0]);



                } else {
                    Toast.makeText(getContext(),"No base found this day",Toast.LENGTH_SHORT).show();
                    getAllDataFromSqliteDb();

                }

                ProgressDlg.dismisProgressBar(getContext(), dialogs);
            }

            @Override
            public void onFailure(Call<CustomerBasetResponse> call, Throwable t) {

                System.out.println("TTTT "+t.getMessage().toString() );

                ProgressDlg.dismisProgressBar(getContext(), dialogs);
            }
        });

    }

    private void saveBaseInDatabase(List<CustomerBasetResponse.ResponseData> responseDate) {

        sqliteManager.addCustomerBaseUser(responseDate);

        getAllDataFromSqliteDb();


    }

    private void getAllDataFromSqliteDb(){
        List<CalledStatusResponse> calledStatusResponseList =sqliteManager.getAllUserData();

        viewAllNumberAdapter=new ViewAllNumberAdapter(getContext(),calledStatusResponseList,this);
        binding.rvViewAllNumber.setAdapter(viewAllNumberAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();

        int refreshStatus=SessionHandler.getValueInt(getContext(),AppConstant.REFRESH_STATUS);

        if(refreshStatus==1) {

            //viewAllNumberAdapter.notifyDataSetChanged();

            getAllDataFromSqliteDb();

            SessionHandler.setValueSInt(getContext(),AppConstant.REFRESH_STATUS,0);

        }

    }

    @Override
    public void onNumberClick(CalledStatusResponse calledStatusResponse) {



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}