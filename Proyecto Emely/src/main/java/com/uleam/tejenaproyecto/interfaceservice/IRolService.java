package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.Personal;
import com.uleam.tejenaproyecto.modelo.Rol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IRolService {

    public List<Rol> listar();
    public Optional<Rol>listarId(Long id);
    public Rol save(Rol p);
    public Rol update(Long id, Rol newUser);
    public boolean delete(Long userId);

}
