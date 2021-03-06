package maca_com.example.pokedexapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import maca_com.example.pokedexapp.network.models.User;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "Login.db";
    public static final String Logtag = "Datasourse";
    SQLiteDatabase MyDB;
    SQLiteOpenHelper dbhelper;



    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = db.insert("users",null,contentValues);
        if (result==1)return false;
        else
            return true;

    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});


        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public List<User> getUsers(){
        List<User>usuarios= new ArrayList<>();
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String query="SELECT * FROM users";
        Cursor cursor= MyDB.rawQuery(query, null);

        Log.i(Logtag,"Filas retornadas " +cursor.getCount());

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){

                User user=new User();
                user.setNickname(cursor.getString(cursor.getColumnIndex("username")));
                usuarios.add(user);
            }

        }
        return usuarios;
    }

    public  void openDb(){
        MyDB=dbhelper.getWritableDatabase();
        Log.i(Logtag,"open db");
    }

    public void closeDb(){
        MyDB.close();
        Log.i(Logtag,"close db");
    }
}
