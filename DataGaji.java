package com.example.whiteknight.laundrysystem;

/**
 * Created by whiteknight on 26/12/16.
 */

public class DataGaji {
    private String username;
    private String periode_gaji;
    private String tanggal_kirim;
    private String jumlah_gaji;

    public DataGaji(String username, String periode_gaji, String tanggal_kirim, String jumlah_gaji) {
        this.username = username;
        this.periode_gaji = periode_gaji;
        this.tanggal_kirim = tanggal_kirim;
        this.jumlah_gaji = jumlah_gaji;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPeriode_gaji() {
        return periode_gaji;
    }

    public void setPeriode_gaji(String periode_gaji) {
        this.periode_gaji = periode_gaji;
    }

    public String getTanggal_kirim() {
        return tanggal_kirim;
    }

    public void setTanggal_kirim(String tanggal_kirim) {
        this.tanggal_kirim = tanggal_kirim;
    }

    public String getJumlah_gaji() {
        return jumlah_gaji;
    }

    public void setJumlah_gaji(String jumlah_gaji) {
        this.jumlah_gaji = jumlah_gaji;
    }
}
