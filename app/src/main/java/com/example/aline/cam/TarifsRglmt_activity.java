package com.example.aline.cam;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TarifsRglmt_activity extends AppCompatActivity implements View.OnClickListener {
    Button btnRglmt, btnTarifs, btnStatuts, btnCourrier;
    TextView cheminAccueil, cheminLeClub, cheminTarifRglmt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifrglmtstatutslicence);

        btnRglmt = (Button) findViewById(R.id.btnRglmt);
        btnRglmt.setOnClickListener(this);

        //Fil d'Arianne
        cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(this);
        cheminLeClub = (TextView) findViewById(R.id.cheminLeClub);
        cheminLeClub.setOnClickListener(this);
        cheminTarifRglmt = (TextView) findViewById(R.id.cheminTarifRglmtStatutsLicence);
        cheminTarifRglmt.setOnClickListener(this);
        //Fin du fil d'Arianne
    }

    /*private boolean copyAssetToTempFile(String nomFichierAsset,
                                        String nomFichierTemp) {
        boolean result = true;

        try {
            byte[] buffer = new byte[512];
            FileOutputStream fos = openFileOutput(nomFichierTemp, MODE_PRIVATE);

            InputStream is = getAssets().open(nomFichierAsset);
            int bytesRead = is.read(buffer);
            while (bytesRead > 0) {
                fos.write(buffer, 0, bytesRead);
                bytesRead = is.read(buffer);
            }
            fos.close();
            is.close();

        } catch (FileNotFoundException e) {
            // Cas d'erreur de création de fichier
            Log.d("xx", "Erreur creation fichier ", e);
            result = false;
        } catch (IOException e) {
            // Cas d'erreur de lecture de fichier
            Log.d("xx", "Erreur lecture fichier", e);
            result = false;
        }
        return result;
    }*/

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
                Intent intent = new Intent(TarifsRglmt_activity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnRglmt)){
            Intent intent = new Intent(TarifsRglmt_activity.this, PresentationActivity.class);
            intent.putExtra("page",5);
            startActivity(intent);
        }
        if(v.equals(cheminAccueil)){
            Intent intent = new Intent(TarifsRglmt_activity.this, MainActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminLeClub)){
            Intent intent = new Intent(TarifsRglmt_activity.this, LeClubActivity.class);
            startActivity(intent);
        }
        if(v.equals(cheminTarifRglmt)){
            Intent intent = new Intent(TarifsRglmt_activity.this, TarifsRglmt_activity.class);
            startActivity(intent);
        }
    }
}
