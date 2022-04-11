package com.example.les_loustics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.les_loustics.Classes.Calcul;
import com.example.les_loustics.Classes.ExoMaths;
import com.example.les_loustics.db.User;

import java.util.ArrayList;

public class ExoMathsActivity extends AppCompatActivity {

    public static final String NIVEAU_KEY = "niveau_key";
    int niveau;
    ExoMaths exo;
    ArrayList<Calcul> calculs = new ArrayList<>();
    private LinearLayout linear;
    private TextView textViewNiveau;
    private ImageView check, error;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_maths);

        niveau = getIntent().getIntExtra(NIVEAU_KEY, 1);
        exo = new ExoMaths(niveau);

        linear = findViewById(R.id.calculs);

        textViewNiveau = findViewById(R.id.textViewNiveau);
        textViewNiveau.setText(String.format(getString(R.string.niveau), niveau));

        user = ((MyApplication) this.getApplication()).getCurrentUser();

        for(Calcul calcul : exo.getCalculs()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            TextView textViewCalcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            textViewCalcul.setText(calcul.getOperande1() + " " + calcul.getOperateur() + " " + calcul.getOperande2() +  " = ");

            calculs.add(calcul);

            //EditText resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);
            //resultat.setText(Integer.toString(multiplication.getResultat()));

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
            EditText resultat = v.findViewById(R.id.template_resultat);

            if(resultat.getText().toString().isEmpty()) {
                resultat.setError("Ce champ a besoin d'une valeur");
                resultat.requestFocus();
                return;
            }

            if (calculs.get(i).getResultat() == Integer.parseInt(resultat.getText().toString())) {
                error.setVisibility(View.GONE);
                check.setVisibility(View.VISIBLE);
            } else {
                check.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                nbErreurs++;
            }
        }
        if(nbErreurs == 0) {
            user.setEtatNiveau("maths", niveau, 1);
            intent = new Intent(ExoMathsActivity.this, LevelSelectionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("game_key", "maths");
            startActivity(intent);
        }
    }
}