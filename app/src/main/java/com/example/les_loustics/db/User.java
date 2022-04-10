package com.example.les_loustics.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nom = "";
    private String prenom = "";
    private int age = 0;
    private int etatNiveau1Maths = 0;
    private int etatNiveau2Maths = 0;
    private int etatNiveau3Maths = 0;
    private int etatNiveau1English = 0;
    private int etatNiveau2English = 0;
    private int etatNiveau1Geo = 0;

    public long getId() { return this.id; }

    public void setId(long id) { this.id = id; }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) { this.prenom = prenom; }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) { this.age = age; }

    public void setEtatNiveau1Maths(int etat) {
        etatNiveau1Maths = etat;
    }

    public void setEtatNiveau2Maths(int etat) {
        etatNiveau2Maths = etat;
    }

    public void setEtatNiveau3Maths(int etat) {
        etatNiveau3Maths = etat;
    }

    public void setEtatNiveau1English(int etat) {
        etatNiveau1English = etat;
    }

    public void setEtatNiveau2English(int etat) {
        etatNiveau2English = etat;
    }

    public void setEtatNiveau1Geo(int etat) {
        etatNiveau1Geo = etat;
    }

    public int getEtatNiveau1Maths() {
        return etatNiveau1Maths;
    }

    public int getEtatNiveau2Maths() {
        return etatNiveau2Maths;
    }

    public int getEtatNiveau3Maths() {
        return etatNiveau3Maths;
    }

    public int getEtatNiveau1English() {
        return etatNiveau1English;
    }

    public int getEtatNiveau2English() {
        return etatNiveau2English;
    }

    public int getEtatNiveau1Geo() {
        return etatNiveau1Geo;
    }

    public int getEtatNiveau(String game, int niveau) {
        switch(game) {
            case "maths":
                switch(niveau) {
                    case 1:
                        return getEtatNiveau1Maths();
                    case 2:
                        return getEtatNiveau2Maths();
                    case 3:
                        return getEtatNiveau3Maths();
                    default:
                        return 0;
                }
            case "letters":
                switch(niveau) {
                    case 1:
                        return getEtatNiveau1English();
                    case 2:
                        return getEtatNiveau2English();
                    default:
                        return 0;
                }
            case "geo":
                switch(niveau) {
                    case 1:
                        return getEtatNiveau1Geo();
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }

    public void setEtatNiveau(String game, int niveau, int etat) {
        switch(game) {
            case "maths":
                switch(niveau) {
                    case 1:
                        setEtatNiveau1Maths(etat);
                        break;
                    case 2:
                        setEtatNiveau2Maths(etat);
                        break;
                    case 3:
                        setEtatNiveau3Maths(etat);
                        break;
                    default:
                        break;
                }
                break;
            case "letters":
                switch(niveau) {
                    case 1:
                        setEtatNiveau1English(etat);
                        break;
                    case 2:
                        setEtatNiveau2English(etat);
                        break;
                    default:
                        break;
                }
                break;
            case "geo":
                switch(niveau) {
                    case 1:
                        setEtatNiveau1Geo(etat);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
