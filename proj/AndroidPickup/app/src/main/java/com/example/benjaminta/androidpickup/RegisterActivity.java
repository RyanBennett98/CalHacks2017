package com.example.benjaminta.androidpickup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calhacks.pickup.User;

import static com.example.benjaminta.androidpickup.MainActivity.pickupdb;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameText;
    EditText passwordText;
    final Pattern STANDARD_CHARS = Pattern.compile("[a-zA-Z\\d]*");
    Matcher standardMatcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        usernameText = (EditText) findViewById(R.id.userText);
        passwordText = (EditText) findViewById(R.id.passText);
    }

    public void registerButtonActivity(View v) {
        try {
            register();
            profile(v);
        } catch (InvalidParameterException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

        if (pickupdb.getUser(username) != User.EMPTY_USER) {
            throw new InvalidParameterException("Username already exists");
        } else {
            User newUser = new User(username, password);
            pickupdb.addUser(newUser);
            MainActivity.setCurrentUser(newUser);
        }
    }

    public void profile(View v) {
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }

}
