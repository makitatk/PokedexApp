package maca_com.example.pokedexapp.game;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import maca_com.example.pokedexapp.R;

public class GameViewHolder extends RecyclerView.ViewHolder {
    TextView tvGameTitle;

    public GameViewHolder(@NonNull View v) {
        super(v);
        tvGameTitle = v.findViewById(R.id.tvGameTitle);
    }
}
