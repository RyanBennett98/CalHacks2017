package com.example.benjaminta.androidpickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.LinearLayout;

import calhacks.pickup.User;

public class ProfileActivity extends AppCompatActivity {
    private static User current_user;
    private TextView usernameText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        current_user = MainActivity.getCurrentUser();
        TextView textElement = (TextView) findViewById(R.id.textView10);
        textElement.setText(current_user.getUsername());
    }

    public void friends(View v) {
        Intent intent = new Intent (this, FriendsActivity.class);
        startActivity(intent);
    }

    public void maps(View v) {
        Intent intent = new Intent (this, MapsActivity.class);
        startActivity(intent);
    }


}