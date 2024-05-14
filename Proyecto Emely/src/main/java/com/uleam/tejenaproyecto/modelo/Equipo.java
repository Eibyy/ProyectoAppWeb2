package com.uleam.tejenaproyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipo_id")
    private Long equipoId;

    @Column(name = "nombre_equipo")
    private String nombreEquipo;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    public Equipo(){}
    public Equipo(Long equipoId, String nombreEquipo, String marca, String modelo) {
        this.equipoId = equipoId;
        this.nombreEquipo = nombreEquipo;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Long getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Long equipoId) {
        this.equipoId = equipoId;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
