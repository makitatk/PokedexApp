package maca_com.example.pokedexapp.network.models;

import com.google.gson.annotations.SerializedName;

public class Games {
    public int getGameIndex() {
        return GameIndex;
    }

    public Version getVersion() {
        return version;
    }

    @SerializedName("game_index")
    private int GameIndex;
    private Version version;

   public class Version {
        private  String name;
        private String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
