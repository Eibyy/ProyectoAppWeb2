package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IClienteRepository;
import com.uleam.tejenaproyecto.interfaceservice.IClienteService;
import com.uleam.tejenaproyecto.modelo.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public List<Cliente> listar() {
        try {
            return clienteRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todos los clientes: {}", e.getMessage());
            throw new RuntimeException("Error al obtener clientes");
        }
    }

    @Override
    public Optional<Cliente> listarId(Long id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                return cliente;
            } else {
                throw new RuntimeException("El cliente con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener cliente por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener el cliente. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Cliente save(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (Exception e) {
            LOGGER.error("Error al guardar cliente: {}", e.getMessage());
            throw new RuntimeException("Error al guardar cliente");
        }
    }

    @Override
    public Cliente update(Long id, Cliente newCliente) {
        try {
            Optional<Cliente> existingCliente = clienteRepository.findById(id);
            if (existingCliente.isPresent()) {
                Cliente updatedCliente = existingCliente.get();
                updatedCliente.setNombreCliente(newCliente.getNombreCliente());
                updatedCliente.setTelefono(newCliente.getTelefono());
                updatedCliente.setDireccion(newCliente.getDireccion());
                updatedCliente.setEmail(newCliente.getEmail());
                return clienteRepository.save(updatedCliente);
            }
            throw new RuntimeException("Cliente no encontrado");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar cliente: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar cliente");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Cliente> cliente = clienteRepository.findById(id);
            if (cliente.isPresent()) {
                clienteRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("El cliente que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar cliente: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar el cliente en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
