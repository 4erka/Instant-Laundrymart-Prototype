package com.example.whiteknight.laundrysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button konsumen = (Button)findViewById(R.id.btKonsumen);
        konsumen.setOnClickListener(clickHandler);
        Button petugas = (Button)findViewById(R.id.btPetugas);
        petugas.setOnClickListener(clickHandler);
        Button pemilik = (Button)findViewById(R.id.btPemilik);
        pemilik.setOnClickListener(clickHandler);
    }
    View.OnClickListener clickHandler = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btKonsumen:
                    intentRunner(KonsumenMain.class);
                    break;
                case R.id.btPetugas:
                    intentRunner(PetugasMain.class);
                    break;
                case R.id.btPemilik:
                    intentRunner(PemilikMain.class);
                    break;
            }
        }
    };
    private void intentRunner(Class kelasku) {
        Intent intenku = new Intent(getBaseContext(),kelasku);
        startActivityForResult(intenku,0);
    }
}
