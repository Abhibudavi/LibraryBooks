package com.atixiansoftwares.librarybooks.remote;

public class ApiUtils {

  // public static final String BASE_URL = "http://www.abhibudavi.com/library/index.php/Application/";
   public static final String BASE_URL = "http://www.rabookshop.club/Application/";



    public static UserServices getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserServices.class);
    }
}
