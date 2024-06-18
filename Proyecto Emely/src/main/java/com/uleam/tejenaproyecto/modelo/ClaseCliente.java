package com.uleam.tejenaproyecto.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clases_clientes")
public class ClaseCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clase_cliente_id")
    private Long claseClienteId;

    @Column(name = "cliente_id")
    private Long cliente;

    @Column(name = "clase_id")
    private Long clase;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    public ClaseCliente(){}

    public ClaseCliente(Long claseClienteId, Long cliente, Long clase, LocalDate fechaRegistro) {
        this.claseClienteId = claseClienteId;
        this.cliente = cliente;
        this.clase = clase;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getClaseClienteId() {
        return claseClienteId;
    }

    public void setClaseClienteId(Long claseClienteId) {
        this.claseClienteId = claseClienteId;
    }

    public Long getCliente() {
        return cliente;
    }

    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    public Long getClase() {
        return clase;
    }

    public void setClase(Long clase) {
        this.clase = clase;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
