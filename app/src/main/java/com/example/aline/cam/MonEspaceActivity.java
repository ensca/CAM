package com.example.aline.cam;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;

public class MonEspaceActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnMesAeronefs, btnMonCarnetVol, btnRetourAccueil;
    TextView cheminAccueil, cheminMesEspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monespace);

        btnMesAeronefs = (Button) findViewById(R.id.btnMesAeronefs);
        btnMesAeronefs.setOnClickListener(this);

        btnRetourAccueil = (Button) findViewById(R.id.btnRetourAccueil);
        btnRetourAccueil.setOnClickListener(this);

        btnMonCarnetVol = (Button) findViewById(R.id.btnMonCarnetVol);
        btnMonCarnetVol.setOnClickListener(this);

        //Fil d'Arianne
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);
        cheminMesEspace = (TextView) findViewById(R.id.cheminMonEspace);
        cheminMesEspace.setOnClickListener(this);
        //Fin du fil d'Arianne
    }

    @Override
    public void onClick(View v)
    {
        if (v.equals(btnMesAeronefs))
        {
            Intent intent = new Intent(MonEspaceActivity.this, MesAeronefsActivity.class);
            intent.putExtra("page",1);
            startActivity(intent);
        }
        if (v.equals(btnMonCarnetVol))
        {
            Intent intent = new Intent(MonEspaceActivity.this, CarnetDeVolActivity.class);
            startActivity(intent);
        }
        if (v.equals(btnRetourAccueil))
        {
            Intent intent = new Intent(MonEspaceActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminAccueil))
        {
            Intent intent = new Intent(MonEspaceActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (v.equals(cheminMesEspace))
        {
            Intent intent = new Intent(MonEspaceActivity.this, MonEspaceActivity.class);
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
                Intent intent = new Intent(MonEspaceActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
