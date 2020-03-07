package com.atixiansoftwares.librarybooks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataBookDetails {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("date_of_publication")
    @Expose
    private String dateOfPublication;
    @SerializedName("binding")
    @Expose
    private String binding;
    @SerializedName("binding_status")
    @Expose
    private String bindingStatus;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("sellFlag")
    @Expose
    private String sellFlag;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("other")
    @Expose
    private String other;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("updateFlag")
    @Expose
    private String updateFlag;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("vendor_code")
    @Expose
    private String vendorCode;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataBookDetails() {
    }

    /**
     *
     * @param date
     * @param image
     * @param other
     * @param updateFlag
     * @param sellFlag
     * @param author
     * @param isbn
     * @param binding
     * @param title
     * @param userId
     * @param categoryName
     * @param dateOfPublication
     * @param vendorCode
     * @param condition
     * @param bindingStatus
     * @param price
     * @param publisher
     * @param id
     * @param category
     * @param sku
     */
    public DataBookDetails(String id, String userId, String title, String author, String publisher, String dateOfPublication, String binding, String bindingStatus, String isbn, String category, String image, String price, String condition, String sellFlag, String sku, String other, String date, String updateFlag, String categoryName, String vendorCode) {
        super();
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.dateOfPublication = dateOfPublication;
        this.binding = binding;
        this.bindingStatus = bindingStatus;
        this.isbn = isbn;
        this.category = category;
        this.image = image;
        this.price = price;
        this.condition = condition;
        this.sellFlag = sellFlag;
        this.sku = sku;
        this.other = other;
        this.date = date;
        this.updateFlag = updateFlag;
        this.categoryName = categoryName;
        this.vendorCode = vendorCode;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getBindingStatus() {
        return bindingStatus;
    }

    public void setBindingStatus(String bindingStatus) {
        this.bindingStatus = bindingStatus;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSellFlag() {
        return sellFlag;
    }

    public void setSellFlag(String sellFlag) {
        this.sellFlag = sellFlag;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

}