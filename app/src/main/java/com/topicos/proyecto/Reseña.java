package com.topicos.proyecto;

import android.widget.TextView;

public class Reseña {

    String nom,app,apm,tel,correo, comtari,resta;
    float res;



    public Reseña(String nom, String app, String apm, String tel, String correo, String comtari, float res) {
        this.nom = nom;
        this.app = app;
        this.apm = apm;
        this.tel = tel;
        this.correo = correo;
        this.comtari = comtari;
        this.res= res;
        //this.resta=resta;
    }

    public String getResta() {
        return resta;
    }

    public void setResta(String resta) {
        this.resta = resta;
    }

    public float getRes() {
        return res;
    }

    public void setRes(float res) {
        this.res = res;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getComtari() {
        return comtari;
    }

    public void setComtari(String comtari) {
        this.comtari = comtari;
    }
}
