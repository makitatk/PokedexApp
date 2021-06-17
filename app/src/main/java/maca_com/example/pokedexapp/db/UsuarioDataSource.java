package maca_com.example.pokedexapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import maca_com.example.pokedexapp.network.models.User;

public class UsuarioDataSource {
    public static final String Logtag = "Datasourse";
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    public UsuarioDataSource(Context context) {
        dbhelper= new UserOpenHelper(context);

    }
    public  void openDb(){
        database=dbhelper.getWritableDatabase();
        Log.i(Logtag,"open db");
    }

    public void closeDb(){
        dbhelper.close();
        Log.i(Logtag,"close db");
    }

    public List<User> getUsers(){
        List<User>usuarios= new ArrayList<>();

        String query="SELECT * FROM usuario";
        Cursor cursor= database.rawQuery(query, null);

        Log.i(Logtag,"Filas retornadas " +cursor.getCount());

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){

                User user=new User();
                user.setNickname(cursor.getString(cursor.getColumnIndex("nickname")));
                usuarios.add(user);
            }

        }
        return usuarios;
    }

    public User insertarUsuario(User contacto){
        ContentValues values= new ContentValues();
        values.put("nickname", contacto.getNickname());
        values.put("password", contacto.getPassword());

        long idcontacto= database.insert("usuario", null, values);
        contacto.setId(idcontacto);

        return contacto;

    }

    public Boolean checkUsernamePassword(String username, String password){
        openDb();
        Cursor cursor = database.rawQuery("Select * from usuario where nickname = ? and password = ?", new String[] {username,password});


        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUsername(String username){
        openDb();
        Cursor cursor = database.rawQuery("Select * from usuario where nickname = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
