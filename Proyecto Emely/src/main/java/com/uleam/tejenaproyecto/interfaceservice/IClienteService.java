package com.uleam.tejenaproyecto.interfaceservice;

import com.uleam.tejenaproyecto.modelo.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    public List<Cliente> listar();
    public Optional<Cliente> listarId(Long id);
    public Cliente save(Cliente p);
    public Cliente update(Long id, Cliente newUser);
    public boolean delete(Long userId);


}