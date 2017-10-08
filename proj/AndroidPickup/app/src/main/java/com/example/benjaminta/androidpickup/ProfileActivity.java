package com.example.benjaminta.androidpickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import calhacks.pickup.User;

public class ProfileActivity extends AppCompatActivity {
    private static User current_user;
    private TextView usernameText, ratingText;

    public final String USERNAME = current_user.getUsername();
    public final String RATING = "" + current_user.getRating();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        current_user = MainActivity.getCurrentUser();
        usernameText = (TextView) findViewById(R.id.usernameView);
        ratingText = (TextView) findViewById(R.id.ratingView);
    }



    public void updateText() {
        usernameText.setText(USERNAME);
        ratingText.append(RATING);
    }


    public void friends(View v) {
        Intent intent = new Intent (this, FriendsActivity.class);
        startActivity(intent);
    }

}
