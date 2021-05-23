package maca_com.example.pokedexapp.pokemon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import maca_com.example.pokedexapp.PokedexActivity;
import maca_com.example.pokedexapp.PokemonDetailActivity;
import maca_com.example.pokedexapp.R;
import maca_com.example.pokedexapp.network.models.Pokemon;
import maca_com.example.pokedexapp.utils.Constans;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    public PokemonAdapter(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    List<Pokemon> pokemonList;
    Context context;

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent,false);

        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder,  int i) {
        holder.tvPokemonNombre.setText(pokemonList.get(i).getName());
        holder.lnPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonId = pokemonList.get(holder.getAdapterPosition()).getName();

                Intent intent = new Intent(context, PokemonDetailActivity.class);
                intent.putExtra(Constans.EXTRA_POKEMON_ID,pokemonId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
