package com.example.benjaminta.androidpickup;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;

import calhacks.pickup.Authenticator;
import calhacks.pickup.Database;
import calhacks.pickup.DatabaseHandler;
import calhacks.pickup.User;

public class MainActivity extends AppCompatActivity {
    //public static Database pickupdb;
    public static DatabaseHandler pickupdb;
    private EditText passwordText;
    private EditText usernameText;
    private Authenticator authenticator;
    private static User current_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickupdb = new DatabaseHandler(this);
        /*File userFile = new File(getFilesDir(), "users.json");
        FileInputStream input = null;
        try {
            if (!userFile.exists()) {
                userFile.createNewFile();
            }
            input = new FileInputStream(userFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        pickupdb = new Database(input);*/
        //pickupdb = new Database(getResources().openRawResource(R.raw.users));
        passwordText = (EditText) findViewById(R.id.passText);
        usernameText = (EditText) findViewById(R.id.userText);
        authenticator = new Authenticator(pickupdb);

        User temp = new User("bob", "pass");
        pickupdb.addUser(temp);
        pickupdb.addUser(new User("steph30", "password"));
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

    public static void addUser(User user) {
        pickupdb.addUser(user);
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
