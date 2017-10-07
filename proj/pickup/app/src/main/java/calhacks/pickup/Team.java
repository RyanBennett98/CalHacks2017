package calhacks.pickup;

import java.util.HashSet;

/**
 * Created by ryan on 2017-10-07.
 */

public class Team {
    public Team(int size){
        _size = size;
        _players = new HashSet<>();
        _team_rating = 0;
    }

    public boolean addPlayer(User user) {
        if (_players.size() >= _size || _players.contains(user)) {
            return false;
        } else {
            _players.add(user);
            _team_rating += user.getRating();
            return true;
        }
    }

    public boolean removePlayer(User user) {
        if (!_players.contains(user)) {
            return false;
        } else {
            _players.remove(user);
            _team_rating -= user.getRating();
            return true;
        }
    }

    public float getTeamRating() {
        return _team_rating;
    }

    public int getSize() {
        return _size;
    }

    public User[] getPlayers() {
        return (User[]) _players.toArray();
    }

    public boolean isFull() {
        return (_players.size() == _size);
    }

    public void clearTeam() {
        _players.clear();
        _team_rating = 0;
    }

    private int _size;
    private float _team_rating;
    private HashSet<User> _players;
}
