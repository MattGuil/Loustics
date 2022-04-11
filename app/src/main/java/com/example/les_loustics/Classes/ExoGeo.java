package com.example.les_loustics.Classes;

import java.util.ArrayList;

public class ExoGeo {

    ArrayList<QuestionGeo> questions;

    public ExoGeo() {
        questions = new ArrayList<>();
        questions.add(new QuestionGeo("Quel est la capitale de l'Italie ?", "rome"));
        questions.add(new QuestionGeo("Sur quel continent se trouve le Canada ?", "amérique"));
        questions.add(new QuestionGeo("Quel langue est parlée au Brésil ?", "portugais"));
        questions.add(new QuestionGeo("Quelle ville est dans l'hémisphère sud : Sydney ou Londres ?", "sydney"));
        questions.add(new QuestionGeo("Combien y a t-il de continents sur Terre ?", "6"));
    }

    public ArrayList<QuestionGeo> getQuestions() {
        return questions;
    }

}
