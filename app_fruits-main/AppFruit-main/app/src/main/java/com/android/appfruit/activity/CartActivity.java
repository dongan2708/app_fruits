package com.android.appfruit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.appfruit.R;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }


    public void onCheckoutClicked(View view) {

        startActivity(new Intent(getApplicationContext(), CheckoutActivity.class));
    }
}