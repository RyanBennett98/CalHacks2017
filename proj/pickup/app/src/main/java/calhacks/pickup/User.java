package calhacks.pickup;

public class User {
    public User(String username, String password_hash, Database d) {
        _username = username;
        _password_hash = password_hash;
        _ratings_given = 0;
        _ratings_received = 0;
        _rating = -1;
        
        d.addTo(_username,this);
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

    public String getUsername() {
        return _username;
    }

    public double getRating() {
        return _rating;
    }

    public boolean equals(User user) {
        return (_username == user.getUsername()); // User names will be unique, so we can check equals by comparing them
    }


    private float _rating;
    private int _ratings_received, _ratings_given;
    private String _username, _password_hash;
}
