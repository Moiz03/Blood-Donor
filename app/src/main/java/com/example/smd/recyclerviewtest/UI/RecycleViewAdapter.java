package com.example.smd.recyclerviewtest.UI;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.smd.recyclerviewtest.Models.DonorsData;
import com.example.smd.recyclerviewtest.R;

import java.util.ArrayList;

import io.github.muddz.styleabletoast.StyleableToast;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> implements Filterable {

    private ArrayList<DonorsData> data;
    private ArrayList<DonorsData> filteredList;
    private Context context;
    public String location;


    public RecycleViewAdapter(Context context, ArrayList<DonorsData> data)
    {

        this.data = new ArrayList<DonorsData>();
        this.data = data;
        this.filteredList = new ArrayList<DonorsData>(data);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(filteredList.get(position).getName());
        holder.city.setText(filteredList.get(position).getCity());
        holder.status.setText(filteredList.get(position).getStatus());
        holder.availability.setText(filteredList.get(position).getAvailability());
        holder.blood_group.setText(filteredList.get(position).getBlood_group());

        holder.phone_no.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                DonorsData donorsData = filteredList.get(position);
                if(donorsData.getAvailability().compareToIgnoreCase("AVAILABLE")==0)
                {   //calling
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.setData(Uri.parse("tel:" + donorsData.getPhone_no()));
                    view.getContext().startActivity(intent);
                }
                else {
                    String text = "Donor not Available";
                    Toast toast = Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, Gravity.CENTER, Gravity.CENTER);
                    toast.show();
                }
                //StyleableToast.makeText(view.getContext(), filteredList.get(position).getPhone_no(), Toast.LENGTH_LONG, R.style.mytoast).show();
            }
        });

        holder.chat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                DonorsData donorsData = filteredList.get(position);
                if(donorsData.getAvailability().compareToIgnoreCase("AVAILABLE")==0)
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    intent.setData(Uri.parse("sms:" + donorsData.getPhone_no()));
                    view.getContext().startActivity(intent);
                }
                else {
                    String text = "Donor not Available";
                    Toast toast = Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, Gravity.CENTER, Gravity.CENTER);
                    toast.show();
                }
                //StyleableToast.makeText(view.getContext(), filteredList.get(position).getPhone_no(), Toast.LENGTH_LONG, R.style.mytoast).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return search_filter;
    }
    private final Filter search_filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<DonorsData> filter_list = new ArrayList<>();

            if (charSequence.length() > 0)
            {
                for(int i=0;i<data.size();i++)
                {
                    if(data.get(i).getCity().toLowerCase().contains(charSequence.toString().toLowerCase())
                    ||data.get(i).getBlood_group().toLowerCase().contains(charSequence.toString().toLowerCase())
                    ||data.get(i).getAvailability().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filter_list.add(data.get(i));
                    }
                }
            }
            else if(charSequence.toString().equals(""))
            {
                filter_list=data;
            }

            FilterResults results = new FilterResults();
            results.count = filter_list.size();
            results.values = filter_list;

            return results;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            filteredList = (ArrayList<DonorsData>) filterResults.values;
            notifyDataSetChanged();
        }

    };


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView city;
        TextView status;
        TextView blood_group;
        ImageView phone_no, chat;
        TextView availability;
        RelativeLayout parent_layout;
        public ViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            city = itemView.findViewById(R.id.city);
            status = itemView.findViewById(R.id.status);
            availability = itemView.findViewById(R.id.availability);
            blood_group = itemView.findViewById(R.id.blood_group);
            phone_no = itemView.findViewById(R.id.phone_no);
            chat = itemView.findViewById(R.id.chat);
            parent_layout = itemView.findViewById(R.id.item_view_layout);
        }


    }

    public void dataRefresher(ArrayList<DonorsData> donorsData)
    {
        this.data.clear();
        this.data.addAll(donorsData);
        this.filteredList.clear();
        this.filteredList.addAll(donorsData);
        this.notifyDataSetChanged();
    }

    public void wew(String str)
    {
        StyleableToast.makeText(context, str, Toast.LENGTH_LONG, R.style.mytoast).show();
    }

}
