package com.example.whiteknight.laundrysystem;

/**
 * Created by whiteknight on 27/12/16.
 */

public class DataTransaksi {
    private String jml_cucian;
    private String jenis_paket;
    private String tanggal_masuk;
    private String uang_masuk;

    public DataTransaksi(String jml_cucian, String jenis_paket, String tanggal_masuk, String uang_masuk) {
        this.jml_cucian = jml_cucian;
        this.jenis_paket = jenis_paket;
        this.tanggal_masuk = tanggal_masuk;
        this.uang_masuk = uang_masuk;
    }

    public String getJml_cucian() {
        return jml_cucian;
    }

    public void setJml_cucian(String jml_cucian) {
        this.jml_cucian = jml_cucian;
    }

    public String getJenis_paket() {
        return jenis_paket;
    }

    public void setJenis_paket(String jenis_paket) {
        this.jenis_paket = jenis_paket;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }

    public String getUang_masuk() {
        return uang_masuk;
    }

    public void setUang_masuk(String uang_masuk) {
        this.uang_masuk = uang_masuk;
    }
}
