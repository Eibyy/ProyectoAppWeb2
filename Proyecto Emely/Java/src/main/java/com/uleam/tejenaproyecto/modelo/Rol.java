package com.uleam.tejenaproyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rol_id;

    @Column(name = "nombre_rol")
    private String nombreRol;

    @Column(name = "descripcion")
    private String descripcion;

    public  Rol(){}
    public Rol(Long rolId, String nombreRol, String descripcion) {
        this.rol_id = rolId;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
    }

    public Long getRolId() {
        return rol_id;
    }

    public void setRolId(Long rolId) {
        this.rol_id = rolId;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
