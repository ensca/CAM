package com.example.aline.cam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    UserManager um;
    ImageButton btnGalerie, btnMeteo, btnTechnique;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_main);

        um = new UserManager(this);

        ImageButton btnLeClub = (ImageButton) findViewById(R.id.btnLeClub);
        btnLeClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeClubActivity.class);
                startActivity(intent);
            }
        });

        btnGalerie = (ImageButton) findViewById(R.id.btnGalerie);
        btnGalerie.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.equals(btnGalerie)) {
            um.open();
            String chemin = um.supprimerDB();
            um.close();
            Toast.makeText(this, "bdd supprimée : " + chemin, Toast.LENGTH_LONG).show();
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
               Intent intent = new Intent(MainActivity.this, MonEspaceActivity.class);
               startActivity((intent));
               return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
