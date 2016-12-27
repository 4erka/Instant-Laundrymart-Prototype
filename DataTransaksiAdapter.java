package com.example.whiteknight.laundrysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whiteknight on 27/12/16.
 */

public class DataTransaksiAdapter extends ArrayAdapter<DataTransaksi> {
    public DataTransaksiAdapter(Context context, int resource, List<DataTransaksi> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View ConvertView,
                        ViewGroup parent) {

        DataTransaksi data = getItem(position);
        if (ConvertView == null) {
            ConvertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.data_transaksi, parent, false);
        }

        TextView jumlah = (TextView) ConvertView.findViewById(R.id.tvJumlah);
        TextView paket = (TextView) ConvertView.findViewById(R.id.tvPaket);
        TextView tanggal = (TextView) ConvertView.findViewById(R.id.tvTanggal);
        TextView uang = (TextView) ConvertView.findViewById(R.id.tvUang);

        jumlah.setText(data.getJml_cucian());
        paket.setText(data.getJenis_paket());
        tanggal.setText(data.getTanggal_masuk());
        uang.setText(data.getUang_masuk());
        return ConvertView;

    }
}
