package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IClaseEquipoRepository;
import com.uleam.tejenaproyecto.interfaceservice.IClaseEquipoService;
import com.uleam.tejenaproyecto.modelo.ClaseEquipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseEquipoService implements IClaseEquipoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClaseEquipoService.class);

    @Autowired
    private IClaseEquipoRepository claseEquipoRepository;

    @Override
    public List<ClaseEquipo> listar() {
        try {
            return claseEquipoRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todas las clases de equipo: {}", e.getMessage());
            throw new RuntimeException("Error al obtener clases de equipo");
        }
    }

    @Override
    public Optional<ClaseEquipo> listarId(Long id) {
        try {
            Optional<ClaseEquipo> claseEquipo = claseEquipoRepository.findById(id);
            if (claseEquipo.isPresent()) {
                return claseEquipo;
            } else {
                throw new RuntimeException("La clase de equipo con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener clase de equipo por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener la clase de equipo. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public ClaseEquipo save(ClaseEquipo claseEquipo) {
        try {
            return claseEquipoRepository.save(claseEquipo);
        } catch (Exception e) {
            LOGGER.error("Error al guardar clase de equipo: {}", e.getMessage());
            throw new RuntimeException("Error al guardar clase de equipo");
        }
    }

    @Override
    public ClaseEquipo update(Long id, ClaseEquipo newClaseEquipo) {
        try {
            Optional<ClaseEquipo> existingClaseEquipo = claseEquipoRepository.findById(id);
            if (existingClaseEquipo.isPresent()) {
                ClaseEquipo updatedClaseEquipo = existingClaseEquipo.get();
                updatedClaseEquipo.setClase(newClaseEquipo.getClase());
                updatedClaseEquipo.setEquipo(newClaseEquipo.getEquipo());
                updatedClaseEquipo.setCantidad(newClaseEquipo.getCantidad());
                return claseEquipoRepository.save(updatedClaseEquipo);
            }
            throw new RuntimeException("Clase de equipo no encontrada");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar clase de equipo: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar clase de equipo");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<ClaseEquipo> claseEquipo = claseEquipoRepository.findById(id);
            if (claseEquipo.isPresent()) {
                claseEquipoRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("La clase de equipo que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar clase de equipo: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar la clase de equipo en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
