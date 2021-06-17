package maca_com.example.pokedexapp.network.models;

public class User {

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    long Id;

    String nickname;

    String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
