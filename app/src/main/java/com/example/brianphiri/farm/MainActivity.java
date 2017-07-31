package com.example.brianphiri.farm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.brianphiri.farm.adapter.CustomerOrderListAdapter;
import com.example.brianphiri.farm.customer.CustomerMainActivity;
import com.example.brianphiri.farm.farmer.FarmerMainActivity;

public class MainActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FarmerMainActivity.class);
                startActivity(intent);
            }
        });
    }

}
