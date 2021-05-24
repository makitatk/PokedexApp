package maca_com.example.pokedexapp.pokemon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import maca_com.example.pokedexapp.PokedexActivity;
import maca_com.example.pokedexapp.PokemonDetailActivity;
import maca_com.example.pokedexapp.R;
import maca_com.example.pokedexapp.network.models.Pokemon;
import maca_com.example.pokedexapp.utils.Constans;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    public PokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    private ArrayList<Pokemon> dataset;
    private Context context;

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent,false);

        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder,  int i) {
        Pokemon p = dataset.get(i);
        holder.tvPokemonNombre.setText(dataset.get(i).getName());

        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivPokemon);

        holder.lnPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonId = dataset.get(holder.getAdapterPosition()).getName();

                Intent intent = new Intent(context, PokemonDetailActivity.class);
                intent.putExtra(Constans.EXTRA_POKEMON_ID,pokemonId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }
}
