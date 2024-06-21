package com.uleam.tejenaproyecto.interfaces;

import com.uleam.tejenaproyecto.modelo.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClaseRepository extends JpaRepository<Clase, Long> {
}