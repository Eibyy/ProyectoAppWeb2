package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.Clase;
import java.util.List;
import java.util.Optional;

public interface IClaseService{

    public List<Clase> listar();
    public Optional<Clase> listarId(Long id);
    public Clase save(Clase p);
    public Clase update(Long id, Clase newUser);
    public boolean delete(Long userId);


}