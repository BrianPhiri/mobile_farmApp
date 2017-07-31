package com.example.brianphiri.farm.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brianphiri.farm.R;
import com.example.brianphiri.farm.adapter.CustomerOrderListAdapter;
import com.example.brianphiri.farm.models.Orders;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.internal.zzs.TAG;

public class OrdersListFragment extends Fragment {
    private List<String> ordersDataTitle = new ArrayList<>();
    private  List<String> ordersDataDescription = new ArrayList<>();
    CustomerOrderListAdapter orderListAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("orders");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final RecyclerView recyclerView = (RecyclerView) inflater.inflate( R.layout.recycler_view, container, false);

        ValueEventListener valueEventListener = myRef.orderByChild("user").equalTo("customerTest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ordersDataDescription.clear();
                ordersDataTitle.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Orders orders = snapshot.getValue(Orders.class);
                    ordersDataDescription.add(orders.getDescription());
                    ordersDataTitle.add(orders.getProduct());
                }
                orderListAdapter = new CustomerOrderListAdapter(getActivity(), ordersDataDescription, ordersDataTitle);
                recyclerView.setAdapter((orderListAdapter));
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        return recyclerView;
    }
}
