package calhacks.pickup;

import java.util.HashSet;

/**
 * Created by ryan on 2017-10-07.
 */

public class Game {
    public Game(GameLocation location) {
        _team_one = new Team(location.getMaxSpotsAvailable()/2);
        _team_two = new Team(location.getMaxSpotsAvailable()/2);
        _location = location;
    }

    public boolean addPlayer(User user) {
        if (_team_one.isFull() && _team_two.isFull()) {
            return false;
        } else if (_team_one.isFull()){
            _team_two.addPlayer(user);
        } else if (_team_two.isFull()) {
            _team_one.addPlayer(user);
        } else {
            if (_team_one.getTeamRating() > _team_two.getTeamRating()) {
                _team_two.addPlayer(user);
            } else {
                _team_one.addPlayer(user);
            }
        }
        _location.addCurrentUser(user);
        return true;
    }

    public Team getTeamOne() {
        return _team_one;
    }

    public Team getTeamTwo() {
        return _team_two;
    }

    public GameLocation getLocation() {
        return _location;
    }

    private Team _team_one, _team_two;
    private GameLocation _location;
}
