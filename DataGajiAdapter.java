package com.example.whiteknight.laundrysystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by whiteknight on 26/12/16.
 */

public class DataGajiAdapter extends ArrayAdapter<DataGaji> {
    public DataGajiAdapter(Context context, int resource, List<DataGaji> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View ConvertView,
                        ViewGroup parent) {

        DataGaji data = getItem(position);
        if (ConvertView == null) {
            ConvertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.data_gaji, parent, false);
        }

        TextView nama = (TextView) ConvertView.findViewById(R.id.tvNama);
        TextView periode = (TextView) ConvertView.findViewById(R.id.tvPeriode);
        TextView tanggal = (TextView) ConvertView.findViewById(R.id.tvTanggal);
        TextView jumlah = (TextView) ConvertView.findViewById(R.id.tvJumlah);

        nama.setText(data.getUsername());
        periode.setText(data.getPeriode_gaji());
        tanggal.setText(data.getTanggal_kirim());
        jumlah.setText(data.getJumlah_gaji());
        return ConvertView;

    }
}
