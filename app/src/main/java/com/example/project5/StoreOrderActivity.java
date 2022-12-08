package com.example.project5;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is to demo the Intent class, which you can use to start another Activity.
 * @author Lily Chang
 */
public class StoreOrderActivity extends AppCompatActivity {
    //load the layout file
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
    }


}