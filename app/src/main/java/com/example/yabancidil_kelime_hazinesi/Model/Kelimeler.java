package com.example.yabancidil_kelime_hazinesi.Model;

public class Kelimeler {
    private long Id;
    private String Dil;
    private String Kelime;
    private String Anlami;

    public Kelimeler(String dil, String anlami, String kelime) {
        Dil = dil;
        Anlami = anlami;
        Kelime = kelime;
    }

    public Kelimeler() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getDil() {
        return Dil;
    }

    public void setDil(String dil) {
        Dil = dil;
    }

    public String getKelime() {
        return Kelime;
    }

    public void setKelime(String kelime) {
        Kelime = kelime;
    }

    public String getAnlami() {
        return Anlami;
    }

    public void setAnlami(String anlami) {
        Anlami = anlami;
    }
}
