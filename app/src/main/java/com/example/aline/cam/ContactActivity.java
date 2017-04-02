package com.example.aline.cam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnRetour, btnEnvoyer;
    TextView cheminAccueil, cheminLeClub, cheminContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcontact);

        btnEnvoyer = (Button) findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(this);

        btnRetour = (Button) findViewById(R.id.btnRetour);
        btnRetour.setOnClickListener(this);

        //Fil d'Arianne
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);
        cheminLeClub = (TextView) findViewById(R.id.cheminLeClub);
        cheminLeClub.setOnClickListener(this);
        cheminContact = (TextView) findViewById(R.id.cheminContact);
        cheminContact.setOnClickListener(this);
        //Fin du fil d'Arianne
    }

    public void contacter(){
        EditText leSujet = (EditText) findViewById(R.id.editSujet);
        EditText leMsg = (EditText) findViewById(R.id.editMsg);
        EditText laAdresse = (EditText) findViewById(R.id.editEmail);
        EditText leNumTel = (EditText) findViewById(R.id.editTel) ;

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"asavreux@ensc.fr"});
        i.putExtra(Intent.EXTRA_SUBJECT, ""+leSujet.getText().toString());
        i.putExtra(Intent.EXTRA_TEXT   , "Message de : "+laAdresse.getText().toString()+"\n"+"Numéro de téléphone : "+leNumTel.getText().toString() + "\n\n"+leMsg.getText().toString());
        try {
            startActivity(Intent.createChooser(i, "Envoi du mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactActivity.this, "Aucune application ne permet d'envoyer cet email.", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(ContactActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(equals(btnRetour)){
            Intent intent =  new Intent(ContactActivity.this, LeClubActivity.class);
            startActivity(intent);
        }
        if(equals(btnEnvoyer)){
            contacter();
            onWindowFocusChanged(true);
        }
        if(v.equals(cheminAccueil)){
            Intent intent = new Intent(ContactActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminLeClub)){
            Intent intent = new Intent(ContactActivity.this, LeClubActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminContact)){
            Intent intent = new Intent(ContactActivity.this, ContactActivity.class);
            startActivity(intent);
        }
    }
}
