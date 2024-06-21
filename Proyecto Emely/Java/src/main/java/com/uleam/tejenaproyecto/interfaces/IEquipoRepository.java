package com.uleam.tejenaproyecto.interfaces;

import com.uleam.tejenaproyecto.modelo.Clase;
import com.uleam.tejenaproyecto.modelo.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipoRepository extends JpaRepository<Equipo, Long> {
}