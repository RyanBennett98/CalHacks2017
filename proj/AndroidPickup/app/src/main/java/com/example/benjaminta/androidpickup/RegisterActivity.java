package com.example.benjaminta.androidpickup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calhacks.pickup.User;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameText = (EditText) findViewById(R.id.userText);
    EditText passwordText = (EditText) findViewById(R.id.passText);

    final Pattern STANDARD_CHARS = Pattern.compile("[a-zA-Z\\d]*");
    Matcher standardMatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void registerButtonActivity(View v) {
        try {
            register();
            setContentView(R.layout.activity_profile);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void register() throws InvalidParameterException{
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (username.equals("")) {
            throw new InvalidParameterException("Must enter a username");
        }

        if (password.equals("")) {
            throw new InvalidParameterException("Must enter a password");
        }

        standardMatcher = STANDARD_CHARS.matcher(usernameText.getText());
        if (!standardMatcher.matches()) {
            throw new InvalidParameterException("Invalid username, only use letters and numbers");
        }

        if (MainActivity.pickupdb.hasUser(username)) {
            throw new InvalidParameterException("Username already exists");
        } else {
            User user = new User(username, password);
            MainActivity.pickupdb.addTo(username, user);
        }
    }

    protected void onClick() {

    }
}
