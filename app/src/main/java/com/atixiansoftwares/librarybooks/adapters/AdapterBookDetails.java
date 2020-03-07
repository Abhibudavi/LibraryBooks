package com.atixiansoftwares.librarybooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atixiansoftwares.librarybooks.BookDetailsEditActivity;
import com.atixiansoftwares.librarybooks.R;
import com.atixiansoftwares.librarybooks.models.DataBookDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class AdapterBookDetails extends RecyclerView.Adapter<AdapterBookDetails.AdapterBookDetails_ViewHolder> implements Filterable {



    private List<DataBookDetails> dataBookDetailsFiltered;
    private List<DataBookDetails> dataBookDetails;
    Context context;
    public AdapterBookDetails(List<DataBookDetails> dataBookDetailsList, Context context) {
        this.dataBookDetailsFiltered = dataBookDetailsList;
        this.dataBookDetails = dataBookDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterBookDetails_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_book_details, viewGroup, false);
        return new AdapterBookDetails.AdapterBookDetails_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBookDetails_ViewHolder adapterBookDetails_viewHolder, int i) {
        final int newid=i;
        adapterBookDetails_viewHolder.txt_title.setText(dataBookDetailsFiltered.get(i).getTitle());
        adapterBookDetails_viewHolder.txt_author.setText(dataBookDetailsFiltered.get(i).getAuthor());
        //adapterBookDetails_viewHolder.txt_publisher.setText(dataBookDetailsFiltered.get(i).getPublisher());

        //adapterBookDetails_viewHolder.txt_binding.setText(dataBookDetailsFiltered.get(i).getBinding());
        adapterBookDetails_viewHolder.txt_binding.setText(dataBookDetailsFiltered.get(i).getBinding());
        adapterBookDetails_viewHolder.txt_isbn.setText(dataBookDetailsFiltered.get(i).getIsbn());
        adapterBookDetails_viewHolder.txt_sku.setText(dataBookDetailsFiltered.get(i).getSku());


        if(dataBookDetailsFiltered.get(i).getUpdateFlag().equals("1")){
            adapterBookDetails_viewHolder.ll_isbn.setVisibility(View.GONE);
        }

        if(dataBookDetailsFiltered.get(i).getUpdateFlag().equals("1") || dataBookDetailsFiltered.get(i).getSellFlag().equals("1")  ){
            adapterBookDetails_viewHolder.lnl_price.setVisibility(View.VISIBLE);
            adapterBookDetails_viewHolder.txt_price.setText(dataBookDetailsFiltered.get(i).getPrice());
            adapterBookDetails_viewHolder.ll_pubdate.setVisibility(View.GONE);
        }else{
            adapterBookDetails_viewHolder.txt_date_of_publication.setText(dataBookDetailsFiltered.get(i).getDateOfPublication());
        }


        adapterBookDetails_viewHolder.crd_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(dataBookDetailsFiltered.get(newid).getSellFlag().equals("1")){
                    Toast.makeText(context, "You are not authorised. \n Already Book has been sold.", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, BookDetailsEditActivity.class);
                    intent.putExtra("BOOKID",dataBookDetailsFiltered.get(newid).getId());
                    intent.putExtra("BOOKTITLE",dataBookDetailsFiltered.get(newid).getTitle());
                    intent.putExtra("BOOKAUTHOR",dataBookDetailsFiltered.get(newid).getAuthor());
                    intent.putExtra("BOOKBINDING",dataBookDetailsFiltered.get(newid).getBinding());
                    intent.putExtra("BOOKIMGURL",dataBookDetailsFiltered.get(newid).getImage());
                    intent.putExtra("BOOKPRICE",dataBookDetailsFiltered.get(newid).getPrice());
                    intent.putExtra("BOOKICONDITION",dataBookDetailsFiltered.get(newid).getCondition());
                    intent.putExtra("BOOKSELLFLAG",dataBookDetailsFiltered.get(newid).getSellFlag());
                    intent.putExtra("BOOKBINDINGSTATUS",dataBookDetailsFiltered.get(newid).getBindingStatus());
                    intent.putExtra("BOOKUPDATEFLAG",dataBookDetailsFiltered.get(newid).getUpdateFlag());
                    context.startActivity(intent);
                }


            }
        });



       String bookurl="http://www.abhibudavi.com/library/uploads/books/";
        Glide.with(context)
                .load(bookurl+dataBookDetailsFiltered.get(i).getImage())
                .apply(new RequestOptions().fitCenter())
                .into(adapterBookDetails_viewHolder.img_book);

    }

    @Override
    public int getItemCount() {
        return dataBookDetailsFiltered.size();
    }

    public class AdapterBookDetails_ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_pubdate,ll_isbn,ll_sku;
        TextView txt_title;
        TextView txt_author;
        TextView txt_publisher;
        TextView txt_date_of_publication;
        TextView txt_binding;
        TextView txt_isbn;
        CardView crd_id;
        ImageView img_book;
        LinearLayout lnl_price;
        TextView txt_price;
        TextView txt_sku;

        public AdapterBookDetails_ViewHolder(@NonNull View itemView) {
            super(itemView);
                crd_id  =  itemView.findViewById(R.id.crd_id);
                img_book  =  itemView.findViewById(R.id.img_book);
                txt_title  =  itemView.findViewById(R.id.txt_title);
                txt_author  =  itemView.findViewById(R.id.txt_author);
                txt_sku  =  itemView.findViewById(R.id.txt_sku);
                txt_date_of_publication  =  itemView.findViewById(R.id.txt_date_of_publication);
                txt_binding  =  itemView.findViewById(R.id.txt_binding);
                txt_isbn  =  itemView.findViewById(R.id.txt_isbn);

                lnl_price  =  itemView.findViewById(R.id.lnl_price);
                txt_price  =  itemView.findViewById(R.id.txt_price);
                ll_pubdate  =  itemView.findViewById(R.id.ll_pubdate);
                ll_isbn  =  itemView.findViewById(R.id.ll_isbn);
                ll_sku  =  itemView.findViewById(R.id.ll_sku);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {

                    dataBookDetailsFiltered = dataBookDetails;
                } else {
                    List<DataBookDetails> filteredList = new ArrayList<>();
                    for (DataBookDetails row : dataBookDetails) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }
                    }

                    dataBookDetailsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataBookDetailsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataBookDetailsFiltered = (ArrayList<DataBookDetails>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}

