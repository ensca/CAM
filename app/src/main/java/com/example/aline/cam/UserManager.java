package com.example.aline.cam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Date;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class UserManager {

    DBManager dbm;
    SQLiteDatabase db;

    public UserManager(Context ctx) {
        dbm = new DBManager(ctx, "carnet_de_vol", null, 1);
    }

    public void open() {
        db = dbm.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void ajouterAeronef(Aeronef a){
        ContentValues vals = new ContentValues();
        vals.put("idAeronef", a.getIdAeronef());
        vals.put("nom",a.getNom());
        vals.put("type",a.getType());
        vals.put("acquisition",a.getAcquisition());
        vals.put("envergure",a.getEnvergure());
        vals.put("poids",a.getPoids());
        vals.put("remarques",a.getRemarques());
        db.insert("aeronef",null,vals);
    }

    public void ajouterVol(Vol v)
    {
        ContentValues vals = new ContentValues();
        vals.put("idVol", v.getIdVol());
        vals.put("date", v.getDate());
        vals.put("heureDeb", v.getHeureDeb());
        vals.put("heureFin", v.getHeureFin());
        vals.put("lieu", v.getLieu());
        vals.put("remarques", v.getRemarques());
        vals.put("idAeronef",v.getIdAeronef());
        db.insert("vol",null,vals);
    }

    public ArrayList<Aeronef> TousAeronefs(){
        ArrayList<Aeronef> aero = new ArrayList<>();
        Cursor c = db.query("aeronef", new String[]{"idAeronef","nom","type","acquisition","envergure","poids","remarques"},null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Aeronef a = new Aeronef();
            a.setIdAeronef(c.getInt(0));
            a.setNom(c.getString(1));
            a.setType(c.getString(2));
            a.setAcquisition(c.getString(3));
            a.setEnvergure(c.getInt(4));
            a.setPoids(c.getInt(5));
            a.setRemarques(c.getString(6));
            aero.add(a);
            c.moveToNext();
        }
        return aero;
    }
    public ArrayList<Vol> TousVols(){
        ArrayList<Vol> lesVols = new ArrayList<>();
        Cursor c = db.query("vol", new String[]{"idVol","date","heureDeb","heureFin","lieu","remarques","idAeronef"},null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Vol v = new Vol();
            v.setIdVol(c.getInt(0));
            v.setDate(c.getString(1));
            v.setHeureDeb(c.getString(2));
            v.setHeureFin(c.getString(3));
            v.setLieu(c.getString(4));
            v.setRemarques(c.getString(5));
            v.setIdAeronef(c.getInt(6));
            lesVols.add(v);
            c.moveToNext();
        }
        return lesVols;
    }

    public int CompteAeronefs(){
        int compteur=0;
        Cursor c = db.query("aeronef", new String[]{"idAeronef","nom","type","acquisition","envergure","poids","remarques"},null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            compteur++;
            c.moveToNext();
        }
        return compteur;
    }
    public int CompteVols(){
        int compteur=0;
        Cursor c = db.query("vol", new String[]{"idVol","date","heureDeb","heureFin","lieu","remarques","idAeronef"},null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            compteur++;
            c.moveToNext();
        }
        return compteur;
    }

    public String supprimerDB()
    {
        File file = new File (db.getPath());
        String chemin = db.getPath();
        db.deleteDatabase (file);
        return chemin;
    }

    public void insertionAeronef()
    {
        Aeronef aeronef = new Aeronef(1,"blop","avion électrique","12/12/2015",123,25,"je suis content");
        Aeronef aeronef2 = new Aeronef(2,"pouletto","drone","12/03/2016",123,25,"patate pas cuite");
        Aeronef aeronef3 = new Aeronef(3,"bob le bonhomme vert","biplan","23/04/2016",123,25,"j'aime les pâtes");
        ajouterAeronef(aeronef);
        ajouterAeronef(aeronef2);
        ajouterAeronef(aeronef3);
    }
    public void insertionVol()
    {
        Vol vol1 = new Vol(1,"03/06/2016","12:30","12:33","Carlepont","pas mal",1);
        ajouterVol(vol1);
    }

    public int selectIdAeronef(String leNom)
    {
        ArrayList<Aeronef> listAero = TousAeronefs();
        int id = 1000;
        for (int i = 0; i < listAero.size(); i++) {
            if (listAero.get(i).getNom().equals(leNom)) {
                id = listAero.get(i).getIdAeronef();
            }
        }
        return id;
    }
    public String selectNomAeronef(int id)
    {
        ArrayList<Aeronef> listAero = TousAeronefs();
        String nom = "erreur : cet aéronef n'existe pas";
        for (int i = 0; i < listAero.size(); i++) {
            if (listAero.get(i).getIdAeronef() == id) {
                nom = listAero.get(i).getNom();
            }
        }
        return nom;
    }

    public String selectNomAeronefVol(int idVol){
        ArrayList<Vol> listVols = TousVols();
        String nom = "erreur : ce vol n'existe pas";
        int idAero = 0;
        for (int i = 0; i < listVols.size(); i++) {
            if (listVols.get(i).getIdVol() == idVol) {
                idAero = listVols.get(i).getIdAeronef();
                nom = selectNomAeronef(idAero);
            }
        }
        return nom;
    }

    public int modifierAeronef(int idAeronef, Aeronef aeronef){
        ContentValues values = new ContentValues();
        values.put("nom", aeronef.getNom());
        values.put("type", aeronef.getType());
        values.put("acquisition", aeronef.getAcquisition());
        values.put("envergure", aeronef.getEnvergure());
        values.put("poids", aeronef.getPoids());
        values.put("remarques", aeronef.getRemarques());
        return db.update("aeronef", values, "idAeronef" + " = " +idAeronef, null);
    }

    public void modifierVol(int idVol, Vol vol){
        ContentValues values = new ContentValues();
        values.put("date", vol.getDate());
        values.put("lieu", vol.getLieu());
        values.put("heureDeb", vol.getHeureDeb());
        values.put("heureFin", vol.getHeureFin());
        values.put("remarques", vol.getRemarques());
        values.put("idAeronef", vol.getIdAeronef());
        db.update("vol", values, "idVol" + " = " +idVol, null);
    }

}
