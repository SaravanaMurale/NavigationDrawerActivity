package com.pojo.navigationdrawer.model;

public class CalledStatusResponse {


    int id;
    String name;
    String mobileNumber;
    String calledStatus;
    String date;



    public CalledStatusResponse(int id, String name, String mobileNumber, String calledStatus,String date) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.calledStatus = calledStatus;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String isCalledStatus() {
        return calledStatus;
    }

    public void setCalledStatus(String calledStatus) {
        this.calledStatus = calledStatus;
    }

    public String getCalledStatus() {
        return calledStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
