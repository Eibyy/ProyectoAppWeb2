package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IRolRepository;
import com.uleam.tejenaproyecto.interfaceservice.IRolService;
import com.uleam.tejenaproyecto.modelo.Rol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService implements IRolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolService.class);

    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> listar() {
        try {
            return rolRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todo el personal: {}", e.getMessage());
            throw new RuntimeException("Error al obtener personal");
        }
    }

    @Override
    public Optional<Rol> listarId(Long id) {
        try {
            Optional<Rol> rol = rolRepository.findById(id);
            if (rol.isPresent()) {
                return rol;
            } else {
                throw new RuntimeException("El rol con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener rol por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener el rol. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Rol save(Rol rol) {
        try {
            return rolRepository.save(rol);
        } catch (Exception e) {
            LOGGER.error("Error al guardar rol: {}", e.getMessage());
            throw new RuntimeException("Error al guardar rol");
        }
    }

    @Override
    public Rol update(Long id, Rol newRol) {
        try {
            Optional<Rol> existingRol = rolRepository.findById(id);
            if (existingRol.isPresent()) {
                Rol updatedRol = existingRol.get();
                updatedRol.setDescripcion(newRol.getDescripcion());
                updatedRol.setNombreRol(newRol.getNombreRol());
                return rolRepository.save(updatedRol);
            }
            throw new RuntimeException("Rol no encontrado");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar rol: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar rol");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Rol> rol = rolRepository.findById(id);
            if (rol.isPresent()) {
                rolRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("El rol que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar rol: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar el rol en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
