package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IClaseService;
import com.uleam.tejenaproyecto.modelo.Clase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clases")
public class ClaseController {

    @Autowired
    private IClaseService claseService;

    @GetMapping
    public ResponseEntity<List<Clase>> getAllClases() {
        List<Clase> claseList = claseService.listar();
        return ResponseEntity.ok().body(claseList);
    }

    @PostMapping
    public ResponseEntity<Clase> saveClase(@Validated @RequestBody Clase clase) {
        Clase savedClase = claseService.save(clase);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clase>> getClaseById(@PathVariable("id") Long id) {
        Optional<Clase> clase = claseService.listarId(id);
        if (clase.isPresent()) {
            return ResponseEntity.ok().body(clase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> updateClase(@RequestBody Clase clase, @PathVariable("id") Long id) {
        Clase updatedClase = claseService.update(id, clase);
        return ResponseEntity.ok().body(updatedClase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaseById(@PathVariable("id") Long id) {
        boolean deleted = claseService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Clase with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clase with id " + id + " was not found.");
        }
    }
}
