package com.example.zgr.ilachatirlatici.Siniflar;


public class Uyeler {

    private int uye_id;
    private String uye_name;
    private String uye_surname;
    private String email;
    private String username;
    private String password;
    private static final long serialVersionUID = 1L;


    public int getUye_id() {

        return uye_id;
    }

    public void setUye_id(int uye_id) {
        this.uye_id = uye_id;
    }

    public String getUye_surname() {
        return uye_surname;
    }

    public void setUye_surname(String uye_surname) {
        this.uye_surname = uye_surname;
    }

    public String getUye_name() {
        return uye_name;
    }

    public void setUye_name(String uye_name) {
        this.uye_name = uye_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "uyeler{" +
                "uye_id=" + uye_id +
                ", uye_name='" + uye_name + '\'' +
                ", uye_surname='" + uye_surname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
