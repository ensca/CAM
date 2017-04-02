package com.example.aline.cam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.aline.cam.R.id.time;


public class DetailsVolActivity extends AppCompatActivity implements View.OnClickListener {
    UserManager um;
    int idVolIntent;
    TextView cheminAccueil, cheminCarnetDeVol, cheminMonEspace, cheminListeParAeronef, cheminDetails;
    TextView txtAeronef, txtType, txtDateVol, txtHeureDeb, txtHeureFin, txtLieu, txtRemarques;
    ImageView img;
    Button btnModifier, btnRetourListe;
    //DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    //Date hdeb, hfin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailvol);
        idVolIntent = getIntent().getIntExtra("idVolIntent", 0)-1;

        //Récupération des TextView, ImageView et Button
        txtAeronef = (TextView) findViewById(R.id.txtAeronef);
        txtDateVol = (TextView) findViewById(R.id.txtDateVol);
        txtType = (TextView) findViewById(R.id.txtType);
        txtLieu = (TextView) findViewById(R.id.txtLieu);
        txtHeureDeb = (TextView) findViewById(R.id.txtHeureDeb);
        txtHeureFin = (TextView) findViewById(R.id.txtHeureFin);
        txtRemarques = (TextView) findViewById(R.id.txtRemarques);

        img = (ImageView) findViewById(R.id.imgAeronef);

        btnModifier = (Button) findViewById(R.id.btnModifier);
        btnModifier.setOnClickListener(this);
        btnRetourListe = (Button) findViewById(R.id.btnRetourListe);
        btnRetourListe.setOnClickListener(this);
        //Fin de la récupération

        //Affectation de chaque TextView
        //On cherche les info concernant le vol dans la bdd vol
        um = new UserManager(this);
        um.open();
        final ArrayList<Vol> lesVols = um.TousVols();
        final ArrayList<Aeronef> lesAeronefs = um.TousAeronefs();
        //On cherche dans la liste des aéronefs, celui qui a été utilisé pour ce vol
        String nomAeronefUtilise = um.selectNomAeronefVol(idVolIntent);
        txtDateVol.setText(lesVols.get(idVolIntent).getDate().toString());
        //txtType.setText(lesAeronefs.get(idAeronef).getType());

        Toast.makeText(this, "id du vol sélectionné : " + idVolIntent, Toast.LENGTH_LONG).show();

        txtAeronef.setText(um.selectNomAeronef(lesVols.get(idVolIntent).getIdAeronef()));
        txtLieu.setText(lesVols.get(idVolIntent).getLieu());
        txtHeureDeb.setText(lesVols.get(idVolIntent).getHeureDeb());
        txtHeureFin.setText(lesVols.get(idVolIntent).getHeureFin());
        txtRemarques.setText(lesVols.get(idVolIntent).getRemarques());
        if (lesAeronefs.get(idVolIntent).getType().equals("avion électrique"))
            img.setImageResource(R.drawable.electricplane);
        else if (lesAeronefs.get(idVolIntent).getType().equals("avion essence"))
            img.setImageResource(R.drawable.oilplane);
        else if (lesAeronefs.get(idVolIntent).getType().equals("biplan"))
            img.setImageResource(R.drawable.biplane);
        else if (lesAeronefs.get(idVolIntent).getType().equals("drone"))
            img.setImageResource(R.drawable.drone);
        else if (lesAeronefs.get(idVolIntent).getType().equals("hélicoptère"))
            img.setImageResource(R.drawable.helicopter);
        else if (lesAeronefs.get(idVolIntent).getType().equals("planeur"))
            img.setImageResource(R.drawable.airplane);

        um.close();
        //Fin de l'affectation des TextView

        //Fil d'Arianne
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);
        cheminMonEspace = (TextView) findViewById(R.id.cheminMonEspace);
        cheminMonEspace.setOnClickListener(this);
        cheminCarnetDeVol = (TextView) findViewById(R.id.cheminCarnetDeVol);
        cheminCarnetDeVol.setOnClickListener(this);
        cheminListeParAeronef = (TextView) findViewById(R.id.cheminDetailsVol);
        cheminListeParAeronef.setOnClickListener(this);
        cheminDetails = (TextView) findViewById(R.id.cheminDetailsVol);
        cheminDetails.setOnClickListener(this);
        //Fin du fil d'Arianne
    }


    @Override
    public void onClick(View v) {
        if(v.equals(btnModifier)){
            //A faire : modifier la bdd
        }
        if(v.equals(btnRetourListe)){
            Intent intent = new Intent(DetailsVolActivity.this,ListeVolsParAeronefActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminAccueil)) {
            Intent intent = new Intent(DetailsVolActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminMonEspace)) {
            Intent intent = new Intent(DetailsVolActivity.this, MonEspaceActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminCarnetDeVol)) {
            Intent intent = new Intent(DetailsVolActivity.this, CarnetDeVolActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminListeParAeronef)) {
            Intent intent = new Intent(DetailsVolActivity.this, ListeVolsParAeronefActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminDetails)) {
            Intent intent = new Intent(DetailsVolActivity.this, DetailsVolActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnMonEspace:
                Intent intent = new Intent(DetailsVolActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
