package maca_com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import maca_com.example.pokedexapp.network.PokemonLoader;
import maca_com.example.pokedexapp.network.models.Pokemon;
import maca_com.example.pokedexapp.network.models.PokemonListResponse;
import maca_com.example.pokedexapp.pokemon.PokemonAdapter;
import maca_com.example.pokedexapp.utils.Constans;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final RecyclerView rvpokemonList = findViewById(R.id.rvpokemonList);



        Call<PokemonListResponse> call = loader.getPokemonList();
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                List<Pokemon> pokemonList = response.body().getPokemonList();

                PokemonAdapter adapter = new PokemonAdapter(pokemonList, PokedexActivity.this);
                rvpokemonList.setAdapter(adapter);
                rvpokemonList.setHasFixedSize(true);

                RecyclerView.LayoutManager manager = new LinearLayoutManager(PokedexActivity.this);
                rvpokemonList.setLayoutManager(manager);
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                Log.e(Constans.DEBUG_POKEMON,t.getMessage());
            }
        });
    }
}