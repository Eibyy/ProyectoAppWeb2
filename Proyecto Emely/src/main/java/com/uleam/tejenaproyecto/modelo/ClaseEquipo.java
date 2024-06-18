package com.uleam.tejenaproyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "clases_equipos")
public class ClaseEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clase_equipo_id")
    private Long claseEquipoId;

    @Column(name = "clase_id")
    private Long clase_id;

    @Column(name = "equipo_id")
    private Long equipo_id;

    @Column(name = "cantidad")
    private int cantidad;

    public ClaseEquipo(){}
    public ClaseEquipo(Long claseEquipoId, Long clase, Long equipo, int cantidad) {
        this.claseEquipoId = claseEquipoId;
        this.clase_id = clase;
        this.equipo_id = equipo;
        this.cantidad = cantidad;
    }

    public Long getClaseEquipoId() {
        return claseEquipoId;
    }

    public void setClaseEquipoId(Long claseEquipoId) {
        this.claseEquipoId = claseEquipoId;
    }

    public Long getClase() {
        return clase_id;
    }

    public void setClase(Long clase) {
        this.clase_id = clase;
    }

    public Long getEquipo() {
        return equipo_id;
    }

    public void setEquipo(Long equipo) {
        this.equipo_id = equipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
