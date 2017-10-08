package com.example.benjaminta.androidpickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import java.security.InvalidParameterException;

import calhacks.pickup.Authenticator;
import calhacks.pickup.Database;
import calhacks.pickup.User;


public class MainActivity extends AppCompatActivity {
    public static Database pickupdb = new Database();
    private EditText passwordText;
    private EditText usernameText;
    private Authenticator authenticator;
    private static User current_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordText = (EditText) findViewById(R.id.passText);
        usernameText = (EditText) findViewById(R.id.userText);
        authenticator = new Authenticator(pickupdb);

        User temp = new User("bob", "pass");
        pickupdb.addTo("bob", temp);
    }

    public void logInButtonActivity(View v) {
        try {
            logIn();
            setContentView(R.layout.activity_profile);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
            passwordText.setText("");
        }

    }

    public void logIn() throws InvalidParameterException{
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (!authenticator.authenticate(username, password)) {
            throw new InvalidParameterException("Incorrect username & password");
        } else {
            current_user = pickupdb.getUser(username);
        }
    }

    public static User getCurrentUser() {
        return current_user;
    }

    public static void setCurrentUser(User user) {
        current_user = user;
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
