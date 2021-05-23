package maca_com.example.pokedexapp.network.models;

import android.content.SharedPreferences;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonListResponse {
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    private int count;
    private String next;
    private String previous;
    @SerializedName("results")
    private List<Pokemon>pokemonList;

    public Sprite getSprites() {
        return sprites;
    }

    private  Sprite sprites;


}
