package maca_com.example.pokedexapp.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonByIdResponse {
    private List<Abilities> abilities;
    @SerializedName("base_experience")
    private  int baseExperience;
    @SerializedName("game_indices")
    private List<Games> games;
    private int id;
    private String name;
    private Sprite sprites;

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public List<Games> getGames() {
        return games;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sprite getSprites() {
        return sprites;
    }
}
