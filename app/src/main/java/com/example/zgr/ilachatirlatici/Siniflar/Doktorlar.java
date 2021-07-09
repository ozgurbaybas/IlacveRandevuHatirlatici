package com.example.zgr.ilachatirlatici.Siniflar;

public class Doktorlar {

    private int doctor_id;
    private String doctor_name;
    private String doctor_surname;
    private String doctor_brans;
    private String doctor_hospital;
    private int doktorlar_uye_id;
    private int randevular_doktor_id;

    public int getRandevular_doktor_id() {
        return randevular_doktor_id;
    }

    public void setRandevular_doktor_id(int randevular_doktor_id) {
        this.randevular_doktor_id = randevular_doktor_id;
    }

    public int getDoktorlar_uye_id() {
        return doktorlar_uye_id;
    }

    public void setDoktorlar_uye_id(int doktorlar_uye_id) {
        this.doktorlar_uye_id = doktorlar_uye_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }


    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_brans() {
        return doctor_brans;
    }

    public void setDoctor_brans(String doctor_brans) {
        this.doctor_brans = doctor_brans;
    }

    public String getDoctor_surname() {
        return doctor_surname;
    }

    public void setDoctor_surname(String doctor_surname) {
        this.doctor_surname = doctor_surname;
    }

    public String getDoctor_hospital() {
        return doctor_hospital;
    }

    public void setDoctor_hospital(String doctor_hospital) {
        this.doctor_hospital = doctor_hospital;
    }

    @Override
    public String toString() {
        return "Doktorlar{" +
                "doctor_id=" + doctor_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", doctor_surname='" + doctor_surname + '\'' +
                ", doctor_brans='" + doctor_brans + '\'' +
                ", doctor_hospital='" + doctor_hospital + '\'' +
                '}';
    }
}
