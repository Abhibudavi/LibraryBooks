package com.atixiansoftwares.librarybooks.ui.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atixiansoftwares.librarybooks.R;
import com.atixiansoftwares.librarybooks.adapters.AdapterBookDetails;
import com.atixiansoftwares.librarybooks.models.BookDetailsListModel;
import com.atixiansoftwares.librarybooks.models.DataBookDetails;
import com.atixiansoftwares.librarybooks.remote.ApiUtils;
import com.atixiansoftwares.librarybooks.remote.UserServices;
import com.atixiansoftwares.librarybooks.utils.PreferenceHelper;
import com.atixiansoftwares.librarybooks.utils.Progress;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailsFragment extends Fragment {


    String categoryString,categoryIdString;
    EditText edt_search;
    RecyclerView rcl_books;
    UserServices userServices;
    AdapterBookDetails adapterBookDetails;
    List<DataBookDetails> dataBookDetailsList = new ArrayList<>();
    Context context;
    View view;
    Integer listlen;
    CardView crd_nodata;
    TextView tv_empty;


    public BookDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_book_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        userServices = ApiUtils.getUserService();

        findViews();

    }

    private void findViews() {

        edt_search = view.findViewById(R.id.edt_search);
        rcl_books = view.findViewById(R.id.rcl_books);
        crd_nodata = view.findViewById(R.id.crd_nodata);
        tv_empty = view.findViewById(R.id.tv_empty);

        dataBookDetailsList.clear();
        findBooks();

        tv_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_EMAIL,"ashishmishrahuf@gmail.com");
                startActivity(Intent.createChooser(intent,"Send E-mail!!!"));
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //String txtsearch =edt_search.
                String text = edt_search.getText().toString().toLowerCase(Locale.getDefault());

                if(dataBookDetailsList.size()==0){

                }else {
                    adapterBookDetails.getFilter().filter(text);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void findBooks() {
        String userid= PreferenceHelper.getInstance().readString("USERID");

        Progress.showProgress(context);
        Call<BookDetailsListModel> bookDetailsListModelCall= userServices.getBookList(userid);

        bookDetailsListModelCall.enqueue(new Callback<BookDetailsListModel>() {
            @Override
            public void onResponse(Call<BookDetailsListModel> call, Response<BookDetailsListModel> response) {

                Progress.dismissProgress();

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        BookDetailsListModel bookDetailsListModel = response.body();

                        if(bookDetailsListModel.getSuccess().equals("ture")){

                            dataBookDetailsList = bookDetailsListModel.getData();


                            listlen = dataBookDetailsList.size();

                            if(listlen.equals(0)){
                                crd_nodata.setVisibility(View.VISIBLE);
                                edt_search.setVisibility(View.GONE);
                                rcl_books.setVisibility(View.GONE);

                            }



                            setAdapters(dataBookDetailsList);
                        }else {
                            Toast.makeText(context,bookDetailsListModel.getMessage(),Toast.LENGTH_LONG).show();

                            listlen = dataBookDetailsList.size();

                            if(listlen.equals(0)){
                                crd_nodata.setVisibility(View.VISIBLE);
                                edt_search.setVisibility(View.GONE);
                                rcl_books.setVisibility(View.GONE);
                            }

                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<BookDetailsListModel> call, Throwable t) {
                Progress.dismissProgress();

                listlen = dataBookDetailsList.size();

                if(listlen.equals(0)){
                    crd_nodata.setVisibility(View.VISIBLE);
                    edt_search.setVisibility(View.GONE);
                    rcl_books.setVisibility(View.GONE);
                }

            }
        });
    }

    private void setAdapters(List<DataBookDetails> dataBookDetailsList) {
        rcl_books.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapterBookDetails = new AdapterBookDetails(dataBookDetailsList, context);
        rcl_books.setAdapter(adapterBookDetails);
    }


    @Override
    public void onResume() {
        super.onResume();
        findBookstwo();
    }

    private void findBookstwo() {
        String userid= PreferenceHelper.getInstance().readString("USERID");


        //Call<BookDetailsListModel> bookDetailsListModelCall= userServices.getBookList(categoryIdString,userid);
        Call<BookDetailsListModel> bookDetailsListModelCall= userServices.getBookList(userid);

        bookDetailsListModelCall.enqueue(new Callback<BookDetailsListModel>() {
            @Override
            public void onResponse(Call<BookDetailsListModel> call, Response<BookDetailsListModel> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        BookDetailsListModel bookDetailsListModel = response.body();

                        if(bookDetailsListModel.getSuccess().equals("ture")){

                            dataBookDetailsList = bookDetailsListModel.getData();
                            setAdapters(dataBookDetailsList);
                        }else {
                            Toast.makeText(context,bookDetailsListModel.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<BookDetailsListModel> call, Throwable t) {
                Progress.dismissProgress();

            }
        });
    }
}
