package com.example.whiteknight.laundrysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.whiteknight.laundrysystem.NotaKonsumen;

import java.util.List;

/**
 * Created by whiteknight on 25/12/16.
 */

public class NotaKonsumenAdapter extends ArrayAdapter<NotaKonsumen> {
    NotaKonsumenAdapter(Context context, int resource, List<NotaKonsumen> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View ConvertView,
                        ViewGroup parent) {

        NotaKonsumen nota = getItem(position);
        if (ConvertView == null) {
            ConvertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.nota_layout_konsumen, parent, false);
        }

        TextView jml_cucian = (TextView) ConvertView.findViewById(R.id.tvJumlah);
        TextView paket = (TextView) ConvertView.findViewById(R.id.tvPaket);
        TextView tgl_masuk = (TextView) ConvertView.findViewById(R.id.tvTanggal);
        TextView status = (TextView) ConvertView.findViewById(R.id.tvStatus);

        jml_cucian.setText(nota.getJml_cucian());
        paket.setText(nota.getJenis_paket());
        tgl_masuk.setText(nota.getTanggal_masuk());
        status.setText(nota.getStatus_cucian());
        return ConvertView;

    }
}