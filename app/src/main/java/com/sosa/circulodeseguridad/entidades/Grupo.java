package com.sosa.circulodeseguridad.entidades;

import java.io.Serializable;

public class Grupo implements Serializable {
    public String Identificador;
    public String Nombre;
    public String Descripcion;
    public String AvatarGrupo;
    public String FechaCreacion;

    public String getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(String identificador) {
        Identificador = identificador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getAvatarGrupo() {
        return AvatarGrupo;
    }

    public void setAvatarGrupo(String avatarGrupo) {
        AvatarGrupo = avatarGrupo;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
}
