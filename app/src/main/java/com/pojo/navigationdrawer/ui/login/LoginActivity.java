package com.pojo.navigationdrawer.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pojo.navigationdrawer.MainActivity;
import com.pojo.navigationdrawer.databinding.ActivityLoginBinding;
import com.pojo.navigationdrawer.model.LoginRequest;
import com.pojo.navigationdrawer.model.LoginResponse;
import com.pojo.navigationdrawer.utils.AppConstant;
import com.pojo.navigationdrawer.utils.ProgressDlg;
import com.pojo.navigationdrawer.utils.SessionHandler;
import com.pojo.navigationdrawer.utils.Validation;
import com.pojo.navigationdrawer.webservice.ApiClient;
import com.pojo.navigationdrawer.webservice.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=binding.signupMobile.getText().toString();
                String password=binding.signupPassword.getText().toString();

                if((Validation.isValidEmail(email) && Validation.isValidText(password))){
                    doLogin(email,password);
                }else {
                    Toast.makeText(LoginActivity.this,"Enter Username and password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void doLogin(String email, String password){
        Dialog dialogs=ProgressDlg.showProgressBar(LoginActivity.this);
        LoginRequest loginRequest=new LoginRequest(email,password);

        ApiInterface apiService = ApiClient.getAPIClient().create(ApiInterface.class);

        Call<LoginResponse> call = apiService.doLogin(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                ProgressDlg.dismisProgressBar(LoginActivity.this,dialogs);

                LoginResponse loginResponses=response.body();

                if(loginResponses.getType().equalsIgnoreCase("success")) {

                    if (loginResponses.getResponseData() != null && loginResponses.getResponseData().size() > 0) {
                        if (loginResponses.getResponseData().get(0).getId() > 0) {
                            SessionHandler.setValueSInt(LoginActivity.this, AppConstant.USER_ID, loginResponses.getResponseData().get(0).getId());
                            System.out.println("TypeAndMessage " + loginResponses.getType() + " " + loginResponses.getMessage());
                            System.out.println("ReceivedData " + loginResponses.getResponseData().get(0).getName());
                            launchHomeActivity();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Entered Username and password is wrong", Toast.LENGTH_LONG).show();
                    }

                } else if (loginResponses.getType().equalsIgnoreCase("error")) {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("Exception "+t.getMessage().toString());

            }
        });



    }


    private void launchHomeActivity(){
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}