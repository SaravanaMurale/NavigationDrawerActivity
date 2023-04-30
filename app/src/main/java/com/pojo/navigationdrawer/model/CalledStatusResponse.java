package com.pojo.navigationdrawer.model;

public class CalledStatusResponse {


    int id;
    String name;
    String mobileNumber;
    String calledStatus;



    public CalledStatusResponse(int id, String name, String mobileNumber, String calledStatus) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.calledStatus = calledStatus;
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
}
