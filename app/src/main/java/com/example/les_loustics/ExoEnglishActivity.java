package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.les_loustics.Classes.ExoEnglish;
import com.example.les_loustics.Classes.QuestionCouleur;
import com.example.les_loustics.db.User;

import java.util.ArrayList;

public class ExoEnglishActivity extends AppCompatActivity {

    public static final String NIVEAU_KEY = "niveau_key";
    int niveau;
    ExoEnglish exo;
    ArrayList<QuestionCouleur> questions = new ArrayList<>();
    private LinearLayout linear;
    private TextView textViewNiveau;
    private ImageView check, error;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_english);

        niveau = getIntent().getIntExtra(NIVEAU_KEY, 1);
        exo = new ExoEnglish(niveau);

        linear = findViewById(R.id.questions);

        textViewNiveau = findViewById(R.id.textViewNiveau);
        textViewNiveau.setText(String.format(getString(R.string.niveau), niveau));

        user = ((MyApplication) this.getApplication()).getCurrentUser();

        for(QuestionCouleur question : exo.getQuestions()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_question_couleur, null);

            Button colorBlock = (Button) linearTMP.findViewById(R.id.template_color_block);
            switch(question.getCouleur()) {
                case "white" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.white));
                    break;
                case "black" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.black));
                    break;
                case "green" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.green));
                    break;
                case "red" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.red));
                    break;
                case "yellow" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.yellow));
                    break;
                case "blue" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.blue));
                    break;
                case "orange" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.orange));
                    break;
                case "cyan" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.cyan));
                    break;
                case "pink" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.pink));
                    break;
                case "purple" :
                    colorBlock.setBackgroundColor(getResources().getColor(R.color.purple));
                    break;
            }

            questions.add(question);

            linear.addView(linearTMP);
        }
    }

    public void valider(View view) {
        Intent intent;
        int nbErreurs = 0;
        int count = linear.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = linear.getChildAt(i);
            check = v.findViewById(R.id.check);
            error = v.findViewById(R.id.error);
            EditText resultat = v.findViewById(R.id.template_color_result);

            if(resultat.getText().toString().isEmpty()) {
                resultat.setError("Ce champ a besoin d'une valeur");
                resultat.requestFocus();
                return;
            }

            if (resultat.getText().toString().equals(questions.get(i).getCouleur())) {
                error.setVisibility(View.GONE);
                check.setVisibility(View.VISIBLE);
            } else {
                check.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                nbErreurs++;
            }
        }
        if(nbErreurs == 0) {
            user.setEtatNiveau("english", niveau, 1);
            intent = new Intent(ExoEnglishActivity.this, LevelSelectionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("game_key", "english");
            startActivity(intent);
        }
    }
}