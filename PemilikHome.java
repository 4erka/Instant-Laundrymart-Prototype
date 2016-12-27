package com.example.whiteknight.laundrysystem;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class PemilikHome extends AppCompatActivity {
    private DatabaseHelper helperku;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik_home);
        Button gaji = (Button) findViewById(R.id.btGaji);
        gaji.setOnClickListener(clickHandler);
        Button transaksi = (Button) findViewById(R.id.btTransaksi);
        transaksi.setOnClickListener(clickHandler);
        helperku = new DatabaseHelper(this,"dbku.db",null,1);
    }

    View.OnClickListener clickHandler = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btGaji:
                    addGaji();
                    break;
                case R.id.btTransaksi:
                    lihatTransaksi();
                    break;
            }
        }
    };
    private void lihatTransaksi(){
        LayoutInflater li = LayoutInflater.from(this);
        View inputnya = li.inflate(R.layout.list_data_transaksi,null);

        AlertDialog.Builder dialognya = new
                AlertDialog.Builder(this);
        dialognya.setView(inputnya);
        ListView lv = (ListView) inputnya.findViewById(R.id.lv1);
        ArrayList<DataTransaksi> dataku = helperku.loadDataTransaksi();
        DataTransaksiAdapter myAdapter = new DataTransaksiAdapter(this,0,dataku);
        lv.setAdapter(myAdapter);
        dialognya
                .setView(inputnya)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                });
        dialognya.show();


    }
    private void addGaji(){
        LayoutInflater li = LayoutInflater.from(this);
        View inputnya = li.inflate(R.layout.form_gaji,null);

        AlertDialog.Builder dialognya = new
                AlertDialog.Builder(this);
        dialognya.setView(inputnya);

        final EditText username = (EditText) inputnya.findViewById(R.id.etUsername);
        final EditText jumlah = (EditText) inputnya.findViewById(R.id.etJumlah);
        final EditText periode = (EditText) inputnya.findViewById(R.id.etPeriode);
        final EditText tanggal = (EditText) inputnya.findViewById(R.id.etTanggal);
        dialognya
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isNotExist = helperku.cekRegisPetugas(username.getText().toString());
                        if(isNotExist){
                            Toast.makeText(getBaseContext(),"Username Tidak Ada!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            helperku.addGaji(username.getText().toString(),periode.getText().toString(),tanggal.getText().toString(),Integer.valueOf(jumlah.getText().toString()));
                            Toast.makeText(getBaseContext(),"Konfirmasi Gaji Berhasil Ditambah!",Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialognya.show();


    }




    }

