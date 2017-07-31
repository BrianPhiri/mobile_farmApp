package com.example.brianphiri.farm.farmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.brianphiri.farm.LoginActivity;
import com.example.brianphiri.farm.R;
import com.example.brianphiri.farm.SettingsActivity;
import com.example.brianphiri.farm.customer.CustomerMainActivity;
import com.example.brianphiri.farm.fragment.FarmerOrderRequestFragment;
import com.example.brianphiri.farm.fragment.FarmerProductFormFragment;
import com.example.brianphiri.farm.fragment.FarmerProductListFragment;

public class FarmerMainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    switchToFragment1();
                    break;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    switchToFragment2();
                    break;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    switchToFragment3();
                    break;
            }
            return false;
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(FarmerMainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_contact_us) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            View inflater = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_readmore, null);

            final TextView message = (TextView) inflater.findViewById(R.id.more_information_txt);
            message.setText("We have designed an application that bring together farmers and customers an online market if you will, to get in touch with us please contact us on 078888888888");

            builder.setView(inflater);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if (id == R.id.action_sign_out){
            Intent intent = new Intent(FarmerMainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void switchToFragment1() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new FarmerProductListFragment()).commit();
    }
    public void switchToFragment2() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new FarmerOrderRequestFragment()).commit();
    }
    public void switchToFragment3() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, new FarmerProductFormFragment()).commit();
    }

}
