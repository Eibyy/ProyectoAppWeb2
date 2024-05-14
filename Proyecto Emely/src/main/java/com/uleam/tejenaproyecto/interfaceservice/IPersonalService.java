package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.Personal;

import java.util.List;
import java.util.Optional;

public interface IPersonalService {
    public List<Personal>listar();
    public Optional<Personal>listarId(int id);
    public int save(Personal p);
    public void delete(int id);

}
