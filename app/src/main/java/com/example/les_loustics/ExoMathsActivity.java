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

import java.util.ArrayList;

public class ExoMathsActivity extends AppCompatActivity {

    public static final String NIVEAU_KEY = "niveau_key";
    int niveau;
    ExoMaths exo;
    ArrayList<Calcul> calculs = new ArrayList<>();
    private LinearLayout linear;
    private TextView textViewNiveau;
    private ImageView check, error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_maths);

        niveau = getIntent().getIntExtra(NIVEAU_KEY, 1);
        exo = new ExoMaths(niveau);

        linear = findViewById(R.id.calculs);

        textViewNiveau = findViewById(R.id.textViewNiveau);
        textViewNiveau.setText(String.format(getString(R.string.niveau), niveau));

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
            if (calculs.get(i).getResultat() != Integer.parseInt(resultat.getText().toString())) {
                check.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                nbErreurs++;
            } else {
                error.setVisibility(View.GONE);
                check.setVisibility(View.VISIBLE);
            }
        }
        if(nbErreurs == 0) {
            setResult(this.niveau);
        } else {
            setResult(-1);
        }
    }
}