package com.atixiansoftwares.librarybooks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("book_id")
    @Expose
    private String bookId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("addDate")
    @Expose
    private String addDate;
    @SerializedName("addIP")
    @Expose
    private String addIP;

    /**
     * No args constructor for use in serialization
     *
     */
    public NotificationDetails() {
    }

    /**
     *
     * @param id
     * @param message
     * @param userId
     * @param addDate
     * @param addIP
     * @param bookId
     */
    public NotificationDetails(String id, String userId, String bookId, String message, String addDate, String addIP) {
        super();
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.message = message;
        this.addDate = addDate;
        this.addIP = addIP;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getAddIP() {
        return addIP;
    }

    public void setAddIP(String addIP) {
        this.addIP = addIP;
    }

}