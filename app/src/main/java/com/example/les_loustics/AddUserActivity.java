package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.les_loustics.db.DatabaseClient;
import com.example.les_loustics.db.User;

public class AddUserActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;

    // VIEW
    private EditText editTextNomView;
    private EditText editTextPrenomView;
    private EditText editTextAgeView;
    private Button saveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        editTextNomView = findViewById(R.id.editTextNom);
        editTextPrenomView = findViewById(R.id.editTextPrenom);
        editTextAgeView = findViewById(R.id.editTextAge);
        saveView = findViewById(R.id.button_save);

        // Associer un événement au bouton save
        saveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
    }

    private void saveUser() {

        // Récupérer les informations contenues dans les vues
        final String sNom = editTextNomView.getText().toString().trim();
        final String sPrenom = editTextPrenomView.getText().toString().trim();
        final int sAge = Integer.parseInt(editTextAgeView.getText().toString().trim());

        // Vérifier les informations fournies par l'utilisateur
        if(sNom.isEmpty()) {
            editTextNomView.setError("Quel est ton nom ?");
            editTextNomView.requestFocus();
            return;
        }

        if(sPrenom.isEmpty()) {
            editTextPrenomView.setError("Quel est ton prénom ?");
            editTextNomView.requestFocus();
            return;
        }

        /** Création d'une classe asynchrone pour sauvegarder le compte qui vient d'être créé **/

        class SaveUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                // créer un User
                User user = new User();
                user.setNom(sNom);
                user.setPrenom(sPrenom);
                user.setAge(sAge);

                // l'ajouter à la database
                long id = mDb.getAppDatabase().userDao().insert(user);

                // mettre à jour l'id du user
                // nécessaire si on souhaite avoir accès à l'id plus tard dans l'activité
                user.setId(id);

                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                // Quand le user est créé, on arrête l'activité AccountCreationActivity (on l'enlève de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Compte enregistré", Toast.LENGTH_LONG).show();
            }
        }

        // IMPORTANT : Bien penser à executer la demande asynchrone
        SaveUser su = new SaveUser();
        su.execute();
    }
}