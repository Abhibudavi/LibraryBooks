package com.atixiansoftwares.librarybooks.ui.home;


import android.content.Context;
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
public class ViewSoldBookFragment extends Fragment {

    EditText edt_search;
    RecyclerView rcl_books_view;
    UserServices userServices;
    AdapterBookDetails adapterBookDetailsViewBooks;
    List<DataBookDetails> dataBookDetailsList = new ArrayList<>();
    Context context;
    View view;


    public ViewSoldBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_sold_book, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        userServices = ApiUtils.getUserService();
        edt_search = view.findViewById(R.id.edt_search);
        rcl_books_view = view.findViewById(R.id.rcl_books_view);

        findViews();
    }

    private void findViews() {
        dataBookDetailsList.clear();
        findBooks();

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
                    adapterBookDetailsViewBooks.getFilter().filter(text);
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

        Call<BookDetailsListModel> bookDetailsListModelCallView= userServices.viewSoldbookList(userid);

        bookDetailsListModelCallView.enqueue(new Callback<BookDetailsListModel>() {
            @Override
            public void onResponse(Call<BookDetailsListModel> call, Response<BookDetailsListModel> response) {

                Progress.dismissProgress();

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


    private void setAdapters(List<DataBookDetails> dataBookDetailsList) {
        rcl_books_view.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapterBookDetailsViewBooks = new AdapterBookDetails(dataBookDetailsList, context);
        rcl_books_view.setAdapter(adapterBookDetailsViewBooks);
    }

}
