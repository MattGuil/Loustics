package com.example.les_loustics.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import com.example.les_loustics.R;
import com.example.les_loustics.db.User;

public class UsersAdapter extends ArrayAdapter<User> {

    public UsersAdapter(Context mCtx, List<User> userList) {
        super(mCtx, R.layout.template_user, userList);
    }

    /**
     * Remplit une ligne de la listView avec les informations associée
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user = getItem(position);

        // Charge le template XML
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.template_user, parent, false);

        // Récupération des objets graphiques dans le template
        TextView textViewNom = (TextView) rowView.findViewById(R.id.textViewNom);
        TextView textViewPrenom = (TextView) rowView.findViewById(R.id.textViewPrenom);

        textViewNom.setText(user.getNom());
        textViewPrenom.setText(user.getPrenom());

        return rowView;
    }

}
