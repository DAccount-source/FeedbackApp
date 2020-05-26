package com.akanksha.feedbackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "feedback.db";
    public static final String LOGIN_TABLE_NAME = "login_table";
    public static final String SIGNUP_TABLE_NAME = "signup_table";
    public static final String LOGIN_EMAIL = "login_email";
    public static final String LOGIN_PASSWORD = "login_password";
    public static final String SIGNUP_ID = "signup_id";
    public static final String SIGNUP_FULLNAME = "signup_fullname";
    public static final String SIGNUP_USERNAME = "signup_username";
    public static final String SIGNUP_PHONENO = "signup_phoneno";
    public static final String SIGNUP_EMAIL = "signup_email";
    public static final String SIGNUP_PASSWORD = "signup_password";
    public static final String SIGNUP_CONFIRM_PASSWORD = "signup_confirm_password";

    static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + SIGNUP_TABLE_NAME + "("
                + SIGNUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SIGNUP_FULLNAME + " TEXT NOT NULL,"
                + SIGNUP_USERNAME + " TEXT NOT NULL,"
                + SIGNUP_PHONENO + " INTEGER NOT NULL,"
                + SIGNUP_EMAIL + " TEXT NOT NULL,"
                + SIGNUP_PASSWORD + " TEXT NOT NULL,"
                + SIGNUP_CONFIRM_PASSWORD + " TEXT NOT NULL);");
        db.execSQL("create table " + LOGIN_TABLE_NAME + "("
                + LOGIN_EMAIL + " TEXT NOT NULL,"
                + LOGIN_PASSWORD + " TEXT NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SIGNUP_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE_NAME);
        onCreate(db);
    }

    public boolean LoginInsertData(String EmailID, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOGIN_EMAIL, EmailID);
        contentValues.put(LOGIN_PASSWORD, Password);
        long result = db.insert(LOGIN_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean SignupInsertData(String fullname, String username, String phoneno, String email, String password, String confirmPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SIGNUP_FULLNAME, fullname);
        contentValues.put(SIGNUP_USERNAME, username);
        contentValues.put(SIGNUP_PHONENO, phoneno);
        contentValues.put(SIGNUP_EMAIL, email);
        contentValues.put(SIGNUP_PASSWORD, password);
        contentValues.put(SIGNUP_CONFIRM_PASSWORD, confirmPassword);
        long result = db.insert(SIGNUP_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public String getSinlgeEntry(String useremail) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(SIGNUP_TABLE_NAME,null," signup_email=?",new String[]{useremail},null,null,null);

        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
            cursor.moveToFirst();
            String ResultPassword = cursor.getString(cursor.getColumnIndex("signup_password"));
            cursor.close();
            return ResultPassword;

    }
}
