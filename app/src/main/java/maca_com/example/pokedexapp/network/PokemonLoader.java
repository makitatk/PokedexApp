package maca_com.example.pokedexapp.network;

import maca_com.example.pokedexapp.network.models.PokemonByIdResponse;
import maca_com.example.pokedexapp.network.models.PokemonListResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonLoader implements PokemonApi {

    PokemonApi pokemonApi;
    final String URL_BASE="https://pokeapi.co/api/v2/";

    public PokemonLoader() {
        Retrofit retrofit= new Retrofit.Builder()
                                .baseUrl(URL_BASE)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        pokemonApi = retrofit.create(PokemonApi.class);
    }

    @Override
    public Call<PokemonListResponse> getPokemonList() {
        return pokemonApi.getPokemonList();
    }

    @Override
    public Call<PokemonByIdResponse> getPokemonById(String id) {
        return pokemonApi.getPokemonById(id);
    }

    @Override
    public Call<PokemonListResponse> obtenerListaPokemon(String limit, String offset) {
        return pokemonApi.getPokemonList();
    }

}
