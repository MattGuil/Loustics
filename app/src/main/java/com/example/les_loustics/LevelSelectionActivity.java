package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.les_loustics.Classes.Game;
import com.example.les_loustics.db.DatabaseClient;
import com.example.les_loustics.db.User;

import java.util.Locale;

public class LevelSelectionActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;

    public static final String GAME_KEY = "game_key";
    public static final int RETURNLETTERS_REQUEST = 2;
    public static final int RETURNGEO_REQUEST = 3;
    private LinearLayout linear;
    User user;
    Intent intent;
    TextView tvGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        String game = getIntent().getStringExtra(GAME_KEY);
        linear = findViewById(R.id.levelsList);
        user = ((MyApplication) this.getApplication()).getCurrentUser();

        tvGame = findViewById(R.id.textViewGame);
        tvGame.setText(game.toUpperCase(Locale.ROOT));

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        class UpdateUser extends AsyncTask<Void, Void, User> {

            private User user;

            public UpdateUser(User user) {
                this.user = user;
            }

            @Override
            protected User doInBackground(Void... voids) {
                mDb.getAppDatabase().userDao().update(user);
                return user;
            }
        }

        if(user.getNom() != "") {
            UpdateUser uu = new UpdateUser(user);
            uu.execute();
        }

        if(game.equals("maths")) {
            for(int i = 1; i <= Game.getNbNiveauxMaths(); i++) {
                LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_level, null);
                Button btnLevel = (Button) linearTMP.findViewById(R.id.btnLevel);
                btnLevel.setText("Niveau " + i);
                if(user.getEtatNiveau("maths", i) == 0) {
                    btnLevel.setBackgroundColor(getResources().getColor(R.color.purple_500));
                } else {
                    btnLevel.setBackgroundColor(getResources().getColor(R.color.green));
                }
                int niveau = i;
                btnLevel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(LevelSelectionActivity.this, ExoMathsActivity.class);
                        intent.putExtra("niveau_key", niveau);
                        startActivity(intent);
                    }
                });
                linear.addView(linearTMP);
            }
        } else if(game.equals("english")) {
            for(int i = 1; i <= Game.getNbNiveauxEnglish(); i++) {
                LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_level, null);
                Button btnLevel = (Button) linearTMP.findViewById(R.id.btnLevel);
                btnLevel.setText("Niveau " + i);
                if(user.getEtatNiveau("english", i) == 0) {
                    btnLevel.setBackgroundColor(getResources().getColor(R.color.purple_500));
                } else {
                    btnLevel.setBackgroundColor(getResources().getColor(R.color.green));
                }
                int niveau = i;
                btnLevel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(LevelSelectionActivity.this, ExoEnglishActivity.class);
                        intent.putExtra("niveau_key", niveau);
                        startActivity(intent);
                    }
                });
                linear.addView(linearTMP);
            }
        } else if(game.equals("geo")) {
            for(int i = 1; i <= Game.getNbNiveauxGeo(); i++) {
                LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_level, null);
                Button btnLevel = (Button) linearTMP.findViewById(R.id.btnLevel);
                btnLevel.setText("Niveau " + i);
                if(user.getEtatNiveau("geo", i) == 0) {
                    btnLevel.setBackgroundColor(getResources().getColor(R.color.purple_500));
                } else {
                    btnLevel.setBackgroundColor(getResources().getColor(R.color.green));
                }
                int niveau = i;
                btnLevel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(LevelSelectionActivity.this, ExoGeoActivity.class);
                        intent.putExtra("niveau_key", niveau);
                        startActivity(intent);
                    }
                });
                linear.addView(linearTMP);
            }
        }
    }

}