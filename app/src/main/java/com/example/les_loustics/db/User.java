package com.example.les_loustics.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String nom;
    private String prenom;
    private int age;

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

}
