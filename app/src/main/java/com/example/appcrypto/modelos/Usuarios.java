package com.example.appcrypto.modelos;
import com.orm.SugarRecord;

public class Usuarios extends SugarRecord{

    private String password;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Usuarios() {
    }

    public Usuarios(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
