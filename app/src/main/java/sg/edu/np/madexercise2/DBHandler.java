package sg.edu.np.madexercise2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    Boolean f;

    //Database Info
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";

    //Table Name
    public static final String TABLE_USERS = "users";

    //Table Columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_FOLLOWED = "followed";

    public DBHandler(@Nullable Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                //COLUMN_ID + " INTEGER,"+
                COLUMN_NAME + " TEXT," +
                COLUMN_DESC + " TEXT," +
                COLUMN_FOLLOWED + " INTEGER" +
                ")";

        db.execSQL(CREATE_USERS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

    }

    public void insertUser(String name, String desc, Integer id, Integer followed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESC, desc);
        values.put(COLUMN_FOLLOWED, followed);


        db.insert(TABLE_USERS, null, values);
        //db.close();

    }

    public User updateUserOnClick(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_NAME + " = \"" + userName + "\"", null);

        cursor.moveToFirst();

        if (cursor.getInt(3) == 1) {
            f = false;
        } else if (cursor.getInt(3) == 0) {
            f = true;
        }

        User user = new User(cursor.getString(1), cursor.getString(2), cursor.getInt(0), f);
        return user;
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        ArrayList<User> userList = new ArrayList<>();

        if (cursorUsers.moveToFirst()) {
            do {
                if (cursorUsers.getInt(3) == 1) {
                    f = false;
                } else if (cursorUsers.getInt(3) == 0) {
                    f = true;
                }
                userList.add(new User(cursorUsers.getString(1), cursorUsers.getString(2), cursorUsers.getInt(0), f));
            }
            while (cursorUsers.moveToNext());
        }
        cursorUsers.close();
        return userList;

    }

//    public User findUsers(String name){
//        // database for reading our database.
//        SQLiteDatabase db = this.getWritableDatabase();
//
//
//        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
//                + COLUMN_NAME
//                + " = \"" + name + "\"";
//
//        // on below line we are creating a cursor with query to read data from database.
//        Cursor cursor = db.rawQuery(query, null);
//
//        // on below line we are creating a new array list.
//        User user = new User();
//
//        if (cursor.moveToFirst()) {
//            user.setID(Integer.parseInt(cursor.getString(0)));
//            user.setName(cursor.getString(1));
//            user.setDescription(cursor.getString(2));
//            user.setFollowed(Integer.parseInt(cursor.getString(3)));
//
//            cursor.close();
//        } else {
//            user = null;
//        }
//        db.close();
//        return user;}

    public void updateUser(User user) {

        Boolean x = user.followed;
        Integer num = x ? 0 : 1;
        Integer y = user.id;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = " UPDATE " + TABLE_USERS + " SET " + COLUMN_FOLLOWED + " = " + num + " WHERE " + COLUMN_ID + " = " + y;
        db.execSQL(query);


    }

}
