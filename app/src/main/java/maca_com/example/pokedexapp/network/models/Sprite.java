package maca_com.example.pokedexapp.network.models;

import com.google.gson.annotations.SerializedName;

public class Sprite {
    public String getImage() {
        return image;
    }

    @SerializedName("front_default")
    private String image;
}
