package com.example.les_loustics.Classes;

import java.util.ArrayList;
import java.util.Random;

public class ExoMaths {

    int nbCalculs;
    ArrayList<Calcul> calculs = new ArrayList<Calcul>();

    public ExoMaths(int niveau) {
        if(niveau == 1) {
            this.nbCalculs = 3;
        } else if(niveau == 2) {
            this.nbCalculs = 5;
        } else if(niveau == 3) {
            this.nbCalculs = 10;
        }

        for(int i = 0; i < this.nbCalculs; i++) {
            calculs.add(new Calcul(genererOperande(), genererOperateur(), genererOperande()));
        }
    }

    private static int genererOperande() {
        Random r = new Random();
        return r.nextInt(10);
    }

    private static String genererOperateur() {
        Random r = new Random();
        int result = r.nextInt(4);
        switch(result) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "*";
        }
    }

    public ArrayList<Calcul> getCalculs() {
        return calculs;
    }

}
