package com.topicos.proyecto;

import android.widget.EditText;
import android.widget.Spinner;

public class Usuario {

    private String nombre, ap, am, cont, tel, correo, sex;

    public Usuario() {

    }

    public Usuario(String nombre, String ap, String am, String cont, String tel, String correo, String sex) {
        this.nombre = nombre;
        this.ap = ap;
        this.am = am;
        this.cont = cont;
        this.tel = tel;
        this.correo = correo;
        this.sex = sex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
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

    public void setCorreo(String correoo) {
        this.correo = correoo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

