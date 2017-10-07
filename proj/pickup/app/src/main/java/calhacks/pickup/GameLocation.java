package calhacks.pickup;

/**
 * Created by ryan on 2017-10-07.
 */

public abstract class GameLocation {
    public boolean inUse() {
        return _in_use;
    }

    public boolean hasSpotsAvailable() {
        return _spots_available != 0;
    }

    public int getSpotsAvailable() {
        return _spots_available;
    }

    public int getMaxSpotsAvailable() {
        return _max_spots_available;
    }

    public String getAddress() {
        return _address;
    }

    public abstract boolean addCurrentUser(User user);

    public abstract boolean removeCurrentUser(User user);

    public abstract User[] getCurrentUsers();

    private boolean _in_use;
    private int _spots_available, _max_spots_available;
    private String _address;
}
