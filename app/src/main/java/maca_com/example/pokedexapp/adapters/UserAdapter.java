package maca_com.example.pokedexapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import maca_com.example.pokedexapp.R;
import maca_com.example.pokedexapp.network.models.User;


public class UserAdapter extends ArrayAdapter<User> {

    List<User> objects;
    Context context;
    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.objects=objects;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user=objects.get(position);

        View view= LayoutInflater.from(context).inflate(R.layout.item_usurios, null);
        String nickname=user.getNickname();
        TextView tVNombre= view.findViewById(R.id.tvItemNombre);


        tVNombre.setText(nickname);


        return view;

    }
}
