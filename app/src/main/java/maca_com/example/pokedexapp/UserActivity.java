package maca_com.example.pokedexapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import maca_com.example.pokedexapp.adapters.UserAdapter;
import maca_com.example.pokedexapp.db.UsuarioDataSource;
import maca_com.example.pokedexapp.network.models.User;

public class UserActivity extends AppCompatActivity {

    ListView lvusuarios;
    List<User> usuarios;
    ArrayAdapter<User> adapter;
    UsuarioDataSource dataSource;
    public static final int REQUEST_CODE_AGREGAR_CONTACTO = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("Entrenadores Pokemon");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataSource = new UsuarioDataSource(this);
        dataSource.openDb();
        lvusuarios = findViewById(R.id.lvUsuarios);
        usuarios = dataSource.getUsers();
        dataSource.closeDb();

        adapter = new UserAdapter(this, R.layout.item_usurios, usuarios);
        lvusuarios.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id){

            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

/*  public void actualizarLista(){

        dataSource.openDb();
        usuarios= dataSource.getUsers();
        dataSource.closeDb();
        adapter= new UserAdapter(this, R.layout.item_usurios,usuarios);
        lvusuarios.setAdapter(adapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQUEST_CODE_AGREGAR_CONTACTO && resultCode==1) {

            actualizarLista();
        }
        if (requestCode==REQUEST_CODE_AGREGAR_CONTACTO && resultCode==2){

            actualizarLista();
        }


    }*/
}