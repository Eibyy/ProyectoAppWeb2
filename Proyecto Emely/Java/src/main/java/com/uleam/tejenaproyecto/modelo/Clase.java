package com.uleam.tejenaproyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "clases")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clase_id")
    private Long claseId;

    @Column(name = "nombre_clase")
    private String nombreClase;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "horario")
    private String horario;

    @Column(name = "personal_id")
    private Long personal_id;

    public Clase(){}
    public Clase(Long claseId, String nombreClase, String descripcion, String horario, Long personal) {
        this.claseId = claseId;
        this.nombreClase = nombreClase;
        this.descripcion = descripcion;
        this.horario = horario;
        this.personal_id = personal;
    }

    public Long getClaseId() {
        return claseId;
    }

    public void setClaseId(Long claseId) {
        this.claseId = claseId;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getPersonal() {
        return this.personal_id;
    }

    public void setPersonal(Long personal) {
        this.personal_id = personal;
    }
}
