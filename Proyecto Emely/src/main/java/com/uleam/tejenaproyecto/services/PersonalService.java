package com.uleam.tejenaproyecto.services;

import com.uleam.tejenaproyecto.interfaces.IPersonalRepository;
import com.uleam.tejenaproyecto.interfaceservice.IPersonalService;
import com.uleam.tejenaproyecto.modelo.Personal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService implements IPersonalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalService.class);

    @Autowired
    private IPersonalRepository personalRepository;

    @Override
    public List<Personal> listar() {
        try {
            return personalRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todo el personal: {}", e.getMessage());
            throw new RuntimeException("Error al obtener personal");
        }
    }

    @Override
    public Optional<Personal> listarId(Long id) {
        try {
            Optional<Personal> personal = personalRepository.findById(id);
            if (personal.isPresent()) {
                return personal;
            } else {
                throw new RuntimeException("El rol con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener rol por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener el rol. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Personal save(Personal personal) {
        try {
            return personalRepository.save(personal);
        } catch (Exception e) {
            LOGGER.error("Error al guardar personal: {}", e.getMessage());
            throw new RuntimeException("Error al guardar personal");
        }
    }

    @Override
    public Personal update(Long id, Personal newPersonal) {
        try {
            Optional<Personal> existingPersonal = personalRepository.findById(id);
            if (existingPersonal.isPresent()) {
                Personal updatedPersonal = existingPersonal.get();
                updatedPersonal.setNombre_personal(newPersonal.getNombre_personal());
                updatedPersonal.setEdad(newPersonal.getEdad());
                updatedPersonal.setDireccion(newPersonal.getDireccion());
                updatedPersonal.setSalario(newPersonal.getSalario());
                updatedPersonal.setRol(newPersonal.getRol());
                return personalRepository.save(updatedPersonal);
            }
            throw new RuntimeException("Personal no encontrado");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar personal: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar personal");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Personal> personal = personalRepository.findById(id);
            if (personal.isPresent()) {
                personalRepository.deleteById(id);
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
