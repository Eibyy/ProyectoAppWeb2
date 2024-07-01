package com.uleam.tejenaproyecto.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "clases_equipos")
public class ClaseEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clase_equipo_id")
    private Long claseEquipoId;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    @Column(name = "cantidad")
    private int cantidad;

    public ClaseEquipo(){}
    public ClaseEquipo(Long claseEquipoId, Clase clase, Equipo equipo, int cantidad) {
        this.claseEquipoId = claseEquipoId;
        this.clase = clase;
        this.equipo = equipo;
        this.cantidad = cantidad;
    }

    public Long getClaseEquipoId() {
        return claseEquipoId;
    }

    public void setClaseEquipoId(Long claseEquipoId) {
        this.claseEquipoId = claseEquipoId;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
