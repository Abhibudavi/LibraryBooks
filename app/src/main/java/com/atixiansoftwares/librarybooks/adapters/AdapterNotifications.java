package com.atixiansoftwares.librarybooks.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.atixiansoftwares.librarybooks.R;
import com.atixiansoftwares.librarybooks.models.CommonResponse;
import com.atixiansoftwares.librarybooks.models.NotificationDetails;
import com.atixiansoftwares.librarybooks.ui.home.NotificationFragment;

import java.util.ArrayList;
import java.util.List;

public class AdapterNotifications extends RecyclerView.Adapter<AdapterNotifications.AdapterNotifications_ViewHolder> implements Filterable{





    private List<NotificationDetails> notificationDetailsFiltered;
    private List<NotificationDetails> notificationDetails;
    Context context;
    Fragment fragment;
    public AdapterNotifications(List<NotificationDetails> notificationDetailsArrayList, FragmentActivity activity, NotificationFragment notificationFragment) {
        this.notificationDetailsFiltered = notificationDetailsArrayList;
        this.notificationDetails = notificationDetailsArrayList;
        this.context = activity;
        this.fragment = notificationFragment;
    }

    @NonNull
    @Override
    public AdapterNotifications_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_notification, viewGroup, false);
        return new AdapterNotifications.AdapterNotifications_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotifications_ViewHolder adapterNotifications_viewHolder, int i) {
        final int newid=i;
        adapterNotifications_viewHolder.txt_message.setText(notificationDetailsFiltered.get(i).getMessage());
        adapterNotifications_viewHolder.txt_datetime.setText(notificationDetailsFiltered.get(i).getAddDate());
        adapterNotifications_viewHolder.imgv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NotificationFragment)fragment).DeleteNotify(notificationDetailsFiltered.get(newid).getId() , newid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notificationDetailsFiltered.size();
    }

    public class AdapterNotifications_ViewHolder extends RecyclerView.ViewHolder  {
        TextView txt_message,txt_datetime;
        ImageView imgv_delete;
        public AdapterNotifications_ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_message = itemView.findViewById(R.id.txt_message);
            txt_datetime = itemView.findViewById(R.id.txt_datetime);
            imgv_delete = itemView.findViewById(R.id.imgv_delete);



        }


    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {

                    notificationDetailsFiltered = notificationDetails;
                } else {
                    List<NotificationDetails> filteredList = new ArrayList<>();
                    for (NotificationDetails row : notificationDetails) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getAddDate().toLowerCase().contains(charString.toLowerCase()) || row.getMessage().toLowerCase().contains(charString.toLowerCase())  ) {
                            filteredList.add(row);
                        }
                    }

                    notificationDetailsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = notificationDetailsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                notificationDetailsFiltered = (ArrayList<NotificationDetails>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
