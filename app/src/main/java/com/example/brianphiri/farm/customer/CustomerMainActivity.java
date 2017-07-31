package com.example.brianphiri.farm.customer;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.brianphiri.farm.LoginActivity;
import com.example.brianphiri.farm.R;
import com.example.brianphiri.farm.SettingsActivity;
import com.example.brianphiri.farm.fragment.OrdersListFragment;
import com.example.brianphiri.farm.fragment.ProductsListFragment;

public class CustomerMainActivity extends AppCompatActivity {


    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new ProductsListFragment(),
                    new OrdersListFragment(),
            };
            private final String[] mFragmentNames = new String[] {"Products","Orders"};
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }


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
            Intent intent = new Intent(CustomerMainActivity.this, SettingsActivity.class);
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
            Intent intent = new Intent(CustomerMainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
