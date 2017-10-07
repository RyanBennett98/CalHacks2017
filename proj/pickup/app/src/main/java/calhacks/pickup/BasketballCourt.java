package calhacks.pickup;

import java.util.HashSet;

/**
 * Created by ryan on 2017-10-07.
 */

public class BasketballCourt extends GameLocation {
    public enum GameType {
        THREE_ON_THREE,
        FIVE_ON_FIVE
    }

    @Override
    public boolean addCurrentUser(User user) {
        if (_spots_available == 0 || _current_users.contains(user)) {
            return false;
        } else {
            if (!_in_use) {
                _in_use = true;
            }
            _current_users.add(user);
            _spots_available -= 1;
            return true;
        }
    }

    @Override
    public boolean removeCurrentUser(User user) {
        if (!_current_users.contains(user)) {
            return false;
        } else {
            _current_users.remove(user);
            _spots_available += 1;
            if (_spots_available == _max_spots_available && _in_use) {
                _in_use = false;
            }
            return true;
        }
    }

    @Override
    public User[] getCurrentUsers() {
        return (User[]) _current_users.toArray();
    }

    public BasketballCourt(GameType type, String address) {
        switch (type) {
            case THREE_ON_THREE:
                _spots_available = _max_spots_available = 6;
            case FIVE_ON_FIVE:
                _spots_available = _max_spots_available = 10;
            default:
                _spots_available = _max_spots_available = 10;
        }
        _address = address;
        _in_use = false;
        _current_users = new HashSet<>();
    }

    private boolean _in_use;
    private int _spots_available, _max_spots_available;
    private String _address;
    private HashSet<User> _current_users;
}
