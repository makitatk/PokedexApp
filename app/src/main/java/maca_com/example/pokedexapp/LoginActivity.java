package maca_com.example.pokedexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import maca_com.example.pokedexapp.db.UsuarioDataSource;

public class LoginActivity extends AppCompatActivity {

    EditText username1,password1;
    Button btnlogin;
    UsuarioDataSource DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username1 = (EditText) findViewById(R.id.txtusername1);
        password1 = (EditText) findViewById(R.id.txtpassword1);

        btnlogin = (Button) findViewById(R.id.btnlogin);
        DB = new UsuarioDataSource(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username1.getText().toString();
                String pass = password1.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Completa los campos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean check = DB.checkUsernamePassword(user,pass);
                    if (check) {
                        Toast.makeText(LoginActivity.this, "Iniciando sesion", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PokedexActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Credenciales invalidas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

