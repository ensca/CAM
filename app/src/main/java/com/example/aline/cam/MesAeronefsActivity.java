package com.example.aline.cam;


import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MesAeronefsActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listeAeronefs;
    ImageButton btnAjouterAeronef, btnAjouterVol;
    UserManager um;
    int page;
    int idAeronefSelect = 0;
    TextView cheminMesAeronefs, cheminMonEspace, cheminAccueil, txtVol;
    int envergure = 0;
    int poids = 0;
    String remarques = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesaeronefs);
        page = getIntent().getIntExtra("page", 0);

        um = new UserManager(this);

        //Si on souhaite afficher la liste des aéronefs et en ajouter
        if (page == 1) {
            listeAeronefs = (ListView) findViewById(R.id.listeAeronefs);

            /*um.open();
            um.insertionAeronef();
            um.close();*/

            // Affichage de la liste des aéronefs de l'utilisateur
            um.open();
            final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            SimpleAdapter mSchedule;
            final ArrayList<Aeronef> lesAeronefs = um.TousAeronefs();

            for (int i = 0; i < lesAeronefs.size(); i++) {

                map = new HashMap<String, String>();
                map.put("titre", lesAeronefs.get(i).getNom().toString());
                map.put("description", lesAeronefs.get(i).getType().toString());
                //Toast.makeText(getBaseContext(), "lesAeronefs.get(i).getType().toString() : " + lesAeronefs.get(i).getType().toString(), Toast.LENGTH_LONG).show();
                if (lesAeronefs.get(i).getType().equals("avion électrique"))
                    map.put("img", String.valueOf(R.drawable.electricplane));
                else if (lesAeronefs.get(i).getType().equals("avion essence"))
                    map.put("img", String.valueOf(R.drawable.oilplane));
                else if (lesAeronefs.get(i).getType().equals("biplan"))
                    map.put("img", String.valueOf(R.drawable.biplane));
                else if (lesAeronefs.get(i).getType().equals("drone"))
                    map.put("img", String.valueOf(R.drawable.drone));
                else if (lesAeronefs.get(i).getType().equals("hélicoptère"))
                    map.put("img", String.valueOf(R.drawable.helicopter));
                else if (lesAeronefs.get(i).getType().equals("planeur"))
                    map.put("img", String.valueOf(R.drawable.airplane));
                map.put("acquisition", "Date d'acquisition : " + lesAeronefs.get(i).getAcquisition().toString());
                /*envergure = lesAeronefs.get(i).getEnvergure();
                poids = lesAeronefs.get(i).getPoids();
                remarques = lesAeronefs.get(i).getRemarques().toString();*/
                listItem.add(map);
            }
            mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.affichageitems,
                    new String[]{"img", "titre", "description", "acquisition"}, new int[]{R.id.img, R.id.titre, R.id.description, R.id.acquisition});

            //On attribut à notre listView l'adapter que l'on vient de créer
            listeAeronefs.setAdapter(mSchedule);

            //Enfin on met un écouteur d'évènement sur notre listView
            listeAeronefs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                @SuppressWarnings("unchecked")
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                    HashMap<String, String> map = (HashMap<String, String>) listeAeronefs.getItemAtPosition(position);
                    Intent intent = new Intent(MesAeronefsActivity.this, DetailsAeronefActivity.class);
                    um.open();
                    idAeronefSelect = um.selectIdAeronef(map.get("titre"));
                    um.close();
                    intent.putExtra("idAeronef", idAeronefSelect-1);
                    startActivity(intent);
                    //on créer une boite de dialogue
                    /*AlertDialog.Builder adb = new AlertDialog.Builder(MesAeronefsActivity.this);
                    //on attribut un titre à notre boite de dialogue
                    adb.setTitle(map.get("titre"));
                    //on insère l'image de l'aéronef sélectionné à notre boite de dialogue
                    adb.setIcon(R.drawable.aeroplane);
                    if(map.get("description").equals("avion électrique"))
                        adb.setIcon(R.drawable.electricplane);
                    else if(map.get("description").equals("avion essence"))
                        adb.setIcon(R.drawable.oilplane);
                    else if(map.get("description").equals("biplan"))
                        adb.setIcon(R.drawable.biplane);
                    else if(map.get("description").equals("drone"))
                        adb.setIcon(R.drawable.drone);
                    else if(map.get("description").equals("hélicoptère"))
                        adb.setIcon(R.drawable.helicopter);
                    else if(map.get("description").equals("planeur"))
                        adb.setIcon(R.drawable.airplane);
                    //on insère le nom de l'aéronef à notre boite de dialogue
                    adb.setMessage("Type : " + map.get("description") + "\nDate d'acquisition : " + map.get("acquisition") + "\nEnvergure : " + envergure + "\nPoids : " + poids + "\nRemarques : " + remarques);
                    //on indique que l'on veut le bouton ok à notre boite de dialogue
                    adb.setPositiveButton("Ok", null);
                    //on affiche la boite de dialogue
                    adb.show();*/
                }
            });
            um.close();
            // Fin affichage liste aéronefs

            // Ajout d'un aéronef dans la base de données
            btnAjouterAeronef = (ImageButton) findViewById(R.id.btnAjouterAeronef);
            btnAjouterAeronef.setOnClickListener(this);
            // Fin ajout d'un aéronef
        }
        //Si on souhaite afficher les vols et en ajouter
        if (page == 2) {                    //Pour afficher TOUS LES VOLS
            listeAeronefs = (ListView) findViewById(R.id.listeAeronefs);        //On réutilise la même structure que pour l'affichage des aéronefs donc la même ListView

            um.open();
            um.insertionVol();
            um.close();

            // Affichage de la liste des vols enregistrés par l'utilisateur
            um.open();
            ArrayList<Vol> lesVols = um.TousVols();
            String[] newVols = new String[lesVols.size()];
            for (int i = 0; i < newVols.length; i++) {
                newVols[i] = "Id : " + lesVols.get(i).getIdVol() + "\nDate : " + lesVols.get(i).getDate().toString() + "\nAéronef utilisé : " + um.selectNomAeronef(lesVols.get(i).getIdAeronef()) + "\nLieu : " + lesVols.get(i).getLieu().toString() + "\nHeure de début du vol : " + lesVols.get(i).getHeureDeb().toString() + "\nHeure de fin du vol : " + lesVols.get(i).getHeureFin().toString();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newVols);
            listeAeronefs.setAdapter(adapter);
            um.close();
            // Fin affichage liste des vols

            // Ajout d'un aéronef dans la base de données
            btnAjouterVol = (ImageButton) findViewById(R.id.btnAjouterAeronef);
            btnAjouterVol.setOnClickListener(this);
            // Fin ajout d'un aéronef

            //Modifications des TextView
            cheminMesAeronefs = (TextView) findViewById(R.id.cheminMesAeronefs);
            cheminMesAeronefs.setText(">Mon carnet de vol");
            txtVol = (TextView) findViewById(R.id.txtMesAeronefs);
            txtVol.setText("Mon carnet de vol");
            //Fin de modification des TextView

            //Fil d'Arianne
            cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
            cheminAccueil.setOnClickListener(this);
            cheminMesAeronefs.setOnClickListener(this);
            cheminMonEspace = (TextView) findViewById(R.id.cheminMonEspace);
            cheminMonEspace.setOnClickListener(this);
            //Fin du fil d'Arianne
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnAjouterAeronef)) {
            Intent intent = new Intent(MesAeronefsActivity.this, AjouterAeronefActivity.class);
            startActivity(intent);
        }
        if (v.equals(btnAjouterVol)) {
            Intent intent = new Intent(MesAeronefsActivity.this, AjouterVolActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminAccueil)) {
            Intent intent = new Intent(MesAeronefsActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminMonEspace)) {
            Intent intent = new Intent(MesAeronefsActivity.this, MonEspaceActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminMesAeronefs)) {
            Intent intent = new Intent(MesAeronefsActivity.this, MesAeronefsActivity.class);
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
                Intent intent = new Intent(MesAeronefsActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
