package maca_com.example.pokedexapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import maca_com.example.pokedexapp.db.UsuarioDataSource;
import maca_com.example.pokedexapp.network.models.User;

public class HomeActivity extends BaseActivity {

    EditText username,password,repassword;
    Button signup,signin;
    UsuarioDataSource DB;
    int id=0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        signin = (Button) findViewById(R.id.btnSignin);
        signup = (Button) findViewById(R.id.btnSignup);

        DB = new UsuarioDataSource(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuario();

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void guardarUsuario(){

        String user = username.getText().toString();
        String pass = password.getText().toString();
        String repass = repassword.getText().toString();

        DB.openDb();
        if(user.isEmpty() || pass.isEmpty() || repass.isEmpty()){

            Toast.makeText(this,"Favor llenar todos los campos",Toast.LENGTH_LONG).show();
            Log.i("ACA", "Vacio");

        }else if(DB.checkUsername(user)){
            Toast.makeText(this,"Usuario ya existe",Toast.LENGTH_LONG).show();
            Log.i("ACA", "Usuario existe");

        }else if(!pass.equals(repass)){

            Toast.makeText(this,"No coinciden las contraseñas",Toast.LENGTH_LONG).show();
            Log.i("ACA", "Claves no coinciden");
            Log.i("ACA", "PASS "+ pass);
            Log.i("ACA", "REPASS "+ repass);
        }else
        if (pass.equals(repass)){

            if (crearContacto(user, pass)!=-1){

                Toast.makeText(this, "Usuario Guardado",Toast.LENGTH_SHORT).show();
                setResult(1);
                Log.i("ACA", "Usuario creado");


            }
        }

        DB.closeDb();
    }
    public long crearContacto(String nombre, String password){

        User contacto= new User();
        contacto.setNickname(nombre);
        contacto.setPassword(password);
        contacto=DB.insertarUsuario(contacto);
        return contacto.getId();
    }
}
