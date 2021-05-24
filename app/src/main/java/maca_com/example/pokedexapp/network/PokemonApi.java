package maca_com.example.pokedexapp.network;

import maca_com.example.pokedexapp.network.models.PokemonByIdResponse;
import maca_com.example.pokedexapp.network.models.PokemonListResponse;
import maca_com.example.pokedexapp.network.models.PokemonRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonApi {
    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList();

    @GET("pokemon/{id}")
    Call<PokemonByIdResponse> getPokemonById(@Path("id") String id);

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset")int offset);
}
