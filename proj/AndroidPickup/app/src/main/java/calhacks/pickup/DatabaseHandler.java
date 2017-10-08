package calhacks.pickup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 2017-10-08.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pickupdb";
    private static final String TABLE_USERS = "users";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD_HASH = "password_hash";
    private static final String KEY_RATING = "rating";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_USERNAME + " TEXT PRIMARY KEY," +
                KEY_PASSWORD_HASH + " INTEGER," + KEY_RATING + " DOUBLE" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD_HASH, user.getPasswordHash());
        values.put(KEY_RATING, user.getRating());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[] {KEY_USERNAME, KEY_PASSWORD_HASH, KEY_RATING},
                KEY_USERNAME + "=?", new String[] {username}, null, null, null, null);
        if (cursor.moveToFirst()){
            User foundUser = new User(cursor.getString(0), cursor.getInt(1), cursor.getDouble(2));
            return foundUser;
        } else {
            return User.EMPTY_USER;
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0), cursor.getInt(1), cursor.getDouble(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public int updateUserRating(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RATING, user.getRating());
        return db.update(TABLE_USERS, values, KEY_USERNAME + " =?", new String[] {user.getUsername()});
    }
}
