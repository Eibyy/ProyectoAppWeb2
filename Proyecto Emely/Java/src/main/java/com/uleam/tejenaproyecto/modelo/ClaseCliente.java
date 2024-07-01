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

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    private Clase clase;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    public ClaseCliente(){}

    public ClaseCliente(Long claseClienteId, Cliente cliente, Clase clase, LocalDate fechaRegistro) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
