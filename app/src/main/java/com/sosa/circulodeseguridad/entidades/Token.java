package com.sosa.circulodeseguridad.entidades;

import java.io.Serializable;

public class Token implements Serializable {
    public String Token ;
    public String Expiracion;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getExpiracion() {
        return Expiracion;
    }

    public void setExpiracion(String expiracion) {
        Expiracion = expiracion;
    }
}
