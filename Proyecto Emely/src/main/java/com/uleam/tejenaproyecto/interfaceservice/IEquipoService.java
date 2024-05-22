package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.Equipo;

import java.util.List;
import java.util.Optional;

public interface IEquipoService{

    public List<Equipo> listar();
    public Optional<Equipo> listarId(Long id);
    public Equipo save(Equipo p);
    public Equipo update(Long id, Equipo newUser);
    public boolean delete(Long userId);


}