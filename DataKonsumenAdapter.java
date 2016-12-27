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

public class DataKonsumenAdapter extends ArrayAdapter<DataKonsumen> {
    DataKonsumenAdapter(Context context, int resource, List<DataKonsumen> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View ConvertView,
                        ViewGroup parent) {

        DataKonsumen data = getItem(position);
        if (ConvertView == null) {
            ConvertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.data_konsumen, parent, false);
        }

        TextView nama = (TextView) ConvertView.findViewById(R.id.tvNama);
        TextView alamat = (TextView) ConvertView.findViewById(R.id.tvAlamat);
        TextView nomor = (TextView) ConvertView.findViewById(R.id.tvNomor);
        TextView username = (TextView) ConvertView.findViewById(R.id.tvUsername);

        nama.setText(data.getNama());
        alamat.setText(data.getAlamat());
        nomor.setText(data.getNomor());
        username.setText(data.getUsername());
        return ConvertView;

    }
}
