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

    private boolean _in_use;
    private int _spots_available;
    private String _adress;
}
