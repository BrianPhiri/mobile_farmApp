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

import java.util.List;

/**
 * Created by brianphiri on 7/30/17.
 */

public class FarmerProductListAdapter extends RecyclerView.Adapter<FarmerProductListAdapter.ViewHolder> {
    private static final int LENGTH = 18;
    Context context;
    public List<String> productsDataTitle;
    public List<String> productsDataDescription;

    public FarmerProductListAdapter(FragmentActivity activity, List<String> productsDataTitle, List<String> productsDataDescription) {
        this.context = activity;
        this.productsDataTitle = productsDataTitle;
        this.productsDataDescription = productsDataDescription;
    }

    @Override
    public FarmerProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new FarmerProductListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(FarmerProductListAdapter.ViewHolder holder, final int position) {
        holder.title.setText(productsDataTitle.get(position));
        holder.message.setText(productsDataDescription.get(position));
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
            super(inflater.inflate(R.layout.fragment_product_farmer_list, parent, false));
            title = (TextView) itemView.findViewById(R.id.card_title);
            message = (TextView) itemView.findViewById(R.id.card_text);
            order = (Button) itemView.findViewById(R.id.action_button);
            read = (Button) itemView.findViewById(R.id.read_btn);
        }
    }
}
