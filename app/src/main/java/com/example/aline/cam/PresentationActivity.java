package com.example.aline.cam;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;

public class PresentationActivity extends AppCompatActivity implements View.OnClickListener{
    private int page;
    TextView cheminAccueil, cheminLeClub, cheminPresentation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        page = getIntent().getIntExtra("page",0);
        if (page==1){
            TextView chemin = (TextView) findViewById(R.id.cheminPresentation);
            chemin.setText(">Présentation");

            TextView titlePresentation = (TextView) findViewById(R.id.titlePresentation);
            titlePresentation.setText("Presentation du club");

            TextView textPresentation = (TextView) findViewById(R.id.textPresentation);
            textPresentation.setText(R.string.presentation);
        }
        else {
            if (page == 2) {
                TextView chemin = (TextView) findViewById(R.id.cheminPresentation);
                chemin.setText(">Nos rendez-vous");

                TextView titlePresentation = (TextView) findViewById(R.id.titlePresentation);
                titlePresentation.setText("Nos rendez-vous");

                TextView textPresentation = (TextView) findViewById(R.id.textPresentation);
                textPresentation.setText(R.string.TexteRdv);
            }
            else
                {
                    if (page==3){
                        TextView chemin = (TextView) findViewById(R.id.cheminPresentation);
                        chemin.setText(">Nous contacter");

                        TextView titlePresentation = (TextView) findViewById(R.id.titlePresentation);
                        titlePresentation.setText("Nous contacter");

                        TextView textPresentation = (TextView) findViewById(R.id.textPresentation);
                        textPresentation.setText("");
                    }
                    else
                    {
                        if (page==4){
                            TextView chemin = (TextView) findViewById(R.id.cheminPresentation);
                            chemin.setText(">Tarifs, réglement, statuts, licence");

                            TextView titlePresentation = (TextView) findViewById(R.id.titlePresentation);
                            titlePresentation.setText("Tarifs, réglement, statuts et licence");

                            TextView textPresentation = (TextView) findViewById(R.id.textPresentation);
                            textPresentation.setText("");
                        }
                        else
                        {
                            if (page==5){
                                TextView chemin = (TextView) findViewById(R.id.cheminPresentation);
                                chemin.setText(">Réglement intérieur");

                                TextView titlePresentation = (TextView) findViewById(R.id.titlePresentation);
                                titlePresentation.setText("Réglement intérieur du club");

                                TextView textPresentation = (TextView) findViewById(R.id.textPresentation);
                                textPresentation.setText(R.string.reglement);
                            }
                        }
                    }
                }
            }

        //Fil d'Arianne
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);
        cheminLeClub = (TextView) findViewById(R.id.cheminLeClub);
        cheminLeClub.setOnClickListener(this);
        cheminPresentation = (TextView) findViewById(R.id.cheminPresentation);
        cheminPresentation.setOnClickListener(this);
        //Fin du fil d'Arianne
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
                Intent intent = new Intent(PresentationActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(cheminAccueil)){
            Intent intent = new Intent(PresentationActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminLeClub)){
            Intent intent = new Intent(PresentationActivity.this, LeClubActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminPresentation)){
            Intent intent = new Intent(PresentationActivity.this, PresentationActivity.class);
            startActivity(intent);
        }
    }
}

