package com.example.brianphiri.farm.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.brianphiri.farm.R;
import com.example.brianphiri.farm.models.Products;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by brianphiri on 7/30/17.
 */

public class FarmerProductFormFragment extends Fragment {
    EditText product, description, price, location;
    Button clear, postProduct;
    final List<Products> prods = new LinkedList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("market");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate( R.layout.fragment_product_farmer_add, container, false);
        product = (EditText) rootView.findViewById((R.id.productNameInput));
        description = (EditText) rootView.findViewById(R.id.productDescriptionInput);
        price = (EditText) rootView.findViewById(R.id.productPriceInput);
        location = (EditText) rootView.findViewById(R.id.farmLocation);

        clear = (Button) rootView.findViewById(R.id.clearBtn);
        postProduct = (Button) rootView.findViewById(R.id.sellBtn);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product.setText("");
                description.setText("");
                price.setText("");
                location.setText("");
            }
        });

        postProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Products products = new Products(product.getText().toString(), description.getText().toString(), price.getText().toString(), location.getText().toString(), "FarmTest");
                myRef.push().setValue(products);

                product.setText("");
                description.setText("");
                price.setText("");
                location.setText("");
            }
        });

        return rootView;
    }
}
