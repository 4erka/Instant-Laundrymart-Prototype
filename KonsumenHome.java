package com.example.whiteknight.laundrysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class KonsumenHome extends AppCompatActivity {
    private ListView listku;
    private DatabaseHelper helperku;
    private String username_konsumen;
    private NotaKonsumenAdapter adapterku;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsumen_home);
        Intent myIntent = getIntent(); // gets the previously created intent
        String username_konsumen = myIntent.getStringExtra("username");
        TextView nama = (TextView)findViewById(R.id.tvUsername);
        nama.setText(username_konsumen);
        listku = (ListView) findViewById(R.id.lv1);
        helperku = new DatabaseHelper(this,"dbku.db",null,1);
        ArrayList<NotaKonsumen> listNota = helperku.loadNotaKonsumen(username_konsumen);
        adapterku = new NotaKonsumenAdapter(this,0,listNota);
        listku.setAdapter(adapterku);

    }
}
