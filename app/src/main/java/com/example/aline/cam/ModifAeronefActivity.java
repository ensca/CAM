package com.example.aline.cam;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ModifAeronefActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    UserManager um;
    TextView cheminAccueil, cheminMonEspace, cheminMesAeronefs, cheminModifierAeronef;
    TextView txtNomAeronef;
    Spinner spinnerType;
    EditText editNom, txtAcquisition, txtEnvergure, txtPoids, txtRemarques;
    Button btnEnregistrer;
    int idAeronefSelect;
    Aeronef aeronefModifie;
    String newType;
    String newNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifaeronef);
        idAeronefSelect = getIntent().getIntExtra("idAeronef", 0);

        //Récupération des éléments graphiques
        txtNomAeronef = (TextView) findViewById(R.id.txtNomAeronef);
        editNom = (EditText) findViewById(R.id.editNom);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        List<String> lesTypes = new ArrayList<String>();
        lesTypes.add("avion électrique");
        lesTypes.add("avion essence");
        lesTypes.add("biplan");
        lesTypes.add("drone");
        lesTypes.add("hélicoptère");
        lesTypes.add("planeur");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(dataAdapter);
        txtAcquisition = (EditText) findViewById(R.id.txtAcquisition);
        txtEnvergure = (EditText) findViewById(R.id.txtEnvergure);
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtRemarques = (EditText) findViewById(R.id.txtRemarques);
        btnEnregistrer = (Button) findViewById(R.id.btnEnregistrer);
        btnEnregistrer.setOnClickListener(this);
        //Fin de la récupération

        //Remplissage automatiques des champs EditView avec les données de l'aéronef
        um = new UserManager(this);
        um.open();
        final ArrayList<Aeronef> lesAeronefs = um.TousAeronefs();

        txtNomAeronef.setText(lesAeronefs.get(idAeronefSelect).getNom());

        editNom.setText(lesAeronefs.get(idAeronefSelect).getNom());

        if (lesAeronefs.get(idAeronefSelect).getType().equals("avion électrique"))
            spinnerType.setSelection(0);
        if (lesAeronefs.get(idAeronefSelect).getType().equals("avion essence"))
            spinnerType.setSelection(1);
        if (lesAeronefs.get(idAeronefSelect).getType().equals("biplan"))
            spinnerType.setSelection(2);
        if (lesAeronefs.get(idAeronefSelect).getType().equals("drone"))
            spinnerType.setSelection(3);
        if (lesAeronefs.get(idAeronefSelect).getType().equals("hélicoptère"))
            spinnerType.setSelection(4);
        if (lesAeronefs.get(idAeronefSelect).getType().equals("planneur"))
            spinnerType.setSelection(5);

        txtAcquisition.setText(lesAeronefs.get(idAeronefSelect).getAcquisition());

        txtEnvergure.setText(String.valueOf(lesAeronefs.get(idAeronefSelect).getEnvergure()));

        txtPoids.setText(String.valueOf(lesAeronefs.get(idAeronefSelect).getPoids()));

        txtRemarques.setText(lesAeronefs.get(idAeronefSelect).getRemarques());
        //Fin du remplissage

        um.close();

        //Fil d'Ariane
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);

        cheminMonEspace = (TextView) findViewById(R.id.cheminMonEspace);
        cheminMonEspace.setOnClickListener(this);

        cheminMesAeronefs = (TextView) findViewById(R.id.cheminMesAeronefs);
        cheminMesAeronefs.setOnClickListener(this);

        cheminModifierAeronef = (TextView) findViewById(R.id.cheminModifierAeronef);
        cheminModifierAeronef.setText(">Modidier "+lesAeronefs.get(idAeronefSelect).getNom());
        //Fin du fil d'Ariane

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String leType = parent.getSelectedItem().toString();
        this.newType = leType;
    }
    public void onNothingSelected(AdapterView<?> arg0) {    }

    public void buildNewAeronef(String nom, String acquisition, int envergure, int poids, String remarques){
        Aeronef a = new Aeronef();
        a.setNom(nom);
        a.setAcquisition(acquisition);
        a.setEnvergure(envergure);
        a.setPoids(poids);
        a.setRemarques(remarques);
        this.aeronefModifie = a;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnEnregistrer)){
            um.open();
            //Récupération des modifications
            aeronefModifie = new Aeronef();
            onItemSelected(spinnerType,v,1,1);
            buildNewAeronef(editNom.getText().toString(),txtAcquisition.getText().toString(),Integer.parseInt(txtEnvergure.getText().toString()),Integer.parseInt(txtPoids.getText().toString()),txtRemarques.getText().toString());
            aeronefModifie.setType(newType);
            //Fin de la récupération

            um.modifierAeronef(idAeronefSelect+1, aeronefModifie);

            um.close();
            Toast.makeText(this, "Vos modifications ont bien été enregistrées", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ModifAeronefActivity.this, DetailsAeronefActivity.class);
            intent.putExtra("idAeronef", idAeronefSelect);
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
                Intent intent = new Intent(ModifAeronefActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
