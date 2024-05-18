package com.uleam.tejenaproyecto.interfaces;

import com.uleam.tejenaproyecto.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}