package com.pojo.navigationdrawer.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pojo.navigationdrawer.model.CalledStatusResponse;
import com.pojo.navigationdrawer.model.CustomerBasetResponse;

import java.util.ArrayList;
import java.util.List;

public class SqliteManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "bank";
    public static final int DATABASE_VERSION = 1;

    //Maintenance Service Column Datas
    public static final String TABLE_NAME = "cutomerbasetable";

    public static final String COLUMN_ID = "id";
    public static final String NAME = "name";
    public static final String MOBILE_NUMBER = "mobile_number";
    public static final String CALLED_STATUS="called_status";
    public static final String DATE="date";
    public static final String STATUS = "status";

    Context mCtx;

    public SqliteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mCtx = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String customerBAseTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(\n" +
                "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT add_cart_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + NAME + " varchar(200) NOT NULL,\n" +
                "    " + MOBILE_NUMBER + " varchar(200) NOT NULL,\n" +
                "    " + CALLED_STATUS + " varchar(200) NOT NULL,\n" +
                "    " + DATE + " varchar(200) NOT NULL,\n" +
                "    " + STATUS + " text NOT NULL\n" +
                ");";

        sqLiteDatabase.execSQL(customerBAseTable);

    }


    public boolean addCustomerBaseUser(List<CustomerBasetResponse.ResponseData> customerBase) {


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues =new ContentValues();

        boolean insertedStatus=false;

        for (int i = 0; i <customerBase.size() ; i++) {

            System.out.println("InsertedCustomerValue "+customerBase.get(i).getName());



            String dateAndTime = customerBase.get(i).getDateAndTime();
            String[] date = dateAndTime.split(" ");//splits the string based on whitespace
            //System.out.println("SAvedDAte " + date[0]);

            contentValues.put(NAME,    customerBase.get(i).getName());
            contentValues.put(MOBILE_NUMBER, customerBase.get(i).getMobileNumber());
            contentValues.put(STATUS, customerBase.get(i).getStatus());
            contentValues.put(CALLED_STATUS, "false");
            contentValues.put(DATE,date[0]);

            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            insertedStatus=true;
        }


        return insertedStatus;

    }



    public boolean updateCalledStatus(int id) {

        SQLiteDatabase updateSqLiteDatabase = getWritableDatabase();
        ContentValues updateContentValues = new ContentValues();

        updateContentValues.put(CALLED_STATUS, "true");

        return updateSqLiteDatabase.update(TABLE_NAME, updateContentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;


    }





    public List<CalledStatusResponse> getAllUserData(){


        List<CalledStatusResponse> calledStatusResponseList=new ArrayList<>();

        SQLiteDatabase selectAllData = getReadableDatabase();
        Cursor cursor = selectAllData.rawQuery("select id,name,mobile_number,called_status from cutomerbasetable where status=?", new String[]{"Pending"});

        if (cursor.moveToFirst()) {

            do {

                CalledStatusResponse calledStatusResponse=new CalledStatusResponse(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
                calledStatusResponseList.add(calledStatusResponse);

            }
            while (cursor.moveToNext());

        }

        return calledStatusResponseList;
    }


    public void deleteAllValeuesInTable() {

        SQLiteDatabase deleteSqLiteDatabase = this.getWritableDatabase();
        deleteSqLiteDatabase.execSQL("DELETE FROM cutomerbasetable"); //delete all rows in a table

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }






}
