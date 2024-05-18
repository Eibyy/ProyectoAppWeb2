package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IEquipoRepository;
import com.uleam.tejenaproyecto.interfaceservice.IEquipoService;
import com.uleam.tejenaproyecto.modelo.Equipo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService implements IEquipoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EquipoService.class);

    @Autowired
    private IEquipoRepository equipoRepository;

    @Override
    public List<Equipo> listar() {
        try {
            return equipoRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todos los equipos: {}", e.getMessage());
            throw new RuntimeException("Error al obtener equipos");
        }
    }

    @Override
    public Optional<Equipo> listarId(Long id) {
        try {
            Optional<Equipo> equipo = equipoRepository.findById(id);
            if (equipo.isPresent()) {
                return equipo;
            } else {
                throw new RuntimeException("El equipo con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener equipo por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener el equipo. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Equipo save(Equipo equipo) {
        try {
            return equipoRepository.save(equipo);
        } catch (Exception e) {
            LOGGER.error("Error al guardar equipo: {}", e.getMessage());
            throw new RuntimeException("Error al guardar equipo");
        }
    }

    @Override
    public Equipo update(Long id, Equipo newEquipo) {
        try {
            Optional<Equipo> existingEquipo = equipoRepository.findById(id);
            if (existingEquipo.isPresent()) {
                Equipo updatedEquipo = existingEquipo.get();
                updatedEquipo.setNombreEquipo(newEquipo.getNombreEquipo());
                updatedEquipo.setMarca(newEquipo.getMarca());
                updatedEquipo.setModelo(newEquipo.getModelo());
                return equipoRepository.save(updatedEquipo);
            }
            throw new RuntimeException("Equipo no encontrado");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar equipo: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar equipo");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Equipo> equipo = equipoRepository.findById(id);
            if (equipo.isPresent()) {
                equipoRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("El equipo que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar equipo: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar el equipo en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
