package com.example.benjaminta.androidpickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.Window;
import android.widget.Button;

import calhacks.pickup.Database;


public class MainActivity extends AppCompatActivity {
    public static Database pickupdb = new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewRegister(View v) {
        Intent intent = new Intent (this, RegisterActivity.class);
        startActivity(intent);
    }

    public void profile(View v) {
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }



}
