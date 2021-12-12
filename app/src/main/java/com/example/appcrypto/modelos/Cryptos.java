package com.example.appcrypto.modelos;

public class Cryptos {

    private String nombre;
    private String descripcion;
    private String mc;
    private String supply;
    private String precio;
    private String icon;
    private String web;


    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Cryptos(){

    }

    public Cryptos(String nombre,String icon, String descripcion, String mc, String supply, String precio,String web) {
        this.nombre = nombre;
        this.icon = icon;
        this.descripcion = descripcion;
        this.mc = mc;
        this.supply = supply;
        this.precio = precio;
        this.web=web;

    }


}
