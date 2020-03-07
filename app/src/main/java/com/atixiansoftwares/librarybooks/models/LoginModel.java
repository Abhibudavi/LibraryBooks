package com.atixiansoftwares.librarybooks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_data")
    @Expose
    private UserData userData;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginModel() {
    }

    /**
     *
     * @param userData
     * @param success
     * @param message
     */
    public LoginModel(String success, String message, UserData userData) {
        super();
        this.success = success;
        this.message = message;
        this.userData = userData;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}