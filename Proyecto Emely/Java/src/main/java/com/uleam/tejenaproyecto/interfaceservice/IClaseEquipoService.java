package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.ClaseEquipo;

import java.util.List;
import java.util.Optional;

public interface IClaseEquipoService {

    public List<ClaseEquipo> listar();
    public Optional<ClaseEquipo> listarId(Long id);
    public ClaseEquipo save(ClaseEquipo p);
    public ClaseEquipo update(Long id, ClaseEquipo newUser);
    public boolean delete(Long userId);


}