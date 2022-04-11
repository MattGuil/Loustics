package com.example.les_loustics.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ExoEnglish {

    int nbQuestions;
    private Map<Integer, String> couleurs;
    ArrayList<QuestionCouleur> questions;

    public ExoEnglish(int niveau) {
        if(niveau == 1) {
            this.nbQuestions = 3;
        } else if(niveau == 2) {
            this.nbQuestions = 5;
        }

        questions = new ArrayList<>();

        String couleur;

        for(int i = 0; i < this.nbQuestions; i++) {
            questions.add(new QuestionCouleur(genererCouleur()));
        }
    }

    private String genererCouleur() {
        couleurs = new HashMap<>();
        couleurs.put(0, "white");
        couleurs.put(1, "black");
        couleurs.put(2, "green");
        couleurs.put(3, "red");
        couleurs.put(4, "yellow");
        couleurs.put(5, "blue");
        couleurs.put(6, "orange");
        couleurs.put(7, "cyan");
        couleurs.put(8, "pink");
        couleurs.put(9, "purple");

        Random r = new Random();
        return couleurs.get(r.nextInt(9));
    }

    public ArrayList<QuestionCouleur> getQuestions() {
        return questions;
    }

}
