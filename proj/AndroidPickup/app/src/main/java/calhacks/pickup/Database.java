package calhacks.pickup;
import android.content.res.Resources;

import com.example.benjaminta.androidpickup.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Database {
    private HashMap<String, User> _userMap;

    public Database(InputStream input) {
        _userMap = new HashMap<>();
        String jsonString = getJsonString(input);
        populateDatabase(jsonString);
    }

    public void addTo(String name, User u) {
        _userMap.put(name, u);
    }

    private String getJsonString(InputStream input) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            reader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    private void populateDatabase(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray users = jsonObject.getJSONArray("users");
            for (int i = 0; i < users.length(); i++) {
                JSONObject u = users.getJSONObject(i);
                String username = u.getString("username");
                int password_hash = u.getInt("password_hash");
                double rating = u.getDouble("rating");
                _userMap.put(username, new User(username, password_hash, rating));
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean hasUser(String name) {
        return _userMap.keySet().contains(name);
    }

    public void removeUser (String name) {
        _userMap.remove(name);
    }

    public HashMap<String, User> getMap(){
        return _userMap;
    }

    public String[] getNames () {
        return (String[]) _userMap.keySet().toArray();
    }

    public User getUser (String name) {
        return _userMap.get(name);
    }

    public User[] getUsers () {
        return (User[]) _userMap.values().toArray();
    }

    public HashMap<String, User> getUserMap() {
        return _userMap;
    }
}
