package com.pojo.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pojo.navigationdrawer.ui.login.LoginActivity;
import com.pojo.navigationdrawer.utils.AppConstant;
import com.pojo.navigationdrawer.utils.SessionHandler;
import com.pojo.navigationdrawer.utils.SqliteManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SplashScreenActivity extends AppCompatActivity {

    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sqliteManager=new SqliteManager(SplashScreenActivity.this);

        int userId = SessionHandler.getValueInt(SplashScreenActivity.this, AppConstant.USER_ID);

        System.out.println("UserId " + userId);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (userId > 0) {


                    doDeleteAllRecordsInSqliteForFutureDate();


                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 500);

    }

    private void doDeleteAllRecordsInSqliteForFutureDate() {


        try {

            String savedDate=SessionHandler.getValueString(SplashScreenActivity.this,AppConstant.RECORD_DATE);

            if (savedDate!=null && !savedDate.isEmpty() && !savedDate.equalsIgnoreCase("null")) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                Date date1 = formatter.parse(formatter.format(date));
                Date date2 = formatter.parse(savedDate);

                if (date1.compareTo(date2) == 0) {

                } else {
                    sqliteManager.deleteAllValeuesInTable();
                }

            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}