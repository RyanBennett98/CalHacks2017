package calhacks.pickup;

import java.util.HashMap;

public class Database {
    private HashMap<String,User> _userMap;

    public Database() {
        _userMap = new HashMap<>();
    }

    public User getUser (String name) {
        return _userMap.get(name);
    }

    public void removeUser (String name) {
        _userMap.remove(name);
    }

    public HashMap<String, User> getUserMap() {
        return _userMap;
    }

    public User[] getUsers () {
        return (User[]) _userMap.values().toArray();
    }

    public String[] getNames () {
        return (String[]) _userMap.keySet().toArray();
    }
}
