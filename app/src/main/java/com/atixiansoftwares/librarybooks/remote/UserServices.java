package com.atixiansoftwares.librarybooks.remote;

import com.atixiansoftwares.librarybooks.models.BookDetailsListModel;
import com.atixiansoftwares.librarybooks.models.CommonResponse;
import com.atixiansoftwares.librarybooks.models.LoginModel;
import com.atixiansoftwares.librarybooks.models.NotificationListModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserServices {

    @FormUrlEncoded
    @POST("UserLogin")
    Call<LoginModel> loginRequest(@Field("mobile") String mobile, @Field("password") String password, @Field("firebaseId") String firebaseId);



    @FormUrlEncoded
    @POST("SubCategoryList")
    Call<BookDetailsListModel> getBookList(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("viewSoldbookList")
    Call<BookDetailsListModel> viewSoldbookList(@Field("UserId") String UserId);

    @FormUrlEncoded
    @POST("viewUpdatedbookList")
    Call<BookDetailsListModel> viewUpdatedbookList(@Field("UserId") String UserId);

    @FormUrlEncoded
    @POST("getNotificationDetails")
    Call<NotificationListModel> getNotificationDetails(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("UpdateBookDetails")
    Call<CommonResponse> updateBookDetails(@Field("user_id") String user_id, @Field("book_id") String book_id, @Field("binding") String binding, @Field("price") String price, @Field("condition") String condition);

    @FormUrlEncoded
    @POST("updateSellFlag")
    Call<CommonResponse> updateSellFlag(@Field("user_id") String user_id, @Field("book_id") String book_id, @Field("sellFlag") String sellFlag);

    @FormUrlEncoded
    @POST("DeleteNotification")
    Call<CommonResponse> deleteNotification(@Field("user_id") String user_id, @Field("notification_id") String notification_id);


}
