package com.pojo.navigationdrawer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("type")
    String type;
    @SerializedName("message")
    String message;
    @SerializedName("responseData")
    List<UserDetails> responseData;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserDetails> getResponseData() {
        return responseData;
    }

    public void setResponseData(List<UserDetails> responseData) {
        this.responseData = responseData;
    }

    public class UserDetails {
        @SerializedName("id")
        int id;
        @SerializedName("name")
        String name;
        @SerializedName("email")
        String email;
        @SerializedName("mobile_number")
        String mobile_number;
        @SerializedName("status")
        int status;


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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile_number() {
            return mobile_number;
        }

        public void setMobile_number(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}
