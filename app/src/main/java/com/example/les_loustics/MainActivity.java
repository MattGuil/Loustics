package com.example.les_loustics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView textViewAucunCompte;

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

        // TextView à afficher si aucun compte n'est enregitré
        textViewAucunCompte = findViewById(R.id.aucun_compte);

        // Ajouter un événement au bouton d'ajout
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        // Selectionner un compte pour jouer
        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Récupération du user cliqué à l'aide de l'adapter
                User user = adapter.getItem(position);

                jouerAvecCompte(user);
            }
        });

        class DeleteUser extends AsyncTask<Void, Void, User> {

            private User user;

            public DeleteUser(User user) {
                this.user = user;
            }

            @Override
            protected User doInBackground(Void... voids) {
                mDb.getAppDatabase().userDao().delete(user);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                Toast.makeText(getApplicationContext(), "Compte supprimé", Toast.LENGTH_LONG).show();
            }
        }

        // Supprimer un compte de la liste
        listUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Veux-tu supprimer le compte de " + adapter.getItem(position).getPrenom() + " ?");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        DeleteUser du = new DeleteUser(adapter.getItem(position));
                        du.execute();
                        finish();
                        startActivity(getIntent());
                    }
                });
                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
                return false;
            }
        });
    }

    public void jouerAvecCompte(User user) {
        ((MyApplication) this.getApplication()).setCurrentUser(user);
        Intent intent = new Intent(MainActivity.this, GameSelectionActivity.class);
        startActivity(intent);
    }

    public void jouerSansCompte(View view) {
        ((MyApplication) this.getApplication()).setCurrentUser(new User());
        Intent intent = new Intent(MainActivity.this, GameSelectionActivity.class);
        startActivity(intent);
    }

    private void getUsers() {
        // Classe asynchrone permettant de récupérer des utilisateurs et de mettre à jour la listView de l'activité
        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            List<User> userList;

            @Override
            protected List<User> doInBackground(Void... voids) {
                userList = mDb.getAppDatabase().userDao().getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);

                // Mettre à jour l'adapter avec la liste des utilisateurs
                adapter.clear();
                adapter.addAll(users);

                // Afficher un message si il n'y a auncun utilisateur enregistré
                if(users.isEmpty()) {
                    textViewAucunCompte.setVisibility(View.VISIBLE);
                } else {
                    textViewAucunCompte.setVisibility(View.GONE);
                }

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