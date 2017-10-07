package calhacks.pickup;

import java.util.HashMap;

public class Database {
    private HashMap<String, User> _userMap;

    public Database() {
        _userMap = new HashMap<>();
    }

    public void addTo(String name, User u) {
        _userMap.put(name, u);
    }

    public boolean hasUser(String name) {
        return _userMap.keySet().contains(name);
    }

    public void removeUser (String name) {
        _userMap.remove(name);
    }
        g
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
