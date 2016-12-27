package com.example.whiteknight.laundrysystem;

/**
 * Created by whiteknight on 25/12/16.
 */

public class NotaKonsumen {
    private String jml_cucian;
    private String jenis_paket;
    private String status_cucian;
    private String tanggal_masuk;

    public NotaKonsumen(String jml_cucian, String jenis_paket, String status_cucian, String tanggal_masuk) {
        this.jml_cucian = jml_cucian;
        this.jenis_paket = jenis_paket;
        this.status_cucian = status_cucian;
        this.tanggal_masuk = tanggal_masuk;
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

    public String getStatus_cucian() {
        return status_cucian;
    }

    public void setStatus_cucian(String status_cucian) {
        this.status_cucian = status_cucian;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }
}
