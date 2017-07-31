package com.example.brianphiri.farm.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.brianphiri.farm.R;
import com.example.brianphiri.farm.adapter.FarmerProductListAdapter;
import com.example.brianphiri.farm.adapter.ProductListAdapter;
import com.example.brianphiri.farm.models.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by brianphiri on 7/30/17.
 */

public class FarmerProductListFragment extends Fragment {
    private List<String> productsDataTitle = new ArrayList<>();
    private List<String> productsDataDescription = new ArrayList<>();
    FarmerProductListAdapter requestAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("market");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final RecyclerView recyclerView = (RecyclerView) inflater.inflate( R.layout.recycler_view, container, false);

        ValueEventListener valueEventListener = myRef.orderByChild("farmer").equalTo("FarmTest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productsDataDescription.clear();
                productsDataTitle.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Products products = snapshot.getValue(Products.class);
                    productsDataDescription.add(products.getDescription());
                    productsDataTitle.add(products.getProduct());
                }
                requestAdapter = new FarmerProductListAdapter(getActivity(),productsDataTitle,productsDataDescription);
                recyclerView.setAdapter(requestAdapter);
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
