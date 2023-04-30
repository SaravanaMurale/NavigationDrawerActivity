package com.pojo.navigationdrawer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerBasetResponse {

    @SerializedName("type")
    String type;

    @SerializedName("total_records")
    int totalRecords;

    @SerializedName("message")
    String message;
    @SerializedName("responseData")
    List<ResponseData> responseDate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResponseData> getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(List<ResponseData> responseDate) {
        this.responseDate = responseDate;
    }

    public class ResponseData{

        public ResponseData() {
        }



        public ResponseData(int id, String name, String mobileNumber, String status) {
            this.id = id;
            this.name = name;
            this.mobileNumber = mobileNumber;
            this.status = status;
        }

        @SerializedName("id")
        int id;
        @SerializedName("employee_id")
        int empId;
        @SerializedName("name")
        String name;
        @SerializedName("mobile_number")
        String mobileNumber;
        @SerializedName("status")
        String status;
        @SerializedName("created_at")
        String dateAndTime;

        boolean calledStatus;

        public boolean isCalledStatus() {
            return calledStatus;
        }

        public void setCalledStatus(boolean calledStatus) {
            this.calledStatus = calledStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getEmpId() {
            return empId;
        }

        public void setEmpId(int empId) {
            this.empId = empId;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDateAndTime() {
            return dateAndTime;
        }

        public void setDateAndTime(String dateAndTime) {
            this.dateAndTime = dateAndTime;
        }
    }



}
