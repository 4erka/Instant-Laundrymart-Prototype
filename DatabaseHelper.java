package com.example.whiteknight.laundrysystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by whiteknight on 19/12/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dbku) {
        dbku.execSQL("create table if not exists data_konsumen(username TEXT, password TEXT, nama TEXT, nomorhp TEXT, alamat TEXT)" );
        dbku.execSQL("create table if not exists data_petugas(username TEXT, password TEXT, nama TEXT, nomorhp TEXT, alamat TEXT, rekno TEXT)");
        dbku.execSQL("create table if not exists data_nota(username_konsumen TEXT, username_petugas TEXT, jml_cucian REAL, jenis_paket INTEGER, status_cucian INTEGER, tanggal_masuk TEXT)");
        dbku.execSQL("create table if not exists data_gaji(username_pegawai TEXT, periode_gaji TEXT, tanggal_kirim TEXT, jumlah_gaji REAL)");
        dbku.execSQL("create table if not exists data_transaksi(jml_cucian REAL, jenis_paket INTEGER, tanggal_masuk TEXT, uang_masuk REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addKonsumen(String username, String password, String nama, String nomorhp, String alamat){
        SQLiteDatabase dbku = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("nama", nama);
        cv.put("nomorhp", nomorhp);
        cv.put("alamat", alamat);
        dbku.insert("data_konsumen",null,cv);
    }
    public void addPetugas(String username, String password, String nama, String nomorhp, String alamat, String norek){
        SQLiteDatabase dbku = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("nama", nama);
        cv.put("nomorhp", nomorhp);
        cv.put("alamat", alamat);
        cv.put("rekno",norek);
        dbku.insert("data_petugas",null,cv);
    }
    public void addNota(String username_konsumen, String username_petugas, Float jml_cucian, Integer jenis_paket, Integer status_cucian,String tanggal_masuk){
        SQLiteDatabase dbku = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username_konsumen", username_konsumen);
        cv.put("username_petugas",username_petugas );
        cv.put("jml_cucian", jml_cucian);
        cv.put("jenis_paket", jenis_paket);
        cv.put("status_cucian", status_cucian);
        cv.put("tanggal_masuk", tanggal_masuk);
        dbku.insert("data_nota",null,cv);
    }
    public void addGaji(String username, String periode_gaji, String tanggal_kirim, Integer jumlah_gaji){
        SQLiteDatabase dbku = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username_pegawai", username);
        cv.put("periode_gaji",periode_gaji );
        cv.put("tanggal_kirim", tanggal_kirim);
        cv.put("jumlah_gaji", jumlah_gaji);
        dbku.insert("data_gaji",null,cv);
    }
    public void addTransaksi(Float jml_cucian , Integer jenis_paket, String tanggal_masuk, Float uang_masuk){
        SQLiteDatabase dbku = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("jml_cucian", jml_cucian);
        cv.put("jenis_paket",jenis_paket );
        cv.put("tanggal_masuk", tanggal_masuk);
        cv.put("uang_masuk", uang_masuk);
        dbku.insert("data_transaksi",null,cv);
    }
    public boolean cekRegisKonsumen(String username){
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_konsumen where username='"+username+"'",null);
        if(cur.getCount() > 0) {
            return false;
        }
        else return true;

    }
    public boolean cekRegisPetugas(String username){
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_petugas where username='"+username+"'",null);
        if(cur.getCount() > 0) {
            return false;
        }
        else return true;

    }
    public boolean cekLoginKonsumen(String username, String password){
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_konsumen where username='"+username+"' and password = '"+password+"'",null);
        if(cur.getCount() > 0) {
            return true;
        }
        else return false;

    }
    public boolean cekLoginPetugas(String username, String password){
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_petugas where username='"+username+"' and password = '"+password+"'",null);
        if(cur.getCount() > 0) {
            return true;
        }
        else return false;

    }
    public ArrayList<NotaPetugas> loadNotaPetugas(String username){
        ArrayList<NotaPetugas> arrayku = new ArrayList<>();
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_nota where username_petugas='"+username+"'",null);
        int i=0;
        Log.e("Main",String.valueOf(cur.getCount()));
        if(cur.getCount() > 0) cur.moveToFirst();
        while(i<cur.getCount())
        {
            Integer status = Integer.valueOf(cur.getString(cur.getColumnIndex("status_cucian")));
            String status_string="";
            switch (status){
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
            NotaPetugas notaku = new NotaPetugas(cur.getString(cur.getColumnIndex("jml_cucian")),cur.getString(cur.getColumnIndex("jenis_paket")),status_string,cur.getString(cur.getColumnIndex("tanggal_masuk")),cur.getString(cur.getColumnIndex("username_konsumen")));
            arrayku.add(notaku);
            cur.moveToNext();
            i++;
        }
        return arrayku;

    }
    public ArrayList<DataKonsumen> loadDataKonsumen(){
        ArrayList<DataKonsumen> arrayku = new ArrayList<>();
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_konsumen",null);
        int i=0;
        if(cur.getCount() > 0) cur.moveToFirst();
        while(i<cur.getCount())
        {
            DataKonsumen dataku = new DataKonsumen(cur.getString(cur.getColumnIndex("nama")),cur.getString(cur.getColumnIndex("alamat")),cur.getString(cur.getColumnIndex("nomorhp")),cur.getString(cur.getColumnIndex("username")));
            arrayku.add(dataku);
            cur.moveToNext();
            i++;
        }
        return arrayku;

    }
    public ArrayList<DataTransaksi> loadDataTransaksi(){
        ArrayList<DataTransaksi> arrayku = new ArrayList<>();
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_transaksi",null);
        int i=0;
        if(cur.getCount() > 0) cur.moveToFirst();
        while(i<cur.getCount())
        {
            DataTransaksi dataku = new DataTransaksi(cur.getString(cur.getColumnIndex("jml_cucian")),cur.getString(cur.getColumnIndex("jenis_paket")),cur.getString(cur.getColumnIndex("tanggal_masuk")),cur.getString(cur.getColumnIndex("uang_masuk")));
            arrayku.add(dataku);
            cur.moveToNext();
            i++;
        }
        return arrayku;

    }
    public ArrayList<DataGaji> loadDataGaji(String username){
        ArrayList<DataGaji> arrayku = new ArrayList<>();
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_gaji where username_pegawai='"+username+"'",null);
        int i=0;
        if(cur.getCount() > 0) cur.moveToFirst();
        while(i<cur.getCount())
        {
            DataGaji dataku = new DataGaji(cur.getString(cur.getColumnIndex("username_pegawai")),cur.getString(cur.getColumnIndex("periode_gaji")),cur.getString(cur.getColumnIndex("tanggal_kirim")),cur.getString(cur.getColumnIndex("jumlah_gaji")));
            arrayku.add(dataku);
            cur.moveToNext();
            i++;
        }
        return arrayku;

    }
    public ArrayList<NotaKonsumen> loadNotaKonsumen(String username){
        ArrayList<NotaKonsumen> arrayku = new ArrayList<>();
        SQLiteDatabase dbku = this.getWritableDatabase();
        Cursor cur = dbku.rawQuery("select * from data_nota where username_konsumen='"+username+"'",null);
        int i=0;
        Log.e("Main",String.valueOf(cur.getCount()));
        if(cur.getCount() > 0) cur.moveToFirst();
        while(i<cur.getCount())
        {
            Integer status = Integer.valueOf(cur.getString(cur.getColumnIndex("status_cucian")));
            String status_string="";
            switch (status){
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
            NotaKonsumen notaku = new NotaKonsumen(cur.getString(cur.getColumnIndex("jml_cucian")),cur.getString(cur.getColumnIndex("jenis_paket")),status_string,cur.getString(cur.getColumnIndex("tanggal_masuk")));
            arrayku.add(notaku);
            cur.moveToNext();
            i++;
        }
        return arrayku;

    }
    public void hapusNota(String username,String jml_cucian,String tgl_masuk){
        SQLiteDatabase dbku = this.getWritableDatabase();
        dbku.delete("data_nota","username_konsumen='"+username+"' and jml_cucian="+jml_cucian+" and tanggal_masuk='"+tgl_masuk+"'",null);

    }
    public void gantiStatusNota(String username,String jml_cucian,String tgl_masuk,String status_baru){
        SQLiteDatabase dbku = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status_cucian", Integer.valueOf(status_baru));
        dbku.update("data_nota",cv,"username_konsumen='"+username+"' and jml_cucian="+jml_cucian+" and tanggal_masuk='"+tgl_masuk+"'",null);
    }
}
