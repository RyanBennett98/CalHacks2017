package calhacks.pickup;

import java.util.HashMap;

public class Database {
    private HashMap<String,User> userMap = new HashMap<>();

    public Database() {
    }

    public User getUser (String name) {
        return userMap.get(name);
    }

    public void removeFrom (String name) {
        userMap.remove(name);
    }

    public HashMap<String, User> getUserMap () {
        return userMap;
    }

    public User[] getUsers () {
        return (User[]) userMap.values().toArray();
    }

    public String[] getNames () {
        return userMap.keys().toArray();
    }


}