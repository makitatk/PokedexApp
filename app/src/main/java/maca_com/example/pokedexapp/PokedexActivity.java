package maca_com.example.pokedexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import maca_com.example.pokedexapp.network.PokemonApi;
import maca_com.example.pokedexapp.network.PokemonLoader;
import maca_com.example.pokedexapp.network.models.Pokemon;
import maca_com.example.pokedexapp.network.models.PokemonListResponse;
import maca_com.example.pokedexapp.network.models.PokemonRespuesta;
import maca_com.example.pokedexapp.pokemon.PokemonAdapter;
import maca_com.example.pokedexapp.utils.Constans;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexActivity extends BaseActivity {

    private int offset;
    private PokemonAdapter pokemonAdapter;
    private List<Pokemon> pokemonList;
    Retrofit retrofit;
    private boolean aptoParaCargar;
    RecyclerView rvpokemonList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvpokemonList = findViewById(R.id.rvpokemonList);

        pokemonAdapter = new PokemonAdapter(this);
        rvpokemonList.setAdapter(pokemonAdapter);
        rvpokemonList.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvpokemonList.setLayoutManager(layoutManager);

        rvpokemonList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(Constans.DEBUG_POKEMON, " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });
       /* Call<PokemonListResponse> call = loader.getPokemonList();
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
        });*/
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);

    }
    private void obtenerDatos(int offset) {
        PokemonApi service = retrofit.create(PokemonApi.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon(20,offset);

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {

                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();

                    pokemonAdapter.adicionarListaPokemon(listaPokemon);

                } else {
                    Log.e(Constans.DEBUG_POKEMON, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(Constans.DEBUG_POKEMON, " onFailure: " + t.getMessage());
            }
        });
    }
}