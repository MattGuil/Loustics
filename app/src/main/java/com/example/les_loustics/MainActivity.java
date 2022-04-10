package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.les_loustics.db.DatabaseClient;
import com.example.les_loustics.db.User;
import com.example.les_loustics.db.UsersAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 0;

    // DATA
    private DatabaseClient mDb;
    private UsersAdapter adapter;

    // VIEW
    private Button buttonAdd;
    private ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        listUser = findViewById(R.id.listUser);
        buttonAdd = findViewById(R.id.btn_creer_un_compte);

        // Lier l'adapter au listView
        adapter = new UsersAdapter(this, new ArrayList<User>());
        listUser.setAdapter(adapter);

        // Ajouter un événement au bouton d'ajout
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });
    }

    public void jouerSansCompte(View view) {
        Intent intent = new Intent(MainActivity.this, GameSelectionActivity.class);
        intent.putExtra("prenom_key", "Anonyme");
        startActivity(intent);
    }

    private void getUsers() {
        // Classe asynchrone permettant de récupérer des utilisateurs et de mettre à jour la listView de l'activité
        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = mDb.getAppDatabase().userDao().getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);

                // Mettre à jour l'adapter avec la liste des utilisateurs
                adapter.clear();
                adapter.addAll(users);

                // Notifier l'adapter des changements dans la source
                adapter.notifyDataSetChanged();
            }
        }

        // IMPORTANT : Bien penser à éxecuter la demande asynchrone
        // Création d'un objet de type GetUsers et éxecution de la demande asynchrone
        GetUsers gu = new GetUsers();
        gu.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Mise à jour des users
        getUsers();
    }
}