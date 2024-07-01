package com.uleam.tejenaproyecto.interfaces;

import com.uleam.tejenaproyecto.modelo.Personal;
import com.uleam.tejenaproyecto.modelo.Rol;
import org.springframework.data.repository.CrudRepository;

public interface IRol extends CrudRepository<Rol, Integer> {
}
