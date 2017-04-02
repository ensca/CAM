package com.example.aline.cam;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;


public class LeClubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leclub);

        Button btnPresentation = (Button) findViewById(R.id.btnPresentation);
        btnPresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeClubActivity.this, PresentationActivity.class);
                intent.putExtra("page",1);
                startActivity(intent);
            }
        });

        Button btnRdv = (Button) findViewById(R.id.btnRdv);
        btnRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeClubActivity.this, PresentationActivity.class);
                intent.putExtra("page",2);
                startActivity(intent);
            }
        });

        Button btnContact = (Button) findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeClubActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        Button btnRglmtStat = (Button) findViewById(R.id.btnRegles);
        btnRglmtStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeClubActivity.this, TarifsRglmt_activity.class);
                startActivity(intent);
            }
        });

        TextView cheminAccueil = (TextView) findViewById(R.id.cheminAccueil);
        cheminAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeClubActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView cheminleClub = (TextView) findViewById(R.id.cheminleClub);
        cheminleClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeClubActivity.this, LeClubActivity.class);
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(LeClubActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
