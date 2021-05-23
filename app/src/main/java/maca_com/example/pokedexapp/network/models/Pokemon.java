package maca_com.example.pokedexapp.network.models;

public class Pokemon {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public Sprite getSprite() {
        return sprite;
    }

    private Sprite sprite;

}
