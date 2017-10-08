package calhacks.pickup;

/**
 * Created by ryan on 2017-10-07.
 */

public class Authenticator {
    public Authenticator(DatabaseHandler db) {
        _database = db;
    }

    public boolean authenticate(String username, String password) {
        User user = _database.getUser(username);
        if (user == null) {
            return false;
        } else {
            return (user.getPasswordHash() == (password.hashCode()));
        }
    }

    private DatabaseHandler _database;
}
