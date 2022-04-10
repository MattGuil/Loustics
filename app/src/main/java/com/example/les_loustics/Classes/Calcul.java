package com.example.les_loustics.Classes;

public class Calcul {

    int operande1, operande2;
    String operateur;

    Calcul(int operande1, String operateur, int operande2) {
        if(operateur == "/") {
            this.operande1 = operande1 * operande2;
        } else {
            this.operande1 = operande1;
        }
        if(operateur == "/" && operande2 == 0) {
            this.operande2 = 1;
        } else {
            this.operande2 = operande2;
        }
        this.operateur = operateur;
    }

    public int getOperande1() {
        return this.operande1;
    }

    public String getOperateur() {
        return this.operateur;
    }

    public int getOperande2() {
        return this.operande2;
    }

    public int getResultat() {
        if(operateur.equals("+")) {
            return this.operande1 + this.operande2;
        } else if(operateur.equals("-")) {
            return this.operande1 - this.operande2;
        } else if(operateur.equals("*")) {
            return this.operande1 * this.operande2;
        } else if(operateur.equals("/")) {
            return this.operande1 / this.operande2;
        } else {
            return 0;
        }
    }

}
