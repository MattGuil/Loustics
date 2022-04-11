package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    public static final String GAME_KEY = "game_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        String game = getIntent().getStringExtra(GAME_KEY);

        TextView tvGame = findViewById(R.id.textViewGame);
        TextView tvRules = findViewById(R.id.textViewRules);

        if(game.equals("maths")) {
            tvGame.setText(R.string.help_maths);
            tvRules.setText(R.string.rules_maths);
        } else if(game.equals("english")) {
            tvGame.setText(R.string.help_english);
            tvRules.setText(R.string.rules_english);
        } else if(game.equals("geo")) {
            tvGame.setText(R.string.help_geo);
            tvRules.setText(R.string.rules_geo);
        }
    }

    public void ok(View view) {
        setResult(RESULT_OK);
        finish();
    }
}