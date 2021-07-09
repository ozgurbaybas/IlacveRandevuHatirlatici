package com.example.zgr.ilachatirlatici.Siniflar;

import android.text.format.Time;

import java.util.Date;

public class Ilaclar {

    private int ilac_id;
    private String ilac_name;
    private String ilac_kullanma_nedeni;
    private Date ilac_baslama_tarihi;
    private Date ilac_bitis_tarihi;
    private float ilac_dozaj;
    private int toplam_ilac_sayisi;
    private int ilac_kalan_miktar;
    private Time sabah_saat;
    private Time ogle_saat;
    private Time akşam_saat;
    private boolean sabah;
    private boolean ogle;
    private boolean aksam;
    private int gunlukKullanimMiktari;
    private int ilaclar_uye_id;
    private int ilacuye_ilac_id;
    private int ilacuye_uye_id;

    public int getIlacuye_uye_id() {
        return ilacuye_uye_id;
    }

    public void setIlacuye_uye_id(int ilacuye_uye_id) {
        this.ilacuye_uye_id = ilacuye_uye_id;
    }

    public int getIlacuye_ilac_id() {
        return ilacuye_ilac_id;
    }

    public void setIlacuye_ilac_id(int ilacuye_ilac_id) {
        this.ilacuye_ilac_id = ilacuye_ilac_id;
    }

    public int getIlaclar_uye_id() {
        return ilaclar_uye_id;
    }

    public void setIlaclar_uye_id(int ilaclar_uye_id) {
        this.ilaclar_uye_id = ilaclar_uye_id;
    }

    public void setIlac_id(int ilac_id) {
        this.ilac_id = ilac_id;
    }

    public int getGunlukKullanimMiktari() {
        return gunlukKullanimMiktari;
    }

    public void setGunlukKullanimMiktari(int gunlukKullanimMiktari) {
        this.gunlukKullanimMiktari = gunlukKullanimMiktari;
    }

    public int getIlac_id() {
        return ilac_id;
    }


    public String getIlac_name() {
        return ilac_name;
    }

    public void setIlac_name(String ilac_name) {
        this.ilac_name = ilac_name;
    }

    public String getIlac_kullanma_nedeni() {
        return ilac_kullanma_nedeni;
    }

    public void setIlac_kullanma_nedeni(String ilac_kullanma_nedeni) {
        this.ilac_kullanma_nedeni = ilac_kullanma_nedeni;
    }

    public Date getIlac_baslama_tarihi() {
        return ilac_baslama_tarihi;
    }

    public void setIlac_baslama_tarihi(Date ilac_baslama_tarihi) {
        this.ilac_baslama_tarihi = ilac_baslama_tarihi;
    }

    public Date getIlac_bitis_tarihi() {
        return ilac_bitis_tarihi;
    }

    public void setIlac_bitis_tarihi(Date ilac_bitis_tarihi) {
        this.ilac_bitis_tarihi = ilac_bitis_tarihi;
    }

    public float getIlac_dozaj() {
        return ilac_dozaj;
    }

    public void setIlac_dozaj(float ilac_dozaj) {
        this.ilac_dozaj = ilac_dozaj;
    }

    public int getToplam_ilac_sayisi() {
        return toplam_ilac_sayisi;
    }

    public void setToplam_ilac_sayisi(int toplam_ilac_sayisi) {
        this.toplam_ilac_sayisi = toplam_ilac_sayisi;
    }

    public int getIlac_kalan_miktar() {
        return ilac_kalan_miktar;
    }

    public void setIlac_kalan_miktar(int ilac_kalan_miktar) {
        this.ilac_kalan_miktar = ilac_kalan_miktar;
    }

    public Time getSabah_saat() {
        return sabah_saat;
    }

    public void setSabah_saat(Time sabah_saat) {
        this.sabah_saat = sabah_saat;
    }

    public Time getOgle_saat() {
        return ogle_saat;
    }

    public void setOgle_saat(Time ogle_saat) {
        this.ogle_saat = ogle_saat;
    }

    public Time getAkşam_saat() {
        return akşam_saat;
    }

    public void setAkşam_saat(Time akşam_saat) {
        this.akşam_saat = akşam_saat;
    }

    public boolean isSabah() {
        return sabah;
    }

    public void setSabah(boolean sabah) {
        this.sabah = sabah;
    }

    public boolean isOgle() {
        return ogle;
    }

    public void setOgle(boolean ogle) {
        this.ogle = ogle;
    }

    public boolean isAksam() {
        return aksam;
    }

    public void setAksam(boolean aksam) {
        this.aksam = aksam;
    }

    @Override
    public String toString() {
        return "Ilaclar{" +
                "ilac_id=" + ilac_id +
                ", ilac_name='" + ilac_name + '\'' +
                ", ilac_kullanma_nedeni='" + ilac_kullanma_nedeni + '\'' +
                ", ilac_baslama_tarihi=" + ilac_baslama_tarihi +
                ", ilac_bitis_tarihi=" + ilac_bitis_tarihi +
                ", ilac_dozaj=" + ilac_dozaj +
                ", toplam_ilac_sayisi=" + toplam_ilac_sayisi +
                ", ilac_kalan_miktar=" + ilac_kalan_miktar +
                ", sabah_saat=" + sabah_saat +
                ", ogle_saat=" + ogle_saat +
                ", akşam_saat=" + akşam_saat +
                ", sabah=" + sabah +
                ", ogle=" + ogle +
                ", aksam=" + aksam +
                ", gunlukKullanimMiktari=" + gunlukKullanimMiktari +
                '}';
    }
}
