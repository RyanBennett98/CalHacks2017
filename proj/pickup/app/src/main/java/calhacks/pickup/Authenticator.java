package calhacks.pickup;

/**
 * Created by ryan on 2017-10-07.
 */

public class Authenticator {
    public Authenticator(Database database) {
        _database = database;
    }

    public boolean authenticate(String username, String password) {
        if (!_database.hasUser(username)) {
            return false;
        } else {
            return (_database.getUser(username).getPasswordHash().equals(password.hashCode()));
        }
    }

    private Database _database;
}
