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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListeVolsParAeronefActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    TextView cheminAccueil, cheminMonEspace, cheminCarnetVol, cheminListeParAeronef;
    TextView existVol;
    Spinner spinnerAeronef;
    UserManager um;
    String nomAeronef;
    ListView listeVols;
    Button btnAfficher;
    ImageButton btnAjouterVol;

    int idVolSelect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeparaeronef);

        um = new UserManager(this);

        btnAfficher = (Button) findViewById(R.id.btnAfficher);
        btnAfficher.setOnClickListener(this);

        btnAjouterVol = (ImageButton) findViewById(R.id.btnAjouterVol);
        btnAjouterVol.setOnClickListener(this);

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

        listeVols = (ListView) findViewById(R.id.listeVols);    //Liste view dans laquelle la liste des vols s'affiche
        existVol = (TextView) findViewById(R.id.existVol);      //TextView qui informe l'utilisateur lorsqu'aucun vol n'existe pour l'aéronef sélectionné

        //Fil d'Arianne
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);
        cheminMonEspace = (TextView) findViewById(R.id.cheminMonEspace);
        cheminMonEspace.setOnClickListener(this);
        cheminCarnetVol = (TextView) findViewById(R.id.cheminCarnetDeVol);
        cheminCarnetVol.setOnClickListener(this);
        cheminListeParAeronef = (TextView) findViewById(R.id.cheminListeParAeronef);
        cheminListeParAeronef.setOnClickListener(this);
        //Fin du fil d'Arianne
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnAfficher)){
            onItemSelected(spinnerAeronef,v,1,1);
        }
        if(v.equals(btnAjouterVol)){
            Intent intent = new Intent(ListeVolsParAeronefActivity.this,AjouterVolActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminAccueil)){
            Intent intent = new Intent(ListeVolsParAeronefActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminMonEspace)){
            Intent intent = new Intent(ListeVolsParAeronefActivity.this, MonEspaceActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminCarnetVol)){
            Intent intent = new Intent(ListeVolsParAeronefActivity.this, CarnetDeVolActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminListeParAeronef)){
            Intent intent = new Intent(ListeVolsParAeronefActivity.this, ListeVolsParAeronefActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String nomAero = parent.getSelectedItem().toString();
        this.nomAeronef = nomAero;
        boolean boolexistVol = false;

        // Affichage de la liste des vols effectués avec l'aéronef sélectionné
        um.open();
        final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        SimpleAdapter mSchedule;

        final ArrayList<Aeronef> lesAeronefs = um.TousAeronefs();
        final ArrayList<Vol> lesVols = um.TousVols();

        int idAeroSelect = um.selectIdAeronef(nomAero);

        //Faire la liste des vols de l'aéronef concerné au lieu de chercher dans tous les vols.
        //Faire une fonctions userManager

        for (int i = 0; i < lesVols.size(); i++) {
            if (lesVols.get(i).getIdAeronef() == idAeroSelect) {
                boolexistVol = true;
                existVol.setText("");
                idVolSelect = lesVols.get(i).getIdVol();

                map = new HashMap<String, String>();    //Pour garder la disposition qui existe déjà pour la liste des aéronefs, on fait les associations suivantes:
                map.put("titre", lesVols.get(idVolSelect-1).getDate().toString());                  //titre = date du vol
                map.put("description", lesVols.get(idVolSelect-1).getHeureDeb().toString());        //description = heure de début du vol
                map.put("acquisition", lesVols.get(idVolSelect-1).getLieu().toString());            //acquisition = lieu de vol
                if (lesAeronefs.get(idAeroSelect-1).getType().equals("avion électrique"))
                    map.put("img", String.valueOf(R.drawable.electricplane));
                else if (lesAeronefs.get(idAeroSelect-1).getType().equals("avion essence"))
                    map.put("img", String.valueOf(R.drawable.oilplane));
                else if (lesAeronefs.get(idAeroSelect-1).getType().equals("biplan"))
                    map.put("img", String.valueOf(R.drawable.biplane));
                else if (lesAeronefs.get(idAeroSelect-1).getType().equals("drone"))
                    map.put("img", String.valueOf(R.drawable.drone));
                else if (lesAeronefs.get(idAeroSelect-1).getType().equals("hélicoptère"))
                    map.put("img", String.valueOf(R.drawable.helicopter));
                else if (lesAeronefs.get(idAeroSelect-1).getType().equals("planeur"))
                    map.put("img", String.valueOf(R.drawable.airplane));
                listItem.add(map);
            }
        }

    mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.affichageitems,
            new String[]{"img", "titre", "description", "acquisition"}, new int[]{R.id.img, R.id.titre, R.id.description, R.id.acquisition});

    listeVols.setAdapter(mSchedule);

    listeVols.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        @SuppressWarnings("unchecked")
        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
            HashMap<String, String> map = (HashMap<String, String>) listeVols.getItemAtPosition(position);
            Intent intent = new Intent(ListeVolsParAeronefActivity.this, DetailsVolActivity.class);
            intent.putExtra("idVolIntent", idVolSelect);
            startActivity(intent);
        }
    });
        um.close();
        if(boolexistVol == false){
            existVol.setText("Aucun vol n'a été enregistré avec cet aéronef");
        }
        // Fin affichage liste vols
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
                Intent intent = new Intent(ListeVolsParAeronefActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
