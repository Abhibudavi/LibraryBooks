package com.atixiansoftwares.librarybooks.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookDetailsListModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DataBookDetails> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public BookDetailsListModel() {
    }

    /**
     *
     * @param data
     * @param success
     * @param message
     */
    public BookDetailsListModel(String success, String message, List<DataBookDetails> data) {
        super();
        this.success = success;
        this.message = message;
        this.data = data;
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

    public List<DataBookDetails> getData() {
        return data;
    }

    public void setData(List<DataBookDetails> data) {
        this.data = data;
    }

}
