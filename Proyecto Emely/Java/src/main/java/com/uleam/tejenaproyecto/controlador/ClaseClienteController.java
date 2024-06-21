package com.uleam.tejenaproyecto.controlador;

import com.uleam.tejenaproyecto.interfaceservice.IClaseClienteService;
import com.uleam.tejenaproyecto.modelo.ClaseCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clasesclientes")
public class ClaseClienteController {

    @Autowired
    private IClaseClienteService claseClienteService;

    @GetMapping
    public ResponseEntity<List<ClaseCliente>> getAllClasesClientes() {
        List<ClaseCliente> claseClienteList = claseClienteService.listar();
        return ResponseEntity.ok().body(claseClienteList);
    }

    @PostMapping
    public ResponseEntity<ClaseCliente> saveClaseCliente(@Validated @RequestBody ClaseCliente claseCliente) {
        ClaseCliente savedClaseCliente = claseClienteService.save(claseCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClaseCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClaseCliente>> getClaseClienteById(@PathVariable("id") Long id) {
        Optional<ClaseCliente> claseCliente = claseClienteService.listarId(id);
        if (claseCliente.isPresent()) {
            return ResponseEntity.ok().body(claseCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseCliente> updateClaseCliente(@RequestBody ClaseCliente claseCliente, @PathVariable("id") Long id) {
        ClaseCliente updatedClaseCliente = claseClienteService.update(id, claseCliente);
        return ResponseEntity.ok().body(updatedClaseCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaseClienteById(@PathVariable("id") Long id) {
        boolean deleted = claseClienteService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("ClaseCliente with id " + id + " has been successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ClaseCliente with id " + id + " was not found.");
        }
    }
}
