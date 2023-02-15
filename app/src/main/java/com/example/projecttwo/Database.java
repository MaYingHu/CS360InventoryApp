package com.example.projecttwo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventoryApp.db";
    private static final int VERSION = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

        private static final class LoginDetailsTable {
        private static final String TABLE = "loginDetails";
        private static final String COL_ID = "_id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LoginDetailsTable.TABLE + " (" +
                LoginDetailsTable.COL_ID + " integer primary key autoincrement, " +
                LoginDetailsTable.COL_USERNAME + " text, " +
                LoginDetailsTable.COL_PASSWORD + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + LoginDetailsTable.TABLE);
        onCreate(db);
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

