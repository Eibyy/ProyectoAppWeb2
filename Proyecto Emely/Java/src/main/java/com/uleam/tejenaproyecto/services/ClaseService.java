package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IClaseRepository;
import com.uleam.tejenaproyecto.interfaceservice.IClaseService;
import com.uleam.tejenaproyecto.modelo.Clase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseService implements IClaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClaseService.class);

    @Autowired
    private IClaseRepository claseRepository;

    @Override
    public List<Clase> listar() {
        try {
            return claseRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todas las clases: {}", e.getMessage());
            throw new RuntimeException("Error al obtener clases");
        }
    }

    @Override
    public Optional<Clase> listarId(Long id) {
        try {
            Optional<Clase> clase = claseRepository.findById(id);
            if (clase.isPresent()) {
                return clase;
            } else {
                throw new RuntimeException("La clase con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener clase por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener la clase. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Clase save(Clase clase) {
        try {
            return claseRepository.save(clase);
        } catch (Exception e) {
            LOGGER.error("Error al guardar clase: {}", e.getMessage());
            throw new RuntimeException("Error al guardar clase");
        }
    }

    @Override
    public Clase update(Long id, Clase newClase) {
        try {
            Optional<Clase> existingClase = claseRepository.findById(id);
            if (existingClase.isPresent()) {
                Clase updatedClase = existingClase.get();
                updatedClase.setNombreClase(newClase.getNombreClase());
                updatedClase.setDescripcion(newClase.getDescripcion());
                updatedClase.setHorario(newClase.getHorario());
                updatedClase.setPersonal(newClase.getPersonal());
                return claseRepository.save(updatedClase);
            }
            throw new RuntimeException("Clase no encontrada");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar clase: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar clase");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Clase> clase = claseRepository.findById(id);
            if (clase.isPresent()) {
                claseRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("La clase que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar clase: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar la clase en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
