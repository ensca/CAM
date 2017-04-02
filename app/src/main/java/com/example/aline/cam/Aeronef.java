package com.example.aline.cam;


import java.util.Date;

public class Aeronef {
    int idAeronef, envergure, poids;
    String nom, type, remarques, acquisition;


    // Début getteurs et setteurs
    public int getIdAeronef() {
        return idAeronef;
    }

    public void setIdAeronef(int id) {
        this.idAeronef = id;
    }

    public int getEnvergure() {
        return envergure;
    }

    public void setEnvergure(int envergure) {
        this.envergure = envergure;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getRemarques() { return remarques; }

    public void setRemarques(String remarques) { this.remarques = remarques; }

    public String getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(String acquisition) {
        this.acquisition = acquisition;
    }
    // fin getteurs et setteurs

    //Constructeurs
    public Aeronef() {
        super();
    }

    public Aeronef(int id, String nom,String type, String acquisition, int envergure, int poids, String remarques) {
        this.idAeronef = id;
        this.envergure = envergure;
        this.poids = poids;
        this.nom = nom;
        this.type = type;
        this.acquisition = acquisition;
        this.remarques = remarques;
    }
}
