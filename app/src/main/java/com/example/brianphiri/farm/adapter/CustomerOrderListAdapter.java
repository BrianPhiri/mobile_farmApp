package com.example.brianphiri.farm.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.brianphiri.farm.R;

import java.util.List;

/**
 * Created by brianphiri on 7/30/17.
 */

public class CustomerOrderListAdapter extends RecyclerView.Adapter<CustomerOrderListAdapter.ViewHolder>{
    private static final int LENGTH = 18;
    Context context;
    public List<String> ordersDataTitle;
    public List<String> ordersDataDescription;

    public CustomerOrderListAdapter(FragmentActivity activity, List<String> ordersDataDescription, List<String> ordersDataTitle) {
        this.context = activity;
        this.ordersDataDescription = ordersDataDescription;
        this.ordersDataTitle = ordersDataTitle;
    }

    @Override
    public CustomerOrderListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomerOrderListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(CustomerOrderListAdapter.ViewHolder holder, int position) {
        holder.title.setText(ordersDataTitle.get(position));
        holder.message.setText(ordersDataDescription.get(position));
    }

    @Override
    public int getItemCount() {
        return ordersDataTitle.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, message;
        Button btnC, read;

        public ViewHolder(View itemView) {
            super(itemView);

        }
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.customer_orders, parent, false));
            title = (TextView) itemView.findViewById(R.id.card_title);
            message = (TextView) itemView.findViewById(R.id.card_text);
            btnC = (Button) itemView.findViewById(R.id.action_button);
            read = (Button) itemView.findViewById(R.id.read_btn);
        }
    }
}
