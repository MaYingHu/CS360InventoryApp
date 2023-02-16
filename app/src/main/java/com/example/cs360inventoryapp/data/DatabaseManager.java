package com.example.cs360inventoryapp.data;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;

public class DatabaseManager extends SQLiteOpenHelper {

    private static DatabaseManager instance;
    private static final String DATABASE_NAME = "data.db";
    private static final int VERSION = 1;

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + LoginDetailsTable.TABLE + " (" +
                LoginDetailsTable.COL_ID + " integer primary key autoincrement, " +
                LoginDetailsTable.COL_USERNAME + " text, " +
                LoginDetailsTable.COL_PASSWORD + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + LoginDetailsTable.TABLE);
        onCreate(db);
    }

    public boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;

        SQLiteDatabase db = getReadableDatabase();

        String sql = "Select * from " + LoginDetailsTable.TABLE +
                " WHERE " + LoginDetailsTable.COL_USERNAME + " = ? AND " +
                LoginDetailsTable.COL_PASSWORD + " = ? ";

        Cursor cursor = db.rawQuery(sql, new String[]{username, password});

        if (cursor.moveToFirst()) {
            isAuthenticated = true;
        }

        return isAuthenticated;
    }

    private static final class LoginDetailsTable {
        private static final String TABLE = "loginDetails";
        private static final String COL_ID = "_id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    public long addUser(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LoginDetailsTable.COL_USERNAME, username);
        values.put(LoginDetailsTable.COL_PASSWORD, password);

        long userId = db.insert(LoginDetailsTable.TABLE, null, values);

        return userId;
    }

    public boolean deleteUser(long id) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(LoginDetailsTable.TABLE, LoginDetailsTable.COL_ID + " =?",
                new String[] { Long.toString(id) });
        return rowsDeleted > 0;
    }
}