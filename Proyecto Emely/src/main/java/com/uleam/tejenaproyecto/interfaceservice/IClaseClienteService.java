package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.ClaseCliente;

import java.util.List;
import java.util.Optional;

public interface IClaseClienteService {

    public List<ClaseCliente> listar();
    public Optional<ClaseCliente> listarId(Long id);
    public ClaseCliente save(ClaseCliente p);
    public ClaseCliente update(Long id, ClaseCliente newUser);
    public boolean delete(Long userId);


}