package com.example.whiteknight.laundrysystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PetugasHome extends AppCompatActivity {
    private ListView listku;
    private DatabaseHelper helperku;
    private String username_petugas;
    private NotaPetugasAdapter adapterku;
    private ArrayList<NotaPetugas> listNota;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petugas_home);
        Intent myIntent = getIntent(); // gets the previously created intent
        username_petugas = myIntent.getStringExtra("username");
        TextView nama = (TextView)findViewById(R.id.tvUsername);
        Button tambah = (Button)findViewById(R.id.btTambah);
        Button data = (Button)findViewById(R.id.btData);
        Button gaji = (Button)findViewById(R.id.btGaji);
        Button transaksi = (Button)findViewById(R.id.btTransaksi);
        listku = (ListView) findViewById(R.id.lv1);
        tambah.setOnClickListener(clickHandler);
        data.setOnClickListener(clickHandler);
        gaji.setOnClickListener(clickHandler);
        transaksi.setOnClickListener(clickHandler);
        nama.setText(username_petugas);
        helperku = new DatabaseHelper(this,"dbku.db",null,1);
        listNota = helperku.loadNotaPetugas(username_petugas);
        adapterku = new NotaPetugasAdapter(this,0,listNota);
        listku.setAdapter(adapterku);

    }

    View.OnClickListener clickHandler = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btTambah:
                    addNota();
                    break;
                case R.id.btData:
                    lihatDataKonsumen();
                    break;
                case R.id.btGaji:
                    lihatGaji();
                    break;
                case R.id.btTransaksi:
                    lihatTransaksi();
                    break;
            }
        }
    };
    public void lihatDataKonsumen(){
        LayoutInflater li = LayoutInflater.from(this);
        View inputnya = li.inflate(R.layout.list_data_konsumen,null);

        AlertDialog.Builder dialognya = new
                AlertDialog.Builder(this);
        dialognya.setView(inputnya);
        ListView lv = (ListView) inputnya.findViewById(R.id.lv1);
        ArrayList<DataKonsumen> dataku = helperku.loadDataKonsumen();
        DataKonsumenAdapter myAdapter = new DataKonsumenAdapter(this,0,dataku);
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
    public void lihatGaji(){
        LayoutInflater li = LayoutInflater.from(this);
        View inputnya = li.inflate(R.layout.list_data_gaji,null);

        AlertDialog.Builder dialognya = new
                AlertDialog.Builder(this);
        dialognya.setView(inputnya);
        ListView lv = (ListView) inputnya.findViewById(R.id.lv1);
        ArrayList<DataGaji> dataku = helperku.loadDataGaji(username_petugas);
        DataGajiAdapter myAdapter = new DataGajiAdapter(this,0,dataku);
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
    public void lihatTransaksi(){
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


    public void addNota(){
        LayoutInflater li = LayoutInflater.from(this);
        View inputnya = li.inflate(R.layout.nota_buat,null);

        AlertDialog.Builder dialognya = new
                AlertDialog.Builder(this);
        dialognya.setView(inputnya);

        final EditText username = (EditText) inputnya.findViewById(R.id.etUsername);
        final EditText jumlah = (EditText) inputnya.findViewById(R.id.etJumlah);
        final EditText paket = (EditText) inputnya.findViewById(R.id.etPaket);
        Calendar c = Calendar.getInstance();
        int tanggal = c.get(Calendar.DATE);
        int bulan = c.get(Calendar.MONTH);
        int tahun = c.get(Calendar.YEAR);
        final String tangga_final = String.valueOf(tanggal)+"/"+String.valueOf(bulan)+"/"+String.valueOf(tahun);
        final int status = 1;
        dialognya
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isNotExist = helperku.cekRegisKonsumen(username.getText().toString());
                        if(isNotExist){
                            Toast.makeText(getBaseContext(),"Username Tidak Ada!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            helperku.addNota(username.getText().toString(),username_petugas,Float.valueOf(jumlah.getText().toString()),Integer.valueOf(paket.getText().toString()),status,tangga_final);
                            listNota = helperku.loadNotaPetugas(username_petugas);
                            adapterku = new NotaPetugasAdapter(getBaseContext(),0,listNota);
                            listku.setAdapter(adapterku);
                            Toast.makeText(getBaseContext(),"Nota Berhasil Ditambah!",Toast.LENGTH_SHORT).show();
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
