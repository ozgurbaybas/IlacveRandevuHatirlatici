package com.example.zgr.ilachatirlatici.Siniflar;

import android.text.format.Time;

import java.util.Date;

public class Randevular {

    int randevu_id;
    int randevu_uye_id;
    int randevu_doktor_id;
    Date randevu_muayene_tarihi;
    Time randevu_muayene_saati;
    String randevu_hastane;
    String randevu_brans;

    public String getRandevu_brans() {
        return randevu_brans;
    }

    public void setRandevu_brans(String randevu_brans) {
        this.randevu_brans = randevu_brans;
    }

    public String getRandevu_hastane() {
        return randevu_hastane;
    }

    public void setRandevu_hastane(String randevu_hastane) {
        this.randevu_hastane = randevu_hastane;
    }

    public int getRandevu_id() {
        return randevu_id;
    }

    public void setRandevu_id(int randevu_id) {
        this.randevu_id = randevu_id;
    }

    public int getRandevu_uye_id() {
        return randevu_uye_id;
    }

    public void setRandevu_uye_id(int randevu_uye_id) {
        this.randevu_uye_id = randevu_uye_id;
    }

    public int getRandevu_doktor_id() {
        return randevu_doktor_id;
    }

    public void setRandevu_doktor_id(int randevu_doktor_id) {
        this.randevu_doktor_id = randevu_doktor_id;
    }

    public Date getRandevu_muayene_tarihi() {
        return randevu_muayene_tarihi;
    }

    public void setRandevu_muayene_tarihi(Date randevu_muayene_tarihi) {
        this.randevu_muayene_tarihi = randevu_muayene_tarihi;
    }

    public Time getRandevu_muayene_saati() {
        return randevu_muayene_saati;
    }

    public void setRandevu_muayene_saati(Time randevu_muayene_saati) {
        this.randevu_muayene_saati = randevu_muayene_saati;
    }
}

