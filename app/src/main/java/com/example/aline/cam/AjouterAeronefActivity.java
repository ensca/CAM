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

import java.util.ArrayList;
import java.util.List;


public class AjouterAeronefActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText editNom, editEnvergure, editPoids, editRemarques;
    Button btnAjouter, btnVoirAeronefs;
    UserManager um;
    Spinner spinnerType;
    TextView cheminAccueil, cheminMonEspace, cheminMesAeronefs, cheminAjoutAeronef;
    CalendarView editAcquisition;
    String date;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formajoutaeronef);

        editNom=(EditText) findViewById(R.id.editNom);
        editAcquisition=(CalendarView) findViewById(R.id.editAcquisition);
        editAcquisition.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "/" + i1 + "/" + i;
            }
        });
        editEnvergure=(EditText) findViewById(R.id.editEnvergure);
        editPoids=(EditText) findViewById(R.id.editPoids);
        editRemarques=(EditText) findViewById(R.id.editRemarques);

        um = new UserManager(this);

        //Le type d'aéronef : spinner
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

        btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnAjouter.setOnClickListener(this);

        btnVoirAeronefs = (Button) findViewById(R.id.btnVoirAeronefs);
        btnVoirAeronefs.setOnClickListener(this);

        //Fil d'Arianne
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);

        cheminMonEspace = (TextView) findViewById(R.id.cheminMonEspace);
        cheminMonEspace.setOnClickListener(this);

        cheminMesAeronefs = (TextView) findViewById(R.id.cheminMesAeronefs);
        cheminMesAeronefs.setOnClickListener(this);

        cheminAjoutAeronef = (TextView) findViewById(R.id.cheminAjoutAeronef);
        cheminAjoutAeronef.setOnClickListener(this);
        //Fin du fil d'Arianne
    }

    @Override
    public void onClick(View v) {

        if(v.equals(btnAjouter)) {
            um.open();
            Aeronef a = new Aeronef();
            int idAeronef = um.CompteAeronefs()+ 1;
            String nom = editNom.getText().toString();
            String acquisition = date;
            int envergure = Integer.parseInt(editEnvergure.getText().toString());
            int poids = Integer.parseInt(editPoids.getText().toString());
            String remarques = editRemarques.getText().toString();

            onItemSelected(spinnerType,v,1,1);

            a.setIdAeronef(idAeronef);
            a.setNom(nom);
            a.setType(type);
            a.setAcquisition(acquisition);
            a.setEnvergure(envergure);
            a.setPoids(poids);
            a.setRemarques(remarques);

            um.ajouterAeronef(a);
            um.close();
            Toast.makeText(this, a.getNom()+ " a été ajouté à la liste de vos aéronefs", Toast.LENGTH_LONG).show();
        }

        if(v.equals(btnVoirAeronefs))
        {
            Intent intent = new Intent(AjouterAeronefActivity.this, MesAeronefsActivity.class);
            intent.putExtra("page", 1);
            startActivity(intent);
        }

        if(v.equals(cheminAccueil))
        {
            Intent intent = new Intent(AjouterAeronefActivity.this,MainActivity.class);
            startActivity(intent);
        }

        if(v.equals(cheminMonEspace))
        {
            Intent intent = new Intent(AjouterAeronefActivity.this,MonEspaceActivity.class);
            startActivity(intent);
        }

        if(v.equals(cheminMesAeronefs))
        {
            Intent intent = new Intent(AjouterAeronefActivity.this,MesAeronefsActivity.class);
            intent.putExtra("page", 1);
            startActivity(intent);
        }

        if(v.equals(cheminAjoutAeronef))
        {
            Intent intent = new Intent(AjouterAeronefActivity.this,AjouterAeronefActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String leType = parent.getSelectedItem().toString();
        this.type = leType;
    }
    public void onNothingSelected(AdapterView<?> arg0) {    }

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
                Intent intent = new Intent(AjouterAeronefActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
