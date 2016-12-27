package com.example.whiteknight.laundrysystem;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

/**
 * Created by whiteknight on 26/12/16.
 */

public class NotaPetugasAdapter extends ArrayAdapter<NotaPetugas>{
    private List<NotaPetugas> obj;
    public NotaPetugasAdapter(Context context, int resource, List<NotaPetugas> objects) {
        super(context, resource, objects);
        this.obj = objects;
    }
    public View getView(final int position, View ConvertView,
                        ViewGroup parent) {

        NotaPetugas nota = getItem(position);
        if (ConvertView == null) {
            ConvertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.nota_layout_petugas, parent, false);
        }

        final TextView jml_cucian = (TextView) ConvertView.findViewById(R.id.tvJumlah);
        final TextView paket = (TextView) ConvertView.findViewById(R.id.tvPaket);
        final TextView tgl_masuk = (TextView) ConvertView.findViewById(R.id.tvTanggal);
        final TextView status = (TextView) ConvertView.findViewById(R.id.tvStatus);
        final TextView username = (TextView) ConvertView.findViewById(R.id.tvUsername);
        Button hapus = (Button) ConvertView.findViewById(R.id.btHapus);
        Button status_b = (Button) ConvertView.findViewById(R.id.btStatus);
        hapus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DatabaseHelper helperku = new DatabaseHelper(getContext(),"dbku.db",null,1);
                if (status.getText().toString().equals("Pencucian Selesai")){
                    Calendar c = Calendar.getInstance();
                    int tanggal = c.get(Calendar.DATE);
                    int bulan = c.get(Calendar.MONTH);
                    int tahun = c.get(Calendar.YEAR);
                    final String tangga_final = String.valueOf(tanggal)+"/"+String.valueOf(bulan)+"/"+String.valueOf(tahun);
                    String paketnya = paket.getText().toString();
                    Float uang = Float.valueOf(0);
                    switch(paketnya){
                        case "1":
                            uang = Float.valueOf(jml_cucian.getText().toString())*10000;
                            break;
                        case "2":
                            uang = Float.valueOf(jml_cucian.getText().toString())*15000;
                            break;
                        case "3":
                            uang = Float.valueOf(jml_cucian.getText().toString())*20000;
                            break;
                    }
                    helperku.addTransaksi(Float.valueOf(jml_cucian.getText().toString()),Integer.valueOf(paket.getText().toString()),tangga_final,uang);
                }
                helperku.hapusNota(username.getText().toString(),jml_cucian.getText().toString(),tgl_masuk.getText().toString());
                obj.remove(position);
                notifyDataSetChanged();
            }
        });
        status_b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(getContext());
                View inputnya = li.inflate(R.layout.ganti_status,null);
                AlertDialog.Builder dialognya = new
                        AlertDialog.Builder(getContext());
                dialognya.setView(inputnya);
                final EditText status_baru = (EditText) inputnya.findViewById(R.id.etStatus);
                dialognya
                        .setCancelable(false)
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper helperku = new DatabaseHelper(getContext(),"dbku.db",null,1);
                                helperku.gantiStatusNota(username.getText().toString(),jml_cucian.getText().toString(),tgl_masuk.getText().toString(),status_baru.getText().toString());
                                String status_string="";
                                switch (Integer.valueOf(status_baru.getText().toString())){
                                    case 1:
                                        status_string = "Sedang Dalam Antrian";
                                        break;
                                    case 2:
                                        status_string="Dalam Proses Pencucian";
                                        break;
                                    case 3:
                                        status_string="Dalam Proses Setrika";
                                        break;
                                    case 4:
                                        status_string="Pencucian Selesai";
                                        break;
                                }
                                obj.get(position).setStatus_cucian(status_string);
                                notifyDataSetChanged();
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
        });
        jml_cucian.setText(nota.getJml_cucian());
        paket.setText(nota.getJenis_paket());
        tgl_masuk.setText(nota.getTanggal_masuk());
        status.setText(nota.getStatus_cucian());
        username.setText(nota.getNama_konsumen());
        return ConvertView;

    }



}