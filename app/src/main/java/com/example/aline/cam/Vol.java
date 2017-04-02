package com.example.aline.cam;

import java.sql.Date;
import java.sql.Time;

public class Vol {
    int idVol, idAeronef;           //Durée en minutes
    String lieu, remarques, date, heureDeb, heureFin;

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDeb() {
        return heureDeb;
    }

    public void setHeureDeb(String heureDeb) {
        this.heureDeb = heureDeb;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public int getIdAeronef() {
        return idAeronef;
    }

    public void setIdAeronef(int idAeronef) {
        this.idAeronef = idAeronef;
    }

    //Constructeurs
    public Vol() {
        super();
    }

    public Vol(int idVol,String date, String heureDeb,String heureFin,String lieu, String remarques, int idAeronef)
    {
        this.idVol = idVol;
        this.date = date;
        this.heureDeb = heureDeb;
        this.heureFin = heureFin;
        this.lieu = lieu;
        this.remarques = remarques;
        this.idAeronef = idAeronef;
    }

}
