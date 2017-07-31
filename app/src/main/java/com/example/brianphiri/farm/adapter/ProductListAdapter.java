package com.example.brianphiri.farm.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.brianphiri.farm.R;
import com.example.brianphiri.farm.models.Orders;
import com.example.brianphiri.farm.models.Products;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by brianphiri on 7/30/17.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private static final int LENGTH = 18;
    Context context;
    public List<String> productsDataTitle;
    public List<String> productsDataDescription;

    final List<Products> orderedProduct = new LinkedList<>();

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("orders");

    public ProductListAdapter(FragmentActivity activity, List<String> productsDataTitle, List<String> productsDataDescription) {
        this.context = activity;
        this.productsDataTitle = productsDataTitle;
        this.productsDataDescription = productsDataDescription;
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new ProductListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(final ProductListAdapter.ViewHolder holder, final int position) {
        holder.title.setText(productsDataTitle.get(position));
        holder.message.setText(productsDataDescription.get(position));
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Orders orders = new Orders(holder.title.getText().toString(), holder.message.getText().toString(),"farmerTest", "customerTest");
                myRef.push().setValue(orders);
            }
        });

        holder.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View inflater = LayoutInflater.from(context).inflate(R.layout.dialog_readmore, null);

                final TextView message = (TextView) inflater.findViewById(R.id.more_information_txt);
                message.setText(productsDataDescription.get(position));

                builder.setView(inflater);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsDataTitle.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, message;
        Button order, read;

        public ViewHolder(View itemView) {
            super(itemView);

        }
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            title = (TextView) itemView.findViewById(R.id.card_title);
            message = (TextView) itemView.findViewById(R.id.card_text);
            order = (Button) itemView.findViewById(R.id.action_button);
            read = (Button) itemView.findViewById(R.id.read_btn);
        }
    }
}
