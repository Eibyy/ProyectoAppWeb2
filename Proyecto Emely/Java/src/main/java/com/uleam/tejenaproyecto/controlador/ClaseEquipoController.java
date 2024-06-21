package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IClaseEquipoService;
import com.uleam.tejenaproyecto.modelo.ClaseEquipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/claseequipos")
public class ClaseEquipoController {

    @Autowired
    private IClaseEquipoService claseEquipoService;

    @GetMapping
    public ResponseEntity<List<ClaseEquipo>> getAllClaseEquipos() {
        List<ClaseEquipo> claseEquipoList = claseEquipoService.listar();
        return ResponseEntity.ok().body(claseEquipoList);
    }

    @PostMapping
    public ResponseEntity<ClaseEquipo> saveClaseEquipo(@Validated @RequestBody ClaseEquipo claseEquipo) {
        ClaseEquipo savedClaseEquipo = claseEquipoService.save(claseEquipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClaseEquipo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClaseEquipo>> getClaseEquipoById(@PathVariable("id") Long id) {
        Optional<ClaseEquipo> claseEquipo = claseEquipoService.listarId(id);
        if (claseEquipo.isPresent()) {
            return ResponseEntity.ok().body(claseEquipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseEquipo> updateClaseEquipo(@RequestBody ClaseEquipo claseEquipo, @PathVariable("id") Long id) {
        ClaseEquipo updatedClaseEquipo = claseEquipoService.update(id, claseEquipo);
        return ResponseEntity.ok().body(updatedClaseEquipo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaseEquipoById(@PathVariable("id") Long id) {
        boolean deleted = claseEquipoService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("ClaseEquipo with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClaseEquipo with id " + id + " was not found.");
        }
    }
}
