package com.atixiansoftwares.librarybooks;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.atixiansoftwares.librarybooks.models.LoginModel;
import com.atixiansoftwares.librarybooks.models.UserData;
import com.atixiansoftwares.librarybooks.remote.ApiUtils;
import com.atixiansoftwares.librarybooks.remote.UserServices;
import com.atixiansoftwares.librarybooks.utils.PreferenceHelper;
import com.atixiansoftwares.librarybooks.utils.Progress;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Context context;
    Button btnlogin;
    TextInputEditText id_mobile,id_password;
    String str_mobile,str_password;
    String newToken;

    UserServices userServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        getSupportActionBar().hide();

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.lightgreen));
        }
        getToken();
        userServices = ApiUtils.getUserService();

        findViews();
        setOnClickListner();
    }

    private void findViews() {
        id_mobile = findViewById(R.id.id_mobile);
        id_password = findViewById(R.id.id_password);
        btnlogin = findViewById(R.id.btnlogin);

    }


    private void setOnClickListner() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                str_mobile = id_mobile.getText().toString();
                str_password = id_password.getText().toString();



                if (str_mobile.equals("") || str_mobile.isEmpty()) {
                    id_mobile.requestFocus();
                    Toast.makeText(getApplicationContext(),"Username is required",Toast.LENGTH_LONG).show();
                    return;
                }
                if (str_password.equals("") || str_password.isEmpty()) {
                    id_password.requestFocus();
                    Toast.makeText(getApplicationContext(),"Password is required",Toast.LENGTH_LONG).show();
                    //til_password.setError("Field Requred");
                    return;
                }

                Progress.showProgress(context);

                Call<LoginModel> call = userServices.loginRequest(str_mobile, str_password,newToken);

                call.enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                        Progress.dismissProgress();

                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                LoginModel loginModel = response.body();

                                if(loginModel.getSuccess().equals("true")){


                                    UserData userData=loginModel.getUserData();

                                    String userid=userData.getId();
                                    String vendorcode=userData.getVendorCode();
                                    String username=userData.getName();
                                    String mobileno=userData.getMobile();
                                    String address=userData.getAddress();
                                    String password=userData.getPassword();


                                    PreferenceHelper.getInstance().writeBoolean("REGISTERED", true);
                                    PreferenceHelper.getInstance().writeString("USERID", userid);
                                    PreferenceHelper.getInstance().writeString("VENDORCODE", vendorcode);
                                    PreferenceHelper.getInstance().writeString("USERNAME", username);
                                    PreferenceHelper.getInstance().writeString("MOBILENO", mobileno);
                                    PreferenceHelper.getInstance().writeString("PASSWORD", password);

                                    Intent intent = new Intent(LoginActivity.this , DashboardActivity.class);
                                    startActivity(intent);
                                    finish();


                                }else {
                                    Toast.makeText(context,"UserName Password wrong",Toast.LENGTH_LONG).show();
                                }

                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        Progress.dismissProgress();
                    }
                });


            }
        });
    }

    private void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(LoginActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                newToken = instanceIdResult.getToken();
                System.out.println("MY token"+ newToken);
            }
        });
    }
}
