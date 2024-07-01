package com.uleam.tejenaproyecto.modelo;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "nombre_rol")
    private String nombreRol;

    @Column(name = "descripcion")
    private String descripcion;

    public  Rol(){}
    public Rol(Long rolId, String nombreRol, String descripcion) {
        this.rolId = rolId;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
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
