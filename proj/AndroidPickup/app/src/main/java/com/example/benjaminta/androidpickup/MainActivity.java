package com.example.benjaminta.androidpickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.security.InvalidParameterException;
import calhacks.pickup.Authenticator;
import calhacks.pickup.DatabaseHandler;
import calhacks.pickup.User;

public class MainActivity extends AppCompatActivity {
    /* TODO
    - Convert from MainActivity to LogInActivity (This shouldn't be where we set everything up
    - Create new main activity that does all the pre-auth things. This will be the start splash page
     */
    public static DatabaseHandler pickupdb; // Creates a new database handler which will send SQL requests
    private EditText passwordText;
    private EditText usernameText;
    private Authenticator authenticator;
    private static User current_user; // Pointer to current user. Initialized upon login or registration
    boolean freshPassword;
    boolean freshUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickupdb = new DatabaseHandler(this);
        passwordText = (EditText) findViewById(R.id.passText);
        usernameText = (EditText) findViewById(R.id.userText);
        authenticator = new Authenticator(pickupdb);
        freshPassword = true;
        freshUser = true;
    }

    public void logInButtonActivity(View v) {
        try {
            logIn();
            profile(v);
        } catch (InvalidParameterException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            passwordText.setText("");
        }

    }

    public void userTextOnClick(View v) {
        if (freshUser) {
            usernameText.setText("");
            freshUser = false;
        }
    }

    public void passTextOnClick(View v) {
        if (freshPassword) {
            passwordText.setText("");
            passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            freshPassword = false;
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
