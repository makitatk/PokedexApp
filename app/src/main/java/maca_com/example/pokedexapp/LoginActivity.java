package maca_com.example.pokedexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;
    DBHelper DB;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);

        btnlogin = (Button) findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Completa los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = DB.checkUsernamePassword(user,pass);
                    if (checkuserpass == true) {
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

