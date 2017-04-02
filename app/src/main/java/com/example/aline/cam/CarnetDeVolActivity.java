package com.example.aline.cam;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CarnetDeVolActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnListeTousVols, btnListeParAeronefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnetdevol);

        btnListeTousVols = (Button) findViewById(R.id.btnListeTousVol);
        btnListeTousVols.setOnClickListener(this);
        btnListeParAeronefs = (Button) findViewById(R.id.btnListeParAeronef);
        btnListeParAeronefs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btnListeTousVols)){
            Intent intent = new Intent(CarnetDeVolActivity.this,MesAeronefsActivity.class);
            intent.putExtra("page", 2);
            startActivity(intent);
        }
        if(v.equals(btnListeParAeronefs)){
            Intent intent = new Intent(CarnetDeVolActivity.this,ListeVolsParAeronefActivity.class);
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
                Intent intent = new Intent(CarnetDeVolActivity.this, MonEspaceActivity.class);
                startActivity((intent));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
