package maca_com.example.pokedexapp;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import maca_com.example.pokedexapp.network.PokemonLoader;

public class BaseActivity extends AppCompatActivity {
  public  PokemonLoader loader;
  private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loader= new PokemonLoader();
        progress = new ProgressDialog(this);

    }

    public   void showProgress(){

        progress.setCancelable(false);
        progress.setMessage("Cargando...");
        progress.show();
    }

    public void hideProgress(){

        if(progress.isShowing()){
            progress.dismiss();
        }
    }
}
