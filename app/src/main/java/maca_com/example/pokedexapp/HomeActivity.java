package maca_com.example.pokedexapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends BaseActivity {

    EditText username,password,repassword;
    Button signup,signin;
    DBHelper DB;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        signin = (Button) findViewById(R.id.btnSignin);
        signup = (Button) findViewById(R.id.btnSignup);

        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                
                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(HomeActivity.this, "Porfavor completa todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    if (pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);
                        if (!checkuser){
                            Boolean insert = DB.insertData(user,pass);
                            if (insert){
                                Toast.makeText(HomeActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(HomeActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(HomeActivity.this, "Usuario ya existe intenta con otro", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(HomeActivity.this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
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
}
