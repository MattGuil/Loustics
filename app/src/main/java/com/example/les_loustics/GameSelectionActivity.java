package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.les_loustics.db.User;

public class GameSelectionActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        user = ((MyApplication) this.getApplication()).getCurrentUser();

        String sourceString = "Salut <b>" + user.getPrenom() + "</b>,<br>Choisis une activité !";
        TextView salutation = findViewById(R.id.salutation);
        salutation.setText(Html.fromHtml(sourceString));
    }

    public void launchMaths(View view) {
        Intent intent = new Intent(GameSelectionActivity.this, LevelSelectionActivity.class);
        intent.putExtra("game_key", "maths");
        startActivity(intent);
    }

    public void launchEnglish(View view) {
        Intent intent = new Intent(GameSelectionActivity.this, LevelSelectionActivity.class);
        intent.putExtra("game_key", "english");
        startActivity(intent);
    }

    public void launchGeo(View view) {
        Intent intent = new Intent(GameSelectionActivity.this, LevelSelectionActivity.class);
        intent.putExtra("game_key", "geo");
        startActivity(intent);
    }
}