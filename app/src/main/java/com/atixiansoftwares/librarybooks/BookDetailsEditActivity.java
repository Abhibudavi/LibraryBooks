package com.atixiansoftwares.librarybooks;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.atixiansoftwares.librarybooks.models.CommonResponse;
import com.atixiansoftwares.librarybooks.remote.ApiUtils;
import com.atixiansoftwares.librarybooks.remote.UserServices;
import com.atixiansoftwares.librarybooks.utils.PreferenceHelper;
import com.atixiansoftwares.librarybooks.utils.Progress;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsEditActivity extends AppCompatActivity {

     String bindingStatusString;
     String priceIdString;


     String bookUpdateFlagString;
     String bookSellFlagString;
     String bookPriceString;
     String bookConditionString;
     String userIdString;
     String bookIdString;
     String bookNameString;
     String bookAuthorString;
     String bookBindingString;
     String bookImageurlString;
     Context context;
     String rdbinding;
     String rdcondition;
     String stringsellFlag;


     //TextInputEditText tie_binding;
     TextInputEditText tie_price;
     //TextInputEditText tie_condition;
     Button btnsalesold;
     Button btnupdate;
     RadioButton rdhardcover,rdbspiral,rdbPaperBack;
     RadioButton rdcnew, rdcold, rdcaccepted;

     TextView txt_title;
     TextView txt_author;

    UserServices userServices;


    ImageView img_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details_edit);

        context = this;
        userServices = ApiUtils.getUserService();

        Bundle extras = getIntent().getExtras();
        bookIdString        = extras.getString("BOOKID");
        bookNameString      = extras.getString("BOOKTITLE");
        bookAuthorString    = extras.getString("BOOKAUTHOR");
        bookBindingString   = extras.getString("BOOKBINDING");
        bookImageurlString  = extras.getString("BOOKIMGURL");
        bookPriceString     = extras.getString("BOOKPRICE");
        bookConditionString = extras.getString("BOOKICONDITION");
        bookSellFlagString  = extras.getString("BOOKSELLFLAG");
        bindingStatusString = extras.getString("BOOKBINDINGSTATUS");
        bookUpdateFlagString = extras.getString("BOOKUPDATEFLAG");

        userIdString=PreferenceHelper.getInstance().readString("USERID");



        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.lightgreen));
        }

        getSupportActionBar().setTitle(bookNameString);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable( getResources().getDrawable(R.drawable.splash_background) );

        findViews();
        setonClickListners();
    }


    private void findViews() {
            txt_title = findViewById(R.id.txt_title);
            txt_author = findViewById(R.id.txt_author);

            img_book = findViewById(R.id.img_book);
            tie_price = findViewById(R.id.tie_price);
            btnsalesold = findViewById(R.id.btnsalesold);
            btnupdate = findViewById(R.id.btnupdate);

            rdhardcover  = findViewById(R.id.rdhardcover);
            rdbspiral   = findViewById(R.id.rdbspiral);
            rdbPaperBack   = findViewById(R.id.rdbPaperBack);

            rdcnew          = findViewById(R.id.rdcnew);
            rdcold          = findViewById(R.id.rdcold);
            rdcaccepted     = findViewById(R.id.rdcaccepted);


            txt_title.setText(bookNameString);
            txt_author.setText(bookAuthorString);

            if(bookUpdateFlagString.equals("1")){
                btnupdate.setVisibility(View.GONE);
            }else{
                btnsalesold.setVisibility(View.GONE);
            }

            if(bookBindingString.contains("Hardcover")){
                rdhardcover.setChecked(true);
            }else if(bookBindingString.contains("Spiral")){
                rdbspiral.setChecked(true);
            }else{
                rdbPaperBack.setChecked(true);
            }

            if(bookConditionString.contains("LikeNew")){
                rdcnew.setChecked(true);
            }else if(bookConditionString.contains("Good")){
                rdcold.setChecked(true);
            }else {
                rdcaccepted.setChecked(true);
            }

            tie_price.setText(bookPriceString);

            if(bookSellFlagString.equals("0")){
                btnsalesold.setText("SELL");
            }else{
                btnsalesold.setText("AVAILABEL");
            }


        String bookurl="http://www.abhibudavi.com/library/uploads/books/";
        Glide.with(context).load(bookurl+bookImageurlString).into(img_book);





    }

    private void setonClickListners() {
        btnsalesold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btnsalesold.getText().toString().equals("SELL")){
                    stringsellFlag = "1";
                }else {
                    stringsellFlag = "0";
                }



                Progress.showProgress(context);

                Call<CommonResponse> commonResponseCallSellFlag = userServices.updateSellFlag(userIdString,bookIdString,stringsellFlag);

                commonResponseCallSellFlag.enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        Progress.dismissProgress();
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                CommonResponse commonResponse = response.body();

                                if(commonResponse.getSuccess().equals("true")){
                                    Toast.makeText(context,commonResponse.getMessage(),Toast.LENGTH_LONG).show();

                                     OpenHome();
                                    //btnsalesold.setText("AVAILABEL");

                                  /*  if(stringsellFlag.equals("0")){
                                        btnsalesold.setText("SELL");
                                    }else {
                                        btnsalesold.setText("AVAILABEL");
                                    }*/

                                }else {
                                    Toast.makeText(context,commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {
                        Progress.dismissProgress();
                        Toast.makeText(context,"Failed to Sell",Toast.LENGTH_LONG).show();
                    }
                });



            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                if(rdhardcover.isChecked()){
                    rdbinding="Hardcover";
                }else if(rdbspiral.isChecked()){
                    rdbinding="Spiral";
                }else{
                    rdbinding="Paperback";
                }

                if(rdcnew.isChecked()){
                    rdcondition="LikeNew";
                }else if(rdcold.isChecked()){
                    rdcondition="Good";
                }else if(rdcaccepted.isChecked()){
                    rdcondition="Acceptable";
                }







                priceIdString = tie_price.getText().toString();
                if(priceIdString.equals("")){
                    Toast.makeText(context,"Please enter Price of book",Toast.LENGTH_LONG).show();
                    return;
                }






                Call<CommonResponse> commonResponseCall = userServices.updateBookDetails(userIdString,bookIdString,rdbinding,priceIdString,rdcondition);

                commonResponseCall.enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {

                                CommonResponse commonResponse = response.body();

                                if(commonResponse.getSuccess().equals("true")){
                                    Toast.makeText(context,commonResponse.getMessage(),Toast.LENGTH_LONG).show();

                                    OpenHome();

                                }else {
                                    Toast.makeText(context,commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonResponse> call, Throwable t) {
                        Toast.makeText(context,"Failed due to network",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void OpenHome() {
        Intent intent = new Intent(BookDetailsEditActivity.this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
