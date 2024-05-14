package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    public List<Rol>listar();
    public Optional<Rol>listarId(int id);
    public int save(Rol p);
    public void delete(int id);

}
