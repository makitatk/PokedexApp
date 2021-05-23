package maca_com.example.pokedexapp.game;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import maca_com.example.pokedexapp.R;
import maca_com.example.pokedexapp.network.models.Games;
import maca_com.example.pokedexapp.pokemon.PokemonViewHolder;

public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    public GameAdapter(List<Games> games) {
        this.games = games;
    }

    private List<Games> games;
    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent,false);

        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int i) {
        holder.tvGameTitle.setText(games.get(i).getVersion().getName());

    }

    @Override
    public int getItemCount() {
        return games.size();
    }
}
