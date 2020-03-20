package com.example.celebsdashboard.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.celebsdashboard.R;
import com.example.celebsdashboard.model.Contact;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ContactAdapterViewHolder> {

    // holds fetched data, set by setContactData() method
    private ArrayList<Contact> mContactsData;

    // inflate the list item layout as a viewholder
    @NonNull
    @Override
    public ContactAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new ContactAdapterViewHolder(view);
    }

    // set name and mobile textviews wrt position in the data arraylist
    @Override
    public void onBindViewHolder(@NonNull ContactAdapterViewHolder holder, int position) {
        Contact currentContact = mContactsData.get(position);
        holder.mContactName.setText(currentContact.getName());
        holder.mContactNumber.setText(currentContact.getPhone().getMobile());
    }

    @Override
    public int getItemCount() {
        if (mContactsData == null)
            return 0;
        return mContactsData.size();
    }

    public class ContactAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView mContactName;
        public TextView mContactNumber;

        public ContactAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mContactName = itemView.findViewById(R.id.tv_contactname);
            mContactNumber = itemView.findViewById(R.id.tv_contactdetails);
        }
    }

    // populates data arraylist with the fetched data arraylist
    public void setContactData(ArrayList<Contact> contactsData){
        mContactsData = contactsData;
        notifyDataSetChanged();
    }
}
