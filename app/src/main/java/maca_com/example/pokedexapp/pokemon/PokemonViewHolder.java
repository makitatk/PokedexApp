package maca_com.example.pokedexapp.pokemon;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import maca_com.example.pokedexapp.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    TextView tvPokemonNombre;
    LinearLayout lnPokemon;
    ImageView ivPokemon;
    public PokemonViewHolder(@NonNull View v) {
        super(v);
        tvPokemonNombre = v.findViewById(R.id.tvPokemonName);
        lnPokemon = v.findViewById(R.id.lnPokemon);

    }
}
