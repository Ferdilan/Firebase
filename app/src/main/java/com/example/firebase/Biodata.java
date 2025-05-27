package com.example.firebase;

public class Biodata {
    private String biodata_Id, biodata_Nama, biodata_Umur, biodata_JK;

    public Biodata() {
    }

    public Biodata(String biodata_Id, String biodata_Nama, String biodata_Umur, String biodata_JK) {
        this.biodata_Id = biodata_Id;
        this.biodata_Nama = biodata_Nama;
        this.biodata_Umur = biodata_Umur;
        this.biodata_JK = biodata_JK;
    }


    public String getBiodata_Id() {
        return biodata_Id;
    }

    public String getBiodata_Nama() {
        return biodata_Nama;
    }

    public String getBiodata_Umur() {
        return biodata_Umur;
    }

    public String getBiodata_JK() {
        return biodata_JK;
    }
}
