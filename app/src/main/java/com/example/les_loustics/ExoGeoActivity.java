package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.les_loustics.Classes.ExoGeo;
import com.example.les_loustics.Classes.QuestionGeo;
import com.example.les_loustics.db.User;

import java.util.ArrayList;
import java.util.Locale;

public class ExoGeoActivity extends AppCompatActivity {

    public static final String NIVEAU_KEY = "niveau_key";
    int niveau;
    ExoGeo exo;
    ArrayList<QuestionGeo> questions = new ArrayList<>();
    private LinearLayout linear;
    private TextView textViewNiveau;
    private ImageView check, error;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_geo);

        niveau = getIntent().getIntExtra(NIVEAU_KEY, 1);
        exo = new ExoGeo();

        linear = findViewById(R.id.questions);

        textViewNiveau = findViewById(R.id.textViewNiveau);
        textViewNiveau.setText(String.format(getString(R.string.niveau), niveau));

        user = ((MyApplication) this.getApplication()).getCurrentUser();

        for(QuestionGeo question : exo.getQuestions()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_question_geo, null);
            TextView textViewQuestion = linearTMP.findViewById(R.id.template_question);
            textViewQuestion.setText(question.getQuestion());
            linear.addView(linearTMP);

            questions.add(question);
        }
    }

    public void aide(View view) {
        Intent intent = new Intent(ExoGeoActivity.this, HelpActivity.class);
        intent.putExtra("game_key", "geo");
        startActivity(intent);
    }

    public void valider(View view) {
        Intent intent;
        int nbErreurs = 0;
        int count = linear.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = linear.getChildAt(i);
            check = v.findViewById(R.id.check);
            error = v.findViewById(R.id.error);
            EditText reponse = v.findViewById(R.id.template_reponse);

            if(reponse.getText().toString().isEmpty()) {
                reponse.setError("Ce champ a besoin d'une valeur");
                reponse.requestFocus();
                return;
            }

            if (reponse.getText().toString().toLowerCase(Locale.ROOT).equals(questions.get(i).getReponse())) {
                error.setVisibility(View.GONE);
                check.setVisibility(View.VISIBLE);
            } else {
                check.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                nbErreurs++;
            }
        }
        if(nbErreurs == 0) {
            user.setEtatNiveau("geo", niveau, 1);
            intent = new Intent(ExoGeoActivity.this, LevelSelectionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("game_key", "geo");
            startActivity(intent);
        }
    }
}