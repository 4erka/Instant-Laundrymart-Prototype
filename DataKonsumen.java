package com.example.whiteknight.laundrysystem;

/**
 * Created by whiteknight on 26/12/16.
 */

public class DataKonsumen {
    private String nama;
    private String alamat;
    private String nomor;
    private String username;

    public DataKonsumen(String nama, String alamat, String nomor, String username) {
        this.nama = nama;
        this.alamat = alamat;
        this.nomor = nomor;
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
