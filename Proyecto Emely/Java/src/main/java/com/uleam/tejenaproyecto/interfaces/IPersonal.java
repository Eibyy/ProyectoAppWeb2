package com.uleam.tejenaproyecto.interfaces;

import com.uleam.tejenaproyecto.modelo.Personal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonal extends CrudRepository<Personal, Integer> {
}
