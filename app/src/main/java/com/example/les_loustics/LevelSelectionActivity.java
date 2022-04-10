package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.les_loustics.Classes.Game;

public class LevelSelectionActivity extends AppCompatActivity {

    public static final String GAME_KEY = "game_key";
    public static final int RETURNMATHS_REQUEST = 1;
    public static final int RETURNLETTERS_REQUEST = 2;
    public static final int RETURNGEO_REQUEST = 3;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);
        String game = getIntent().getStringExtra(GAME_KEY);
        linear = findViewById(R.id.levelsList);

        if(game.equals("maths")) {
            for(int i = 1; i <= Game.getNbNiveauxMaths(); i++) {
                LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_level, null);
                Button btnLevel = (Button) linearTMP.findViewById(R.id.btnLevel);
                btnLevel.setText("Niveau " + i);
                int niveau = i;
                btnLevel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LevelSelectionActivity.this, ExoMathsActivity.class);
                        intent.putExtra("niveau_key", niveau);
                        startActivityForResult(intent, RETURNMATHS_REQUEST);
                    }
                });
                linear.addView(linearTMP);
            }
        } else if(game.equals("letters")) {
            for(int i = 1; i <= Game.getNbNiveauxLetters(); i++) {
                LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_level, null);
                Button btnLevel = (Button) linearTMP.findViewById(R.id.btnLevel);
                btnLevel.setText("Niveau " + i);
                linear.addView(linearTMP);
            }
        } else if(game.equals("geo")) {
            for(int i = 1; i <= Game.getNbNiveauxGeo(); i++) {
                LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_level, null);
                Button btnLevel = (Button) linearTMP.findViewById(R.id.btnLevel);
                btnLevel.setText("Niveau " + i);
                linear.addView(linearTMP);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RETURNMATHS_REQUEST) {
            Button btn;
            switch(resultCode) {
                case 1:
                    btn = linear.getChildAt(0).findViewById(R.id.btnLevel);
                    btn.setBackgroundColor(getResources().getColor(R.color.green));
                    break;
                case 2:
                    btn = linear.getChildAt(1).findViewById(R.id.btnLevel);
                    btn.setBackgroundColor(getResources().getColor(R.color.green));
                    break;
                case 3:
                    btn = linear.getChildAt(2).findViewById(R.id.btnLevel);
                    btn.setBackgroundColor(getResources().getColor(R.color.green));
                    break;
                default:
                    break;
            }
        }
    }
}