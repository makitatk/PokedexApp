package maca_com.example.pokedexapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import maca_com.example.pokedexapp.game.GameAdapter;
import maca_com.example.pokedexapp.network.PokemonLoader;
import maca_com.example.pokedexapp.network.models.Abilities;
import maca_com.example.pokedexapp.network.models.Ability;
import maca_com.example.pokedexapp.network.models.PokemonByIdResponse;
import maca_com.example.pokedexapp.pokemon.PokemonAdapter;
import maca_com.example.pokedexapp.utils.Constans;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailActivity extends BaseActivity {

    TextView tvPokemonTitle, tvPokeExperience,tvPokeAbilities;
    ImageView ivPokemonSprite;
    RecyclerView rvPokeGames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        tvPokemonTitle = findViewById(R.id.tvPokemonTitle);
        tvPokeExperience = findViewById(R.id.tvPokeExperience);
        tvPokeAbilities = findViewById(R.id.tvPokeAbilities);
        ivPokemonSprite = findViewById(R.id.ivPokemonSprite);
        rvPokeGames = findViewById(R.id.rvPokeGames);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String pokemonId= getIntent().getStringExtra(Constans.EXTRA_POKEMON_ID);

        Call<PokemonByIdResponse> call= loader.getPokemonById(pokemonId);
        showProgress();
        call.enqueue(new Callback<PokemonByIdResponse>() {
            @Override
            public void onResponse(Call<PokemonByIdResponse> call, Response<PokemonByIdResponse> response) {
                hideProgress();
                tvPokemonTitle.setText(response.body().getId()+"- "+   response.body().getName());
                tvPokeExperience.setText("XP Base: "+response.body().getBaseExperience());

                Glide.with(PokemonDetailActivity.this).load(response.body().getSprites().getImage()).into(ivPokemonSprite);

                List<Ability> abilityList = new ArrayList<>();

                StringBuilder sb = new StringBuilder();

                for(Abilities abilities : response.body().getAbilities()){

                    abilityList.add(abilities.getAbility());
                }

                for(Ability ability: abilityList){
                    sb.append(ability.getName()+"\n");
                }

                tvPokeAbilities.setText(sb.toString());

                GameAdapter adapter = new GameAdapter(response.body().getGames());
                rvPokeGames.setAdapter(adapter);
                rvPokeGames.setHasFixedSize(true);

                RecyclerView.LayoutManager manager = new LinearLayoutManager(PokemonDetailActivity.this);
                rvPokeGames.setLayoutManager(manager);
            }

            @Override
            public void onFailure(Call<PokemonByIdResponse> call, Throwable t) {
                hideProgress();
                Log.e(Constans.DEBUG_POKEMON,t.getMessage());
            }




        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
             default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
