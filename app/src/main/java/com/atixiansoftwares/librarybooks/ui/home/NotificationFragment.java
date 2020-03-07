package com.atixiansoftwares.librarybooks.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.atixiansoftwares.librarybooks.R;
import com.atixiansoftwares.librarybooks.adapters.AdapterNotifications;
import com.atixiansoftwares.librarybooks.models.CommonResponse;
import com.atixiansoftwares.librarybooks.models.NotificationDetails;
import com.atixiansoftwares.librarybooks.models.NotificationListModel;
import com.atixiansoftwares.librarybooks.remote.ApiUtils;
import com.atixiansoftwares.librarybooks.remote.UserServices;
import com.atixiansoftwares.librarybooks.utils.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationFragment extends Fragment  {


    View viewfragment;
    EditText edt_search;
    UserServices userServices;
    public static List<NotificationDetails> notificationDetailsArrayList = new ArrayList<>();
    RecyclerView rcl_notifications;
    AdapterNotifications adapterNotifications;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewfragment =  inflater.inflate(R.layout.fragment_notification, container, false);
        return viewfragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userServices = ApiUtils.getUserService();
        findViews();
        setOnClickListners();
    }



    private void findViews() {

                rcl_notifications = viewfragment.findViewById(R.id.rcl_notifications);
                edt_search = viewfragment.findViewById(R.id.edt_search);




                String userid= PreferenceHelper.getInstance().readString("USERID");

                Call<NotificationListModel> notificationListModelCall = userServices.getNotificationDetails(userid);

                notificationListModelCall.enqueue(new Callback<NotificationListModel>() {
                    @Override
                    public void onResponse(Call<NotificationListModel> call, Response<NotificationListModel> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                NotificationListModel notificationListModel = response.body();

                                if(notificationListModel.getSuccess().equals("ture")){

                                    notificationDetailsArrayList = notificationListModel.getData();
                                    setAdapters(notificationDetailsArrayList);

                                }else {
                                    Toast.makeText(getActivity(),"Data not found",Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationListModel> call, Throwable t) {

                    }
                });
    }


    private void setOnClickListners() {

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = edt_search.getText().toString().toLowerCase(Locale.getDefault());
                if (notificationDetailsArrayList.size()==0){

                }else {
                    adapterNotifications.getFilter().filter(text);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setAdapters(List<NotificationDetails> notificationDetailsArrayList) {
        rcl_notifications.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapterNotifications = new AdapterNotifications(notificationDetailsArrayList, getActivity(),NotificationFragment.this);
        rcl_notifications.setAdapter(adapterNotifications);
    }

    public  void DeleteNotify(String notifyid,int cardindex){


       deleteFun(notifyid,cardindex);
    }

    private void deleteFun(String notifyid, final int cardindex1) {
        String userid= PreferenceHelper.getInstance().readString("USERID");

        Call<CommonResponse> deleteNotification = userServices.deleteNotification(userid,notifyid);

        deleteNotification.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        CommonResponse commonResponse = response.body();

                        if(commonResponse.getSuccess().equals("true")){

                            Toast.makeText(getActivity(),commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                            notificationDetailsArrayList.remove(cardindex1);
                            setAdapters(notificationDetailsArrayList);

                        }else {
                            Toast.makeText(getActivity(),commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {

            }
        });
    }


}
