package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IClaseClienteRepository;
import com.uleam.tejenaproyecto.interfaceservice.IClaseClienteService;
import com.uleam.tejenaproyecto.modelo.ClaseCliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseClienteService implements IClaseClienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClaseClienteService.class);

    @Autowired
    private IClaseClienteRepository claseClienteRepository;

    @Override
    public List<ClaseCliente> listar() {
        try {
            return claseClienteRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todas las clases de cliente: {}", e.getMessage());
            throw new RuntimeException("Error al obtener clases de cliente");
        }
    }

    @Override
    public Optional<ClaseCliente> listarId(Long id) {
        try {
            Optional<ClaseCliente> claseCliente = claseClienteRepository.findById(id);
            if (claseCliente.isPresent()) {
                return claseCliente;
            } else {
                throw new RuntimeException("La clase de cliente con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener clase de cliente por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener la clase de cliente. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public ClaseCliente save(ClaseCliente claseCliente) {
        try {
            return claseClienteRepository.save(claseCliente);
        } catch (Exception e) {
            LOGGER.error("Error al guardar clase de cliente: {}", e.getMessage());
            throw new RuntimeException("Error al guardar clase de cliente");
        }
    }

    @Override
    public ClaseCliente update(Long id, ClaseCliente newClaseCliente) {
        try {
            Optional<ClaseCliente> existingClaseCliente = claseClienteRepository.findById(id);
            if (existingClaseCliente.isPresent()) {
                ClaseCliente updatedClaseCliente = existingClaseCliente.get();
                updatedClaseCliente.setCliente(newClaseCliente.getCliente());
                updatedClaseCliente.setClase(newClaseCliente.getClase());
                updatedClaseCliente.setFechaRegistro(newClaseCliente.getFechaRegistro());
                return claseClienteRepository.save(updatedClaseCliente);
            }
            throw new RuntimeException("Clase de cliente no encontrada");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar clase de cliente: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar clase de cliente");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<ClaseCliente> claseCliente = claseClienteRepository.findById(id);
            if (claseCliente.isPresent()) {
                claseClienteRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("La clase de cliente que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar clase de cliente: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar la clase de cliente en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
