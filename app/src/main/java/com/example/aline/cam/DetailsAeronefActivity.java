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

import org.w3c.dom.Text;

import java.util.ArrayList;


public class DetailsAeronefActivity extends AppCompatActivity implements View.OnClickListener {

    UserManager um;
    int idAeronef;
    TextView cheminAccueil, cheminMesAeronefs, cheminMonEspace, cheminAeronefSelect;
    TextView nomAeronef, txtType, txtAcquisition, txtEnvergure, txtPoids, txtRemarques;
    ImageView img;
    Button btnModifier, btnRetourListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsaeronef);
        idAeronef = getIntent().getIntExtra("idAeronef", 0);

        //Récupération des TextView, ImageView et Button
        nomAeronef = (TextView) findViewById(R.id.txtNomAeronef);
        txtType = (TextView) findViewById(R.id.txtType);
        txtAcquisition = (TextView) findViewById(R.id.txtAcquisition);
        txtEnvergure = (TextView) findViewById(R.id.txtEnvergure);
        txtPoids = (TextView) findViewById(R.id.txtPoids);
        txtRemarques = (TextView) findViewById(R.id.txtRemarques);

        img = (ImageView) findViewById(R.id.imgAeronef);

        btnModifier = (Button) findViewById(R.id.btnModifier);
        btnModifier.setOnClickListener(this);
        btnRetourListe = (Button) findViewById(R.id.btnRetourListe);
        btnRetourListe.setOnClickListener(this);
        //Fin de la récupération

        //Affectation de chaque TextView
        //On cherche les info concernant l'aéronef dans la bdd
        um = new UserManager(this);
        um.open();
        final ArrayList<Aeronef> lesAeronefs = um.TousAeronefs();
        //On cherche dans la liste de tous les aéronefs celui qu'on veut
        nomAeronef.setText(lesAeronefs.get(idAeronef).getNom());
        txtType.setText(lesAeronefs.get(idAeronef).getType());
        txtAcquisition.setText("le " + lesAeronefs.get(idAeronef).getAcquisition());
        txtEnvergure.setText(String.valueOf(lesAeronefs.get(idAeronef).getEnvergure()) + " cm");
        txtPoids.setText(String.valueOf(lesAeronefs.get(idAeronef).getPoids()) + " grammes");
        txtRemarques.setText(lesAeronefs.get(idAeronef).getRemarques());
        if (lesAeronefs.get(idAeronef).getType().equals("avion électrique"))
            img.setImageResource(R.drawable.electricplane);
        else if (lesAeronefs.get(idAeronef).getType().equals("avion essence"))
            img.setImageResource(R.drawable.oilplane);
        else if (lesAeronefs.get(idAeronef).getType().equals("biplan"))
            img.setImageResource(R.drawable.biplane);
        else if (lesAeronefs.get(idAeronef).getType().equals("drone"))
            img.setImageResource(R.drawable.drone);
        else if (lesAeronefs.get(idAeronef).getType().equals("hélicoptère"))
            img.setImageResource(R.drawable.helicopter);
        else if (lesAeronefs.get(idAeronef).getType().equals("planeur"))
            img.setImageResource(R.drawable.airplane);

        um.close();
        //Fin de l'affectation des TextView

        //Fil d'Arianne
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);
        cheminMesAeronefs = (TextView) findViewById(R.id.cheminMesAeronefs);
        cheminMesAeronefs.setOnClickListener(this);
        cheminMonEspace = (TextView) findViewById(R.id.cheminMonEspace);
        cheminMonEspace.setOnClickListener(this);
        cheminAeronefSelect = (TextView) findViewById(R.id.cheminAeronefSelect);
        cheminAeronefSelect.setText(">"+lesAeronefs.get(idAeronef).getNom());
        //Fin du fil d'Arianne
    }


    @Override
    public void onClick(View v) {
        if(v.equals(btnModifier)){
            Intent intent = new Intent(DetailsAeronefActivity.this,ModifAeronefActivity.class);
            intent.putExtra("idAeronef", idAeronef);
            startActivity(intent);
        }
        if(v.equals(btnRetourListe)){
            Intent intent = new Intent(DetailsAeronefActivity.this,MesAeronefsActivity.class);
            intent.putExtra("page", 1);
            startActivity(intent);
        }
        if (v.equals(cheminAccueil)) {
            Intent intent = new Intent(DetailsAeronefActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminMonEspace)) {
            Intent intent = new Intent(DetailsAeronefActivity.this, MonEspaceActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminMesAeronefs)) {
            Intent intent = new Intent(DetailsAeronefActivity.this, MesAeronefsActivity.class);
            intent.putExtra("page", 1);
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
                Intent intent = new Intent(DetailsAeronefActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
