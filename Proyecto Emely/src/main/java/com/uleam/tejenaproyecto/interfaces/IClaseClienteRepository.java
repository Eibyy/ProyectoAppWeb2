package com.uleam.tejenaproyecto.interfaces;

import com.uleam.tejenaproyecto.modelo.ClaseCliente;
import com.uleam.tejenaproyecto.modelo.ClaseEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClaseClienteRepository extends JpaRepository<ClaseCliente, Long> {
}