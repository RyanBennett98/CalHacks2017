package calhacks.pickup;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class User {
    static private final int LAST_PLAYED_WITH_SIZE = 5;

    public User(String username, String password) {
        _username = username;
        _password_hash = password.hashCode();
        _ratings_given = 0;
        _ratings_received = 0;
        _rating = -1;
        _friends = new HashSet<>();
        _last_played_with = new ArrayDeque<>();
    }

    public void updateRating(float new_rating) {
        _ratings_received += 1;
        if (_rating < 0) {
            _rating = new_rating;
        } else {
            _rating = ((_rating * (_ratings_received - 1)) + new_rating) / (_ratings_received);
        }
    }

    public void giveRating(float rating, User user) {
        user.updateRating(rating);
        _ratings_given += 1;
    }

    public int getPasswordHash() {
        return _password_hash;
    }

    public String getUsername() {
        return _username;
    }

    public double getRating() {
        return _rating;
    }

    public boolean equals(User user) {
        return (_username == user.getUsername()); // User names will be unique, so we can check equals by comparing them
    }

    public void addFriend(User user) {
        _friends.add(user);
    }

    public boolean removeFriend(User user) {
        if (!_friends.contains(user)) {
            return false;
        } else {
            _friends.remove(user);
            return true;
        }
    }

    public User[] getFriends() {
        return (User[]) _friends.toArray();
    }

    public void addPlayedWith(User user) {
        if (_last_played_with.size() >= LAST_PLAYED_WITH_SIZE) {
            _last_played_with.removeLast();
        }
        _last_played_with.addFirst(user);
    }

    public User[] getPlayedWith() {
        return (User[]) _last_played_with.toArray();
    }

    private HashSet<User> _friends;
    private ArrayDeque<User> _last_played_with;
    private float _rating;
    private int _ratings_received, _ratings_given, _password_hash;
    private String _username;
}
