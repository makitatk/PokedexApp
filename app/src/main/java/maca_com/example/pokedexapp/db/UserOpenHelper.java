package maca_com.example.pokedexapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pokedex.db";
    public static final int VERSION = 1;
    public static final String tag = "OpenHelper";

    public  String CREATE_TABLE_USUARIO="CREATE TABLE usuario("+ "id INTEGER PRIMARY KEY autoincrement," +
            "nickname TEXT," +
            "password TEXT);";
    public String INSERT_USUARIO1= "INSERT INTO usuario (nickname, password)" + "VALUES ('Makita', '12345')";
    public String INSERT_USUARIO2= "INSERT INTO usuario (nickname, password)" + "VALUES ('dennys', 'lalo123')";

    public UserOpenHelper (@Nullable Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        Log.i(tag, "se creo la lista de usuarios");
        db.execSQL(INSERT_USUARIO1);
        Log.i("OpenHelper", "se inserto contacto 1");
        db.execSQL(INSERT_USUARIO2);
        Log.i("OpenHelper", "se inserto contacto 2");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
