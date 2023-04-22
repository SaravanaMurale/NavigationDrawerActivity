package com.pojo.navigationdrawer.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("type")
    String type;
    @SerializedName("message")
    String message;

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
}
