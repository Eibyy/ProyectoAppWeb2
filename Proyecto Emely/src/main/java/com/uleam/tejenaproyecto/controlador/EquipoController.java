package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IEquipoService;
import com.uleam.tejenaproyecto.modelo.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private IEquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipoList = equipoService.listar();
        return ResponseEntity.ok().body(equipoList);
    }

    @PostMapping
    public ResponseEntity<Equipo> saveEquipo(@Validated @RequestBody Equipo equipo) {
        Equipo savedEquipo = equipoService.save(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Equipo>> getEquipoById(@PathVariable("id") Long id) {
        Optional<Equipo> equipo = equipoService.listarId(id);
        if (equipo.isPresent()) {
            return ResponseEntity.ok().body(equipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@RequestBody Equipo equipo, @PathVariable("id") Long id) {
        Equipo updatedEquipo = equipoService.update(id, equipo);
        return ResponseEntity.ok().body(updatedEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEquipoById(@PathVariable("id") Long id) {
        boolean deleted = equipoService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Equipo with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipo with id " + id + " was not found.");
        }
    }
}
