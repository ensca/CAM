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

import static com.example.aline.cam.R.id.spinnerAeronef;

public class AjouterVolActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText editHeureDeb, editHeureFin, editLieu, editRemarques;
    Button btnAjouter, btnVoirVols;
    UserManager um;
    CalendarView calendarView;
    String date, nomAeronef;
    int numAeronef;
    Spinner spinnerAeronef;
    TextView cheminAccueil, cheminMonEspace, cheminMesVols, cheminAjoutVol;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_formajoutvol);

            editHeureDeb=(EditText) findViewById(R.id.editHeureDeb);
            editHeureFin=(EditText) findViewById(R.id.editHeureFin);
            editLieu=(EditText) findViewById(R.id.editLieu);
            editRemarques=(EditText) findViewById(R.id.editRemarques);

            um = new UserManager(this);

            //La date du vol : calendarView
            calendarView = (CalendarView) findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                    date = i2 + "/" + i1 + "/" + i;
                }
            });
            //Choix de l'aéronef avec lequel l'utilisateur a volé
            um.open();
            spinnerAeronef = (Spinner) findViewById(R.id.spinnerAeronef);
            List<Aeronef> lesAeroBase = um.TousAeronefs();
            List<String> lesAero = new ArrayList<>(); //Liste des éléments affichés dans le spinner
            for(int i=0; i<lesAeroBase.size();i++) {
                lesAero.add(lesAeroBase.get(i).getNom());
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesAero);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAeronef.setAdapter(dataAdapter);
            um.close();
            //Fin du choix

            btnAjouter = (Button) findViewById(R.id.btnAjouter);
            btnAjouter.setOnClickListener(this);

            btnVoirVols = (Button) findViewById(R.id.btnVoirVols);
            btnVoirVols.setOnClickListener(this);

            //Fil d'Arianne
            cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
            cheminAccueil.setOnClickListener(this);
            cheminMonEspace = (TextView) findViewById(R.id.cheminMonEspace);
            cheminMonEspace.setOnClickListener(this);
            cheminMesVols = (TextView) findViewById(R.id.cheminMesVol);
            cheminMesVols.setOnClickListener(this);
            cheminAjoutVol = (TextView) findViewById(R.id.cheminAjoutVol);
            cheminAjoutVol.setOnClickListener(this);
            //Fin du fil d'Arianne
        }

        @Override
        public void onClick(View v) {

            if(v.equals(btnAjouter)) {
                um.open();
                Vol vol = new Vol();
                int idVol = um.CompteVols()+ 1;
                String heureDeb = editHeureDeb.getText().toString();
                String heureFin = editHeureFin.getText().toString();
                String lieu = editLieu.getText().toString();
                String remarques = editRemarques.getText().toString();

                onItemSelected(spinnerAeronef,v,1,1);

                numAeronef = um.selectIdAeronef(nomAeronef);

                vol.setIdVol(idVol);
                vol.setDate(this.date);
                vol.setIdAeronef(this.numAeronef);
                vol.setHeureDeb(heureDeb);
                vol.setHeureFin(heureFin);
                vol.setLieu(lieu);
                vol.setRemarques(remarques);

                um.ajouterVol(vol);
                um.close();
                Toast.makeText(this, "Ce vol a été ajouté à votre carnet de vols, avec l'aéronef : " + nomAeronef, Toast.LENGTH_LONG).show();
            }

            if(v.equals(btnVoirVols))
            {
                Intent intent = new Intent(AjouterVolActivity.this, MesAeronefsActivity.class);
                intent.putExtra("page",2);
                startActivity(intent);
            }
            if(v.equals(cheminAccueil)){
                Intent intent = new Intent(AjouterVolActivity.this, MainActivity.class);
                startActivity(intent);
            }
            if(v.equals(cheminMonEspace)){
                Intent intent = new Intent(AjouterVolActivity.this, MonEspaceActivity.class);
                startActivity(intent);
            }
            if(v.equals(cheminMesVols)){
                Intent intent = new Intent(AjouterVolActivity.this, MesAeronefsActivity.class);
                intent.putExtra("page",2);
                startActivity(intent);
            }
            if(v.equals(cheminAjoutVol)){
                Intent intent = new Intent(AjouterVolActivity.this, AjouterAeronefActivity.class);
                startActivity(intent);
            }
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String nomAero = parent.getSelectedItem().toString();
        this.nomAeronef = nomAero;
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
                    Intent intent = new Intent(com.example.aline.cam.AjouterVolActivity.this, MonEspaceActivity.class);
                    startActivity((intent));
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }


